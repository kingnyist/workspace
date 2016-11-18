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
import com.bee.base.dao.BeeUserMapper;
import com.bee.base.model.BeeUser;
import com.bee.common.util.DateUtil;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/user")  
public class UserController extends BaseController{
	private Logger log = Logger.getLogger(UserController.class);
	
    @Resource  
    private BeeUserMapper beeUserDao;  
      
    @RequestMapping(value = "/list", method = RequestMethod.POST)  
    @ResponseBody  
    public String selectList(@RequestBody BeeUser requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeUser user = requestBean;
        List<BeeUser> list = beeUserDao.selectListByCondition(user);
        BaseResponse<BeeUser> responseBean = new BaseResponse<BeeUser>();
        responseBean.setRows(list);
        user.setLimit(null);
        user.setOffset(null);
        list = beeUserDao.selectListByCondition(user);
        responseBean.setTotal(list.size());

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)  
    @ResponseBody  
    public String edit(@RequestBody BeeUser requestBean,HttpServletRequest request){ 
    	BeeUser user = requestBean;
    	user.setUpdateTime(new Date());
    	int n = beeUserDao.updateByPrimaryKeySelective(user);
    	ResultBean result = new ResultBean();
    	if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    	}
        return objectToJson(result);  
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)  
    @ResponseBody  
    public String add(@RequestBody BeeUser requestBean,HttpServletRequest request){ 
    	BeeUser user = new BeeUser();
        String maxUserNo = beeUserDao.selectMaxUserNo()==null?"BEE0000":beeUserDao.selectMaxUserNo();
        Integer num = Integer.valueOf(maxUserNo.replaceAll("BEE", ""));
        String userNo = "BEE" + String.format("%04d", num+1);
        user = requestBean;
        user.setUserNo(userNo);
        user.setCreateTime(DateUtil.getNowDate());
        user.setUpdateTime(DateUtil.getNowDate());
        int n = beeUserDao.insert(user);
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
    public String delete(@RequestBody BeeUser requestBean,HttpServletRequest request){ 
    	System.out.println(requestBean.toString());
    	BeeUser user = requestBean;
    	int n = beeUserDao.deleteByPrimaryKey(user.getUserNo());
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