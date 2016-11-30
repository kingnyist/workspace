package com.bee.base.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bee.base.bean.BaseResponse;
import com.bee.base.bean.RequestAuthorize;
import com.bee.base.bean.ResultBean;
import com.bee.base.dao.MapRoleMenuMapper;
import com.bee.base.model.BeeMenu;
import com.bee.base.model.BeeUser;
import com.bee.base.model.MapRoleMenu;
import com.bee.common.util.DateUtil;
import com.bee.common.util.ToolUtil;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/authorize")  
public class RoleMenuController extends BaseController{
	private Logger log = Logger.getLogger(RoleMenuController.class);
	
    @Resource  
    private MapRoleMenuMapper beeRoleMenuDao;  
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)  
    @ResponseBody  
    public String menuShow(@RequestBody MapRoleMenu requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	MapRoleMenu mapRoleMenu = requestBean;
    	mapRoleMenu.setLimit(null);
    	mapRoleMenu.setOffset(null);
        List<MapRoleMenu> list = beeRoleMenuDao.selectListByCondition(mapRoleMenu);
        BaseResponse<MapRoleMenu> responseBean = new BaseResponse<MapRoleMenu>();
        responseBean.setRows(list);

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/operate", method = RequestMethod.POST)  
    @ResponseBody  
    public String roleShow(@RequestBody RequestAuthorize requestAuthorize,HttpServletRequest request){
    	log.info("授权操作开始");
    	BeeUser beeUser = (BeeUser) request.getSession().getAttribute("bee");
    	MapRoleMenu mapRoleMenu = new MapRoleMenu();
    	mapRoleMenu.setRoleNo(requestAuthorize.getRoleNo());
    	beeRoleMenuDao.deleteBySelectivity(mapRoleMenu);
    	
    	for(MapRoleMenu bean : requestAuthorize.getList()){
    		bean.setId(ToolUtil.getUUID());
    		bean.setRoleNo(requestAuthorize.getRoleNo());
    		bean.setMenuNo(bean.getMenuNo());
    		bean.setCreateTime(DateUtil.getNowDate());
    		bean.setUpdateTime(DateUtil.getNowDate());
    		bean.setStatus("1");
    		bean.setStatusRem(beeUser.getUserNo()+"["+beeUser.getUserName()+"]授权");
    		beeRoleMenuDao.insert(bean);
    	}
    	
        ResultBean result = new ResultBean();
    	result.setRspCode(SystemCode.SUCCESS_CODE);
    	result.setRspMsg(SystemCode.SUCCESS_MSG);
    	
        return objectToJson(result);  
    } 
    
}  