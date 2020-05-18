var validateRegExp = { 
    decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    intege: "^-?[1-9]\\d*$", //整数
    intege1: "^[1-9]\\d*$", //正整数
    intege2: "^-[1-9]\\d*$", //负整数
    num0: "^([+]?)\\d*\\.?\\d+$", //正数字
    num: "^([+-]?)\\d*\\.?\\d+$", //数字
    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
    color: "^[a-fA-F0-9]{6}$", //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letter_l: "^[a-z]+$", //小写字母
    letter_u: "^[A-Z]+$", //大写字母 
    mobile: "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", //手机
    notempty: "^\\S+$", //非空
    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode: "^\\d{6}$", //邮编
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};


//验证规则,事例：
var validateRules = {
    isNull: function (str) { 
    	//alert(new RegExp(validateRegExp.isNull).test(str)); 
        return (typeof str != "string" || (str.replace(/^\s+,""/).replace(/^\s+$/,"")==''));
    },
    betweenLength: function (str, _min, _max) {
        return (str.length >= _min && str.length <= _max);
    },
    isNum0: function (str) {  
        return new RegExp(validateRegExp.num0).test(str);
    },
    isNum:function(str){
        return new RegExp(validateRegExp.num).test(str);
    },
    isInteger1:function(str){
    	return new RegExp(validateRegExp.intege1).test(str);
    },
    isUid: function (str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    fullNumberName: function (str) {
        return new RegExp(validateRegExp.fullNumber).test(str);
    },
    isPwd: function (str) {
        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
    },
    isPwdRepeat: function (str1, str2) {
        return (str1 == str2);
    },
    isEmail: function (str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel: function (str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile: function (str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    checkType: function (element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isRealName: function (str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isCompanyname: function (str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr: function (str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite: function (str) {
        return new RegExp(validateRegExp.companysite).test(str);
    },
    
    simplePwd: function (str) {
//        var pin = $("#regName").val();
//        if (pin.length > 0) {
//            pin = strTrim(pin);
//            if (pin == str) {
//                return true;
//            }
//        }
        return pwdLevel(str) == 1;
    },
    weakPwd: function (str) {
        for(var i = 0; i < weakPwdArray.length; i++) {
            if(weakPwdArray[i] == str) {
                return true;
            }
        }
        return false;
    }
};
function two_bit_decimal(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
}
//密码验证（数字、大写字母、小写字母及特殊字符，4选2，8-20位字符）
function isPassword(target) {
    var rC = {
        lW: '[a-z]',
        uW: '[A-Z]',
        nW: '[0-9]',
        sW: '[\\u0020-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E]'
    };

    function Reg(str, rStr) {
        var reg = new RegExp(rStr);
        if (reg.test(str)) return true;
        else return false;
    }

    var tR = {
        l: Reg(target, rC.lW),
        u: Reg(target, rC.uW),
        n: Reg(target, rC.nW),
        s: Reg(target, rC.sW)
    };

    if ((tR.l && tR.n) || (tR.l && tR.s) || (tR.u && tR.n) || (tR.u && tR.s) || (tR.n && tR.s)) {
        return true;
    } else {
        return false;
    }
}

function checkStrong(sPW) {
    if (sPW == null || sPW.length < 6) {
        return 0; //密码太短  
    }
    var Modes = 0;
    for (var i = 0; i < sPW.length; i++) {
        //测试每一个字符的类别并统计一共有多少种模式.  
        Modes |= CharMode(sPW.charCodeAt(i));
    }
    return bitTotal(Modes);
}
//bitTotal函数  
//计算出当前密码当中一共有多少种模式  
function bitTotal(num) {
    var modes = 0;
    for (var i = 0; i < 4; i++) {
        if (num & 1) {
            modes++;
        }
        num >>>= 1;
    }
    return modes;
}

function CharMode(iN) {
    if (iN >= 48 && iN <= 57) { //数字  
        return 1;
    }
    if (iN >= 65 && iN <= 90) { //大写字母  
        return 2;
    }
    if (iN >= 97 && iN <= 122) { //小写  
        return 4;
    } else {
        return 8; //特殊字符  
    }
}