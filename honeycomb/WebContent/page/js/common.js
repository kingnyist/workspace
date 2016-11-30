$(function () {
	//页面上 弹出dialog右上角的关闭按钮事件绑定
	$(".close").bind("click", function(){$.beeDialog.closeWindow();});
	//页面查询按钮事件绑定
	if($('#btn-query')){
		$('#btn-query').bind('click', function() { 
			table.bootstrapTable('refresh');
		});
	}
	if($('#btn-view')){
		$('#btn-view').bind('click', function(){
	    	$.beeDialog.createDialog("view");
		});
	}
	if($('#btn-add')){
		$('#btn-add').bind('click', function(){
	    	$.beeDialog.createDialog("add");
		});
	}
	if($('#btn-edit')){
		$('#btn-edit').bind('click', function(){
	    	$.beeDialog.createDialog("edit");
		});
	}
	if($('#btn-delete')){
		$('#btn-delete').bind('click', function(){
			deleteRow();
		});
	}
});

//构建编辑框对象
$.beeDialog = {
		columnNum : 2,
		//基于bootstrap的表单控件的模板
		formGroupText : "<div class=\"form-group padding-right\">{files}</div>",
		//文本框
		text : "<label for=\"{id}\" class=\"col-sm-{two} control-label\">{fileName}</label>"
				    +"<div class=\"col-sm-{four}\">"
				    +"<input type=\"text\" class=\"form-control\" id=\"{id}\" value=\"{value}\" {readonly}>"
				    +"</div>",
	    //下拉列表
		select : "<label for=\"{id}\" class=\"col-sm-{two} control-label\">{fileName}</label>"
					+"<div class=\"col-sm-{four}\">"
					+"<select class=\"form-control\" id=\"{id}\">{option}</select>"
					+"</div>",
		//文本区
		textarea : "<label for=\"{id}\" class=\"col-sm-{two} control-label\">{fileName}</label>"
				    +"<div class=\"col-sm-{ten}\">"
				    +"<textarea class=\"form-control\" rows=\"2\" id=\"{id}\" {readonly}>{value}</textarea>"
				    +"</div>",
		//占位标签
		placeholder : "<div class=\"col-sm-6\"></div>",
		//返回按钮
		butBack : "　<button type=\"button\" id=\"dialog-but-back\" class=\"btn btn-info\">返回</button>　",
		//添加的提交按钮
		butAdd : "　<button type=\"button\" id=\"dialog-but-add\" class=\"btn btn-info\">确定</button>　",
		//修改的提交按钮
		butEdit : "　<button type=\"button\" id=\"dialog-but-edit\" class=\"btn btn-info\">确定</button>　",
		//查看页面的确认按钮
		butSure : "　<button type=\"button\" id=\"dialog-but-sure\" class=\"btn btn-info\">确定</button>　",
		//授权页面的授权按钮
		butAuthorize : "　<button type=\"button\" id=\"dialog-but-authorize\" class=\"btn btn-info\">授权</button>　",
		
		createDialogBefore : function(type){
			var rowData = table.bootstrapTable('getSelections');
			if(type == "view" && rowData.length == 0){
				return false;
			}else if(type == "edit" && rowData.length == 0){
				return false;
			}else{
		    	$("#view-dialog").removeClass("view-dialog-hidden");
		    	//$("body").css("overflow","hidden");
		    	$("#view-dialog").addClass("view-dialog-show");
		    	return true;
			}
		},
		
		//创建编辑框展示内容
		createDialog : function(type){
			if(!$.beeDialog.createDialogBefore(type)){
				$.beeAlert.open("9999", "请选择记录");
				return;
			}
			if(typeof(columnNum)!="undefined"){
				$.beeDialog.columnNum = columnNum;
			}
			//对应记录内容 添加时为空
			var rowData = table.bootstrapTable('getSelections');
			var readonly = "";
			if(type == "view"){
				readonly = "readonly=\"readonly\"";
			}
			var htmltext = "";
			var formGroupTextTemp = $.beeDialog.formGroupText;
			var filetemp ="";
			var n = 0;
			for (var i=0;i<header.length;i++) {  
				var value = header[i];
		        if(value.field!="state"){
		        	var temp = "";
		        	if(value.editType == "text" || !value.editType){
		        		temp = $.beeDialog.text;
		            	temp = temp.replace(new RegExp(/({id})/g), value.field);
		            	temp = temp.replace("{fileName}", value.title);
		            	if(type != "add"){
			            	var showValue = eval("rowData[0]." + value.field);
			            	if(showValue == null){
			            		showValue = "";
			            	}
			            	if(value.field == key && type == "edit"){
		            			readonly = "readonly=\"readonly\"";
		            			temp = temp.replace("{value}", showValue);
		            		}
			            	temp = temp.replace("{value}", showValue);
		            	}else{
		            		temp = temp.replace("{value}", "");
		            	}
		            	temp = temp.replace("{readonly}", readonly);
		            	readonly = "";
		            	if(eval("value."+type)){
			            	filetemp = filetemp + temp;
			            	n = n + 1;
		            	}
		        	}else if(value.editType == "select"){
		            	if((type == "add" || type == "edit") && value.field != key){
			        		temp = $.beeDialog.select;
			            	temp = temp.replace(new RegExp(/({id})/g), value.field);
			            	temp = temp.replace("{fileName}", value.title);
			            	var option = eval(value.field+"_option");
			            	if(type == "edit"){
				            	var showValue = eval("rowData[0]." + value.field);
				            	option = option.replace("value='"+showValue+"'", "value='"+showValue+"' selected");
			            	}
			            	temp = temp.replace(new RegExp(/({option})/g), option);
		            	}else{
		            		temp = $.beeDialog.text;
			            	temp = temp.replace(new RegExp(/({id})/g), value.field);
			            	temp = temp.replace("{fileName}", value.title);
			            	readonly = "readonly=\"readonly\"";
			            	var showValue = eval("rowData[0]." + value.field);
			            	if(showValue == null){
			            		showValue = "";
			            	}
			            	//映射出格式化函数 并执行
			            	var formater = eval(value.formatter)
			            	showValue = formater(showValue);
	            			temp = temp.replace("{value}", showValue);
		            	}
		            	readonly = "";
		            	if(eval("value."+type)){
			            	filetemp = filetemp + temp;
			            	n = n + 1;
		            	}
		        	}else if(value.editType == "textarea"){
		        		temp = $.beeDialog.textarea;
		        		if(n==0){
		                	temp = temp.replace(new RegExp(/({id})/g), value.field);
		                	temp = temp.replace("{fileName}", value.title);
		                	temp = temp.replace("{readonly}", readonly);
		                	if(type != "add"){
				            	var showValue = eval("rowData[0]." + value.field);
				            	if(showValue == null){
				            		showValue = "";
				            	}
				            	temp = temp.replace("{value}", showValue);
			            	}else{
			            		temp = temp.replace("{value}", "");
			            	}
		                	filetemp = temp;
		            		n = n + $.beeDialog.columnNum;
		        		}else{
		        			if($.beeDialog.columnNum==2){
			        			filetemp = filetemp + $.beeDialog.placeholder;
		        			}
		        			i = i - 1;
		            		n = n + 1;
		        		}
		        	}
		        	
		        }
		        if(n==$.beeDialog.columnNum){
		        	htmltext = htmltext + formGroupTextTemp.replace("{files}", filetemp);
		        	formGroupTextTemp = $.beeDialog.formGroupText;
		        	filetemp ="";
		        	n = 0;
		        }else if(header.length == (i+1)){
		        	htmltext = htmltext + formGroupTextTemp.replace("{files}", filetemp);
		        }
		    };
		    var x = 1;
		    if($.beeDialog.columnNum==1){
		    	x = 2;
		    }else if($.beeDialog.columnNum==2){
		    	x = 1;
		    }
		    htmltext = htmltext.replace(new RegExp(/({two})/g), 2*x+"");
		    htmltext = htmltext.replace(new RegExp(/({four})/g), 4*x+"");
		    htmltext = htmltext.replace(new RegExp(/({ten})/g), 12-2*x+"");
		    $("#form-horizontal").empty();
		    $("#form-horizontal").append(htmltext);
		    $.beeDialog.setDialogBut(type);
		},
		
		//用于展示菜单授权的对话框
		createAuthDialog : function(){
			var rowData = table.bootstrapTable('getSelections');
			if(rowData.length == 0){
				$.beeAlert.open("9999", "请选择记录");
				return;
			}
			
			var url = '/honeycomb/authorize/list.htm?rnd=' + Math.random();
    	    beeCallAjaxQuery($.beeDialog.createTreeChecked, url, {"roleNo":rowData[0].id}, false);
			
			$.fn.zTree.init($("#authTree"), $.chkbokTree.setting, $.chkbokTree.zNodes);
			$.chkbokTree.setCheck();
			$("#py").bind("change", $.chkbokTree.setCheck);
			$("#sy").bind("change", $.chkbokTree.setCheck);
			$("#pn").bind("change", $.chkbokTree.setCheck);
			$("#sn").bind("change", $.chkbokTree.setCheck);
		    $.beeDialog.setDialogBut("authorize");
			$("#view-dialog").removeClass("view-dialog-hidden");
	    	$("#view-dialog").addClass("view-dialog-show");
		},
		
		//设置权限树回显内容
		createTreeChecked : function(data){
			for(var n = 0; n < $.chkbokTree.zNodes.length; n++){
				var nodeId = $.chkbokTree.zNodes[n].id;
				var isChecked = false;
				for(var m = 0; m < data.length; m++){
					var menuNo = data[m].menuNo;
					if(menuNo == nodeId){
						isChecked = true;
					}
				}
				if(isChecked){$.chkbokTree.zNodes[n].checked = true;}
				else{$.chkbokTree.zNodes[n].checked = false;}
			}
		},
		
		setDialogBut : function(type){
			$("#dialog-but-area").empty();
			$("#dialog-but-area").append($.beeDialog.butBack);
			$("#dialog-but-back").bind("click", function(){$.beeDialog.closeWindow();});
			if(type == "add"){
				$("#dialog-but-area").append($.beeDialog.butAdd);
				$("#dialog-but-add").bind("click", function(){add();});
			}else if(type == "edit"){
				$("#dialog-but-area").append($.beeDialog.butEdit);
				$("#dialog-but-edit").bind("click", function(){edit();});
			}else if(type == "view"){
				$("#dialog-but-area").append($.beeDialog.butSure);
				$("#dialog-but-sure").bind("click", function(){$.beeDialog.closeWindow();});
			}else if(type == "authorize"){
				$("#dialog-but-area").append($.beeDialog.butAuthorize);
				$("#dialog-but-authorize").bind("click", function(){authorize();});
			}
		},

		closeWindow : function(){
			//恢复页面滚动
			$("body").css("overflow","auto");
			//清除已经写入的内容
			if($("#form-horizontal")){
				$("#form-horizontal").empty();
			}
			if($("#authTree")){
				$("#authTree").empty();
			}
			if($("#dialog-but-area")){
				$("#dialog-but-area").empty();
			}
			
			//去掉显示样式
			$("#view-dialog").removeClass("view-dialog-show");
			//添加隐藏样式
			$("#view-dialog").addClass("view-dialog-hidden");
		},
		
		verify : function(){
			var result = true;
			for(n in header){
				var value = header[n];
				if(value.file != "state" && (value.verify || value.verify == false)){
					var textValue = $("#"+value.field).val();
					if(value.editType == "textarea"){
						textValue=$("#"+value.field).text();
					}
					if(value.verify.indexOf("string")>=0){
						var array = value.verify.split(":");
						
						if(array[1] && value.need){
							if(textValue.length>array[1]){
								$.beeAlert.open("9999", value.title+"输入长度超过"+array[1]+"位！");
								result =  false;
								break;
							}
						}
						if(array[2] && value.need){
							if(textValue.length<array[2]){
								$.beeAlert.open("9999", value.title+"输入长度小于"+array[2]+"位！");
								result =  false;
								break;
							}
						}
						
					}else if(value.verify.indexOf("cellphone")>=0){
						var cellphoneReg = /^[0-9]{11}$/;
						if (!cellphoneReg.test(textValue)) {
							$.beeAlert.open("9999", "手机号格式不正确！");
							result =  false;
							break;
						}
					}else if(value.verify.indexOf("email")>=0){
						var emailReg = /[a-z0-9]*@[a-z0-9]*\.[a-z0-9]+/gi;
						if (!emailReg.test(textValue)) {
							$.beeAlert.open("9999", "电子信箱格式不对！");
							result =  false;
							break;
						}
					}else if(value.verify.indexOf("idcard")>=0){
						var idcardReg = /^[0-9]{17}[0-9X]{1}$|^[0-9]{14}[0-9X]{1}$/;
						if (!idcardReg.test(textValue)) {
							$.beeAlert.open("9999", "身份证号码输入格式错误！");
							result =  false;
							break;
						}
					}else if(value.verify.indexOf("age")>=0){
						var ageReg = /^[1-9][0-9]{2}$/;
						if (!ageReg.test(textValue)) {
							$.beeAlert.open("9999", "年龄输入格式错误！");
							result =  false;
							break;
						}
					}else if(value.verify.indexOf("number")>=0){
						var numberReg = /^[1-9][0-9]{2}$/;
						if (!numberReg.test(textValue)) {
							$.beeAlert.open("9999", value.title+"输入格式错误！");
							result =  false;
							break;
						}
					}
				}
			}		
			return result;
		}
};

//初始化页面数据表
function initTable(queryUrl) {
	$table = table.bootstrapTable({
		method: 'post',
        url: queryUrl,         //请求后台的URL（*）
        classes: 'table table-striped',
        toolbar: '#toolbar',                //工具按钮用哪个容器
        toolbarAlign: "left",
        singleSelect: true,                 //禁止多选
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: queryParams,           //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        strictSearch: false,
        clickToSelect: true,                //是否启用点击选中行
        height: 440,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: key,                     //每一行的唯一标识，一般为主键列
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: header,
        onLoadSuccess:function(){
        },
        onLoadError: function () {
        }
	});
}

$.beeAjaxParam = {
		requestParam : {"userNo": sessionStorage.getItem("userNo")},
		requestUrl : "",
		async : true,
		successCallBack : function(result){
			$.beeAlert.open(result.rspCode, result.rspMsg);
			if(result.rspCode == "0000"){
				$.beeDialog.closeWindow();
			}
			$table.bootstrapTable('refresh', {url: queryUrl}); 
		} ,
		errorCallBack : function(msg){
			$.beeAlert.open("9999", msg);
		} 
}
//用于增删改查
function beeCallAjax() {
	$.ajax({
		url : $.beeAjaxParam.requestUrl,
		type : 'POST',
		async : true,
		dataType : "json",
		contentType: "application/json",
		data : JSON.stringify($.beeAjaxParam.requestParam),
		cache : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$.beeAjaxParam.successCallBack(result);
		},
		error : function() {
			$.beeAjaxParam.errorCallBack("请求失败");
		}
	});
}

$.beeAlert = {
	open : function(type, content){
		$("body").append($.beeAlert.model.replace("{content}",content));//添加弹出框元素
		$("#alert-box").bind("click", function(){$.beeAlert.close();});//弹出框按钮上绑定关闭事件
		if(type == "9999" || type == "error"){
			$(".alert-box .box .content").css("color","red");
		}
		$("#alert-box").fadeIn(500);//展示弹出框
	}, //弹出提示
	close : function(){
		$("#alert-box").unbind("click", function(){$.beeAlert.close();});//解除按钮绑定事件
		$("#alert-box").css("display","none");//设置弹出框隐藏
		$("#alert-box").remove();//移除弹出框元素
	}, //关闭提示
	model : "<div class=\"alert-box\" id=\"alert-box\">"
			+ "<div class=\"box\">"
			+ "<div class=\"title\">提示</div>"
			+ "<div class=\"content\">{content}</div>"
			+ "<div class=\"floor\"><button type=\"button\" id=\"btn-alert\" class=\"btn btn-primary btn-xs\">确定</button></div>"
			+ "</div>" + "</div>",//弹出框模板
}

$.chkbokTree = {
	setting : {
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	},
	zNodes : [ {
		id : 1,
		pId : 0,
		name : "随意勾选 1",
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "随意勾选 1-1",
		open : true
	}, {
		id : 111,
		pId : 11,
		name : "随意勾选 1-1-1"
	}
	],

	setCheck : function() {
		var zTree = $.fn.zTree.getZTreeObj("authTree"), 
		py = $("#py").attr("checked") ? "p" : "s", 
		sy = $("#sy").attr("checked") ? "s": "p", 
		pn = $("#pn").attr("checked") ? "p" : "s", 
		sn = $("#sn").attr("checked") ? "s" : "p", 
		type = {
			"Y" : py + sy,
			"N" : pn + sn
		};
		zTree.setting.check.chkboxType = type;
		$.chkbokTree.showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "'+ type.N + '" };');
	},
	showCode : function(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>");
	}
}

//用于查询数据 回调函数必须自定义
function beeCallAjaxQuery(callBack, url, requestParam, async) {
	$.ajax({
		url : url,
		type : 'POST',
		async : async,
		dataType : "json",
		contentType: "application/json",
		data : JSON.stringify(requestParam),
		cache : false,
		success : function(data) {
			var result = $.parseJSON(data);
			callBack(result.rows);
		},
		error : function() {
			console.log("数据查询请求发送失败");
		}
	});
}
//查询数据字典的数据
function queryDateDic(dataTypes, callBack){
	var requestParam = {
			'dataType' : dataTypes
	};
	var url = '/honeycomb/data/queryAll.htm?rnd=' + Math.random();
	beeCallAjaxQuery(callBack, url, requestParam, false);
}
//格式化状态可用/不可用
function formatStatus(value){
	var returnValue = value;
	for(var n = 0; n < datalist.length; n++){
		if(value == datalist[n].dataValue && datalist[n].dataType == "USABLE"){
			returnValue = datalist[n].dataName;
			break;
		}
	}
	return returnValue;
}
//格式化是否
function formatWether(value){
	var returnValue = value;
	for(var n = 0; n < datalist.length; n++){
		if(value == datalist[n].dataValue && datalist[n].dataType == "WETHER"){
			returnValue = datalist[n].dataName;
			break;
		}
	}
	return returnValue;
}
