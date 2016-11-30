package com.bee.base.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bee.base.bean.ResultBean;
import com.bee.base.dao.BeeUserMapper;
import com.bee.base.model.BeeUser;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/login")  
public class LoginController extends BaseController{
	private Logger log = Logger.getLogger(LoginController.class);
	
    @Resource  
    private BeeUserMapper beeUserDao;  
      
    @RequestMapping(value = "/login", method = RequestMethod.POST)  
    @ResponseBody  
    public String login(@RequestBody BeeUser requestBean,HttpServletRequest request){ 
    	BeeUser user = requestBean;
        user.setLimit(null);
        user.setOffset(null);
        List<BeeUser> list = beeUserDao.selectListByCondition(user);
        int n = list.size();
    	ResultBean result = new ResultBean();
    	if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    		BeeUser bean = list.get(0);
    		HttpSession session = request.getSession();
    		session.setAttribute("bee", bean);
    	}else{
    		result.setRspCode(SystemCode.LOGIN_FAIL_CODE);
    		result.setRspMsg(SystemCode.LOGIN_FAIL_MSG);
    	}
        return objectToJson(result);  
    }
    
}  