
//保留两位小数
function two_bit_decimal(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
}

var validateRegExp = {
    money:"^\d+(\.\d{2})?$"
};
    
//验证规则,事例：
var validateRules = {
    isMoney: function (str) {
        //alert(str);
        return /^(([1-9]\d*)|\d)(\.\d{1,2})?$/.test(str);
    }
};

/**
 * window.open模拟表单提交
 * @param url 为请求地址
 * @param name为form表单的target的name(可以随意写)
 * @param data1为需要请求的数据
 */
function openPostWindow(url, name, dataJson){

    var tempForm = document.createElement("form");//创建form表单，以下数form表单的各种参数
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = url;
    tempForm.target=name;

    for(var p in dataJson){//遍历json对象的每个key/value对,p为key
	    //alert(p + " " + );

	    var hideInput = document.createElement("input");//创建标签 <input></input> 标签 然后设定属性，最后追加为 form标签的子标签
	    hideInput.type = "hidden";
	    hideInput.name = p;
	    hideInput.value = dataJson[p];
	    
	    tempForm.appendChild(hideInput);
    }
   
    if(document.all){
        tempForm.attachEvent("onsubmit",function(){});//IE
    }else{
        var subObj = tempForm.addEventListener("submit",function(){},false);//firefox
    }
    
    document.body.appendChild(tempForm);
    if(document.all){
        tempForm.fireEvent("onsubmit");
    }else{
        tempForm.dispatchEvent(new Event("submit"));
    }
   
    tempForm.submit();//提交POST请求
    document.body.removeChild(tempForm);//删除整个form标签
}