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
import com.bee.base.dao.BeeMenuMapper;
import com.bee.base.model.BeeMenu;
import com.bee.common.util.DateUtil;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/menu")  
public class MenuController extends BaseController{
	private Logger log = Logger.getLogger(MenuController.class);
	
    @Resource  
    private BeeMenuMapper beeMenuDao;  
    
    @RequestMapping(value = "/menuShow", method = RequestMethod.POST)  
    @ResponseBody  
    public String menuShow(@RequestBody BeeMenu requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeMenu beeMenu = requestBean;
        beeMenu.setLimit(null);
        beeMenu.setOffset(null);
        List<BeeMenu> list = beeMenuDao.selectListByCondition(beeMenu);
        BaseResponse<BeeMenu> responseBean = new BaseResponse<BeeMenu>();
        responseBean.setRows(list);

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)  
    @ResponseBody  
    public String selectList(@RequestBody BeeMenu requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeMenu beeMenu = requestBean;
        List<BeeMenu> list = beeMenuDao.selectListByCondition(beeMenu);
        BaseResponse<BeeMenu> responseBean = new BaseResponse<BeeMenu>();
        responseBean.setRows(list);
        beeMenu.setLimit(null);
        beeMenu.setOffset(null);
        list = beeMenuDao.selectListByCondition(beeMenu);
        responseBean.setTotal(list.size());

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)  
    @ResponseBody  
    public String edit(@RequestBody BeeMenu requestBean,HttpServletRequest request){ 
    	BeeMenu beeMenu = requestBean;
    	beeMenu.setUpdateTime(new Date());
    	int n = beeMenuDao.updateByPrimaryKeySelective(beeMenu);
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
    public String add(@RequestBody BeeMenu requestBean,HttpServletRequest request){ 
    	BeeMenu beeMenu = new BeeMenu();
        beeMenu = requestBean;
        String maxMenuId = beeMenuDao.selectMaxUserNo()==null?"BEE0000":beeMenuDao.selectMaxUserNo();
        Integer num = Integer.valueOf(maxMenuId.replaceAll("BEE", ""));
        String menuId = "BEE" + String.format("%04d", num+1);
        beeMenu.setMenuId(menuId);
        beeMenu.setCreateTime(DateUtil.getNowDate());
        beeMenu.setUpdateTime(DateUtil.getNowDate());
        int n = beeMenuDao.insert(beeMenu);
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
    public String delete(@RequestBody BeeMenu requestBean,HttpServletRequest request){ 
    	BeeMenu beeMenu = requestBean;
    	int n = beeMenuDao.deleteByPrimaryKey(beeMenu.getMenuId());
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