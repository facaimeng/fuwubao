/***********************************************************************
 *                           日期时间工具类                            *
 *                     注：调用方式，deteUtil.方法名                   *
 * ********************************************************************/

var dateUtil = {
	
	/**
	 * 方法作用：【日期和月份小于10时前方补0】
	 * 传入格式：整数数字
	 */
	formatMonthDayNumber:function (value){    
        return (value < 10 ? '0' : '') + value;    
    },
    
    /*
     * 方法作用：【计算2个日期之间的天数】
     * 传入格式：yyyy-mm-dd(2015-01-31)
     * 使用方法：dateUtil.dayMinus(startDate,endDate);
     * @startDate {Date}起始日期
     * @endDate {Date}结束日期
     * @return endDate - startDate的天数差
     */
    dayMinus:function(startDate, endDate){
        if(startDate instanceof Date && endDate instanceof Date){
            var days = Math.floor((endDate-startDate)/(1000 * 60 * 60 * 24));
            return days;
        }else{
            return "Param error,date type!";
        }
    },
    dayBetween:function(startTime, endTime){
    	var startTime =new Date(startTime.replace("//-/g", "//"));  
	    var endTime = new Date(endTime.replace("//-/g", "//"));  
        var days = Math.floor((endTime.getTime()-startTime.getTime())/(1000 * 60 * 60 * 24));
        return days;
    },
    
    /**
	 * 方法作用：【获取下N个月的输入日期】
	 * 传入格式：日期(YYYY-MM-DD)字符串
	 * 传入格式：monthNum月数正整数
	 */
	nextMonthDay: function (date, monthNum){
		var dateArr = date.split('-');
		var year = dateArr[0]; //获取当前日期的年份
		var month = dateArr[1]; //获取当前日期的月份
	 	var day = dateArr[2]; //获取当前日期的日
	 	
	 	var days = new Date(year, month, 0);
	 	    days = days.getDate(); //获取当前日期中的月的天数
		var year2 = year;
		var month2 = parseInt(month) + parseInt(monthNum);
		if (month2 >12) {
			year2 = parseInt(year2) + parseInt((parseInt(month2) / 12 == 0 ? 1 : parseInt(month2) / 12));
			month2 = parseInt(month2) % 12;
		}
		var day2 = day;
		var days2 = new Date(year2, month2, 0);
			days2 = days2.getDate();
		if (day2 > days2) {
			day2 = days2;
		}
		
		var t2 = year2 + '-' + this.formatMonthDayNumber(month2) + '-' + this.formatMonthDayNumber(day2);
		return t2;
	},
	
	/**
	 * 方法作用：【获取几个月前的输入日期】
	 * 传入格式：日期(YYYY-MM-DD)
	 * 传入格式：monthNum月数正整数
	 */
	preMonthDay: function (date,monthNum){
		var dateArr = date.split('-');
		var year = dateArr[0]; //获取当前日期的年份
		var month = dateArr[1]; //获取当前日期的月份
		var day = dateArr[2]; //获取当前日期的日
		var days = new Date(year, month, 0);
			days = days.getDate(); //获取当前日期中月的天数
		var year2 = year;
		var month2 = parseInt(month) - monthNum;
		if (month2 <=0) {
 			year2 = parseInt(year2) - parseInt(month2 / 12 == 0 ? 1 : parseInt(month2) / 12);
			month2 = 12 - (Math.abs(month2) % 12);
		}
		var day2 = day;
		var days2 = new Date(year2, month2, 0);
			days2 = days2.getDate();
		if (day2 > days2) {
 			day2 = days2;
		}

  		var t2 = year2 + '-' + this.formatMonthDayNumber(month2) + '-' + this.formatMonthDayNumber(day2);
  		return t2;
	},
    
	nextDay:function(date,dayNum,timeUnit){
	    
	    var dateArr = date.split('-');
		
		var year = dateArr[0]; //获取当前日期的年份
		var month = dateArr[1]; //获取当前日期的月份
		var day = dateArr[2]; //获取当前日期的日
	    var start_date = new Date(year, month-1, day);
	    
	    if (timeUnit === 'd') {  
	    
	        //var r_date = start_date.getDate() + parseInt(dayNum);
	        //alert("计算结果：" + r_date +"==" +k_date);
	        start_date.setDate(start_date.getDate() + parseInt(dayNum)); 
	         
	    } else if (timeUnit === 'M') {  
	        start_date.setMonth(start_date.getMonth() + parseInt(dayNum));  
	    } else if (timeUnit === 'y') {  
	        start_date.setFullYear(start_date.getFullYear() + parseInt(dayNum));  
	    }
	    
	    var strYear = start_date.getFullYear();  
	    var strMonth = parseInt(start_date.getMonth())+1;  
	    var strDay = start_date.getDate();  
	    	    
	    //alert("end_date"+strYear+"-" + strMonth + "-" + strDay);

	    //var strHours = start_date.getHours();
	    //var strMinutes = start_date.getMinutes();
	    var datastr = strYear + '-' + this.formatMonthDayNumber(strMonth) + '-' + this.formatMonthDayNumber(strDay);
	    //+' '+ formatNumber(strHours) + ':' + formatNumber(strMinutes);  
	
	    return datastr;
	},
	
	/** 
     * @描述：获取当前时间毫秒数 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    getCurrentMsTime : function() {  
        var myDate = new Date();  
        return myDate.getTime();  
    },  
  
    /** 
     * @描述：转换日期格式yyyy-MM-dd HH:mm:ss 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    dateTimeConvertToDateTime : function(dateTime) {  
        return this.formatterDateTime(dateTime);  
    },
      
    /** 
     * @描述：日期格式的字符串转换成日期 
     * @传入参数形式 yyyy-MM-dd HH:mm:ss 或者 yyyy-MM-dd
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */ 
    dateStrConvertToDate: function(strDate){
		var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/, function (a) {return parseInt(a, 10) - 1;}).match(/\d+/g) + ')');
		return date;
    },
    
    /** 
     * @描述：日期格式的字符串转换成日期 
     * @传入参数形式  yyyyMMddHHmmss
     * @创建人： 
     * @创建时间：
     */ 
    dateStryyyyMMddToyyyy_MM_dd : function(strDate){
    
        var formatedDate = strDate.replace(/^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/, '$1-$2-$3 $4:$5:$6');
        
        return this.dateStrConvertToDate(formatedDate);
    },
    
    /** 
     * @描述：时间格式转毫秒 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    dateToLongMsTime : function(date) {  
        return date.getTime();  
    },  
    
  
    /** 
     * @描述：格式化日期（不含时间） 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    formatterDate : function(date) {  
        
        var datetime = new Date(date);  
        
        var result = datetime.getFullYear()  
                + "-"// "年"  
                + ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1) : "0"  
                        + (datetime.getMonth() + 1))  
                + "-"// "月"  
                + (datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime  
                        .getDate());  
        //alert('-->'+result);                
        return result;  
    },  
      
    /** 
     * @描述：格式化日期（含时间"00:00:00"） 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    formatterDate2 : function(date) {  
        var datetime = new Date(date);  
        var result = datetime.getFullYear()  
                + "-"// "年"  
                + ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1) : "0"  
                        + (datetime.getMonth() + 1))  
                + "-"// "月"  
                + (datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime  
                        .getDate()) + " " + "00:00:00";  
        return result;  
    },  
     
    /** 
     * @描述：格式化去日期（含时间） 
     * @创建人：millery 
     * @创建时间：2015年12月23日 
     */  
    formatterDateTime : function(date) {  
        var datetime = new Date(date);  
        var result = datetime.getFullYear()  
                + "-"// "年"  
                + ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1) : "0" + (datetime.getMonth() + 1))  
                + "-"// "月"  
                + (datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate())  
                + " "  
                + (datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours())  
                + ":"  
                + (datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes())  
                + ":"  
                + (datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds());  
        return result;  
    }

};