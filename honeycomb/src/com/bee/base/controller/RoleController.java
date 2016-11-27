package com.bee.base.controller;

import java.util.Date;
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
import com.bee.base.bean.ResultBean;
import com.bee.base.dao.BeeRoleMapper;
import com.bee.base.model.BeeRole;
import com.bee.common.util.DateUtil;
import com.bee.common.util.ToolUtil;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/role")  
public class RoleController extends BaseController{
	private Logger log = Logger.getLogger(RoleController.class);
	
    @Resource  
    private BeeRoleMapper beeRoleDao;  
    
    @RequestMapping(value = "/roleShow", method = RequestMethod.POST)  
    @ResponseBody  
    public String roleShow(@RequestBody BeeRole requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeRole BeeRole = requestBean;
        BeeRole.setLimit(null);
        BeeRole.setOffset(null);
        List<BeeRole> list = beeRoleDao.selectListByCondition(BeeRole);
        BaseResponse<BeeRole> responseBean = new BaseResponse<BeeRole>();
        responseBean.setRows(list);

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)  
    @ResponseBody  
    public String selectList(@RequestBody BeeRole requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeRole BeeRole = requestBean;
        List<BeeRole> list = beeRoleDao.selectListByCondition(BeeRole);
        BaseResponse<BeeRole> responseBean = new BaseResponse<BeeRole>();
        responseBean.setRows(list);
        BeeRole.setLimit(null);
        BeeRole.setOffset(null);
        list = beeRoleDao.selectListByCondition(BeeRole);
        responseBean.setTotal(list.size());

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)  
    @ResponseBody  
    public String edit(@RequestBody BeeRole requestBean,HttpServletRequest request){ 
    	BeeRole BeeRole = requestBean;
    	BeeRole.setUpdateTime(new Date());
    	int n = beeRoleDao.updateByPrimaryKeySelective(BeeRole);
    	ResultBean result = new ResultBean();
    	if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    	}else{
    		result.setRspCode(SystemCode.FAIL_CODE);
    		result.setRspMsg(SystemCode.FAIL_MSG);
    	}
        return objectToJson(result);  
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)  
    @ResponseBody  
    public String add(@RequestBody BeeRole requestBean,HttpServletRequest request){ 
    	BeeRole BeeRole = new BeeRole();
        BeeRole = requestBean;
        String maxMenuId = beeRoleDao.selectMaxId()==null?"ROLE000":beeRoleDao.selectMaxId();
        Integer num = Integer.valueOf(maxMenuId.replaceAll("ROLE", ""));
        String roleId = "BEE" + String.format("%03d", num+1);
        BeeRole.setId(roleId);
        BeeRole.setCreateTime(DateUtil.getNowDate());
        BeeRole.setUpdateTime(DateUtil.getNowDate());
        int n = beeRoleDao.insert(BeeRole);
    	ResultBean result = new ResultBean();
        if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    	}else{
    		result.setRspCode(SystemCode.FAIL_CODE);
    		result.setRspMsg(SystemCode.FAIL_MSG);
    	}
        return objectToJson(result);
    } 
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)  
    @ResponseBody  
    public String delete(@RequestBody BeeRole requestBean,HttpServletRequest request){ 
    	BeeRole BeeRole = requestBean;
    	int n = beeRoleDao.deleteByPrimaryKey(BeeRole.getId());
    	ResultBean result = new ResultBean();
    	if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    	}else{
    		result.setRspCode(SystemCode.FAIL_CODE);
    		result.setRspMsg(SystemCode.FAIL_MSG);
    	}
        return objectToJson(result);  
    } 
    
}  