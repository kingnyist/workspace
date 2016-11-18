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
import com.bee.base.dao.BeeDataDicMapper;
import com.bee.base.model.BeeDataDic;
import com.bee.common.util.DateUtil;
import com.bee.common.util.ToolUtil;
import com.bee.common.variables.SystemCode;
  
@Controller  
@RequestMapping("/data")  
public class DataDicController extends BaseController{
	private Logger log = Logger.getLogger(DataDicController.class);
	
    @Resource  
    private BeeDataDicMapper BeeDataDicDao;  
     
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)  
    @ResponseBody  
    public String queryAll(@RequestBody BeeDataDic requestBean){
    	log.info("通过数据类型查询数据字典信息");
    	String dataTypes = requestBean.getDataType();
        BaseResponse<BeeDataDic> responseBean = new BaseResponse<BeeDataDic>();
    	if(dataTypes != null && dataTypes.length() > 0){
    		String[] array = dataTypes.split(",");
            List<BeeDataDic> list = BeeDataDicDao.selectListByDateTypes(array);
            responseBean.setRows(list);
    	}

        return objectToJson(responseBean);  
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)  
    @ResponseBody  
    public String selectList(@RequestBody BeeDataDic requestBean,HttpServletRequest request){
    	log.info("查询列表");
    	BeeDataDic dataDic = requestBean;
        List<BeeDataDic> list = BeeDataDicDao.selectListByCondition(dataDic);
        BaseResponse<BeeDataDic> responseBean = new BaseResponse<BeeDataDic>();
        responseBean.setRows(list);
        dataDic.setLimit(null);
        dataDic.setOffset(null);
        list = BeeDataDicDao.selectListByCondition(dataDic);
        responseBean.setTotal(list.size());

        return objectToJson(responseBean);  
    } 
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)  
    @ResponseBody  
    public String edit(@RequestBody BeeDataDic requestBean,HttpServletRequest request){ 
    	BeeDataDic dataDic = requestBean;
    	dataDic.setUpdateTime(new Date());
    	int n = BeeDataDicDao.updateByPrimaryKeySelective(dataDic);
    	ResultBean result = new ResultBean();
    	if(n == 1){
    		result.setRspCode(SystemCode.SUCCESS_CODE);
    		result.setRspMsg(SystemCode.SUCCESS_MSG);
    	}
        return objectToJson(result);  
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)  
    @ResponseBody  
    public String add(@RequestBody BeeDataDic requestBean,HttpServletRequest request){ 
    	BeeDataDic dataDic = new BeeDataDic();
        dataDic = requestBean;
        dataDic.setId(ToolUtil.getUUID());
        dataDic.setCreateTime(DateUtil.getNowDate());
        dataDic.setUpdateTime(DateUtil.getNowDate());
        int n = BeeDataDicDao.insert(dataDic);
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
    public String delete(@RequestBody BeeDataDic requestBean,HttpServletRequest request){ 
    	BeeDataDic dataDic = requestBean;
    	int n = BeeDataDicDao.deleteByPrimaryKey(dataDic.getId());
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