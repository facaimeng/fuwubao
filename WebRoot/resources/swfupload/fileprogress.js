/*
	A simple class for displaying file information and progress
	Note: This is a demonstration only and not part of SWFUpload.
	Note: Some have had problems adapting this class in IE7. It may not be suitable for your application.
*/

// Constructor
// file is a SWFUpload file object
// targetID is the HTML element id attribute that the FileProgress HTML structure will be added to.
// Instantiating a new FileProgress object with an existing file will reuse/update the existing DOM elements
function FileProgress(file, targetID) {//每加入一个文件进队列则造一个此对象
	this.targetID = targetID;
	
	this.fileProgressID = file.id;

	this.opacity = 100;
	this.height = 0;
	

	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "kuang1";//一个文件一个progressWrapper层
		this.fileProgressWrapper.id = this.fileProgressID;
	
		this.fileProgressElement = document.createElement("div");//包含文件信息的层,放在progressWrapper中
		//this.fileProgressElement.className = "progressContainer";

		var progressFile = document.createElement("div");
		progressFile.id = 'pfile_'+this.fileProgressID;
		progressFile.className = "kuang2";
		progressFile.innerHTML = '<div class="kuang1_1" id="tipimg_'+this.fileProgressID+'"></div><div class="kuang1_2 size12 heise jiacu autolen">'+file.name+'</div><div class="kuang1_3" id="op_'+this.fileProgressID+'"><a href="javascript:void(0);" style="display: none;">取消</a></div>';
        
        var progressMes = document.createElement("div");
        progressMes.id = 'pmes_'+this.fileProgressID;
		progressMes.className = "kuang2";
		progressMes.innerHTML = '<div class="kuang1_1"></div><div class="kuang1_2 size12 hongse" id="mes_'+this.fileProgressID+'">f</div>';
		progressMes.style.display = "none";
		
		var progressBar = document.createElement("div");
		progressBar.id = 'pbar_'+this.fileProgressID;
		progressBar.className = "kuang2";
		progressBar.innerHTML = '<div class="kuang1_1"></div><div class="kuang1_2"><div class="jd1"><div class="jd2" id="bar_'+this.fileProgressID+'"></div></div></div><div class="kuang1_3 size12 en" align="right" id="per_'+this.fileProgressID+'">0%</div>';
		progressBar.style.display = "none";
		
		var progressWatch = document.createElement("div");
		progressWatch.id = 'pwatch_'+this.fileProgressID;
		progressWatch.className = "kuang2";
		progressWatch.innerHTML = '<div class="kuang2"><div class="kuang1_1"></div><div class="kuang1_2 size12 huise" style="width:370px;">已上传:<span id="uped_'+this.fileProgressID+'" style="width:63px;display:inline-block;">0KB</span>&nbsp;&nbsp;速度:<span id="rate_'+this.fileProgressID+'" style="width:73px;display:inline-block;">0KB/s</span>&nbsp;剩余时间:<span id="retime_'+this.fileProgressID+'" style="width:53px;display:inline-block;">00:00:00</span></div></div>';
		progressWatch.style.display = "none";

		this.fileProgressElement.appendChild(progressFile);
		this.fileProgressElement.appendChild(progressMes);
		this.fileProgressElement.appendChild(progressBar);
		this.fileProgressElement.appendChild(progressWatch);

		this.fileProgressWrapper.appendChild(this.fileProgressElement);
		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
		
	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
		//this.reset();
	}
	this.height = this.fileProgressWrapper.offsetHeight;
	this.setTimer(null);
}

FileProgress.prototype.setTimer = function (timer) {
	this.fileProgressElement["FP_TIMER"] = timer;
};
FileProgress.prototype.getTimer = function (timer) {
	return this.fileProgressElement["FP_TIMER"] || null;
};

FileProgress.prototype.reset = function () {
	this.fileProgressElement.className = "progressContainer";

	this.fileProgressElement.childNodes[2].innerHTML = "&nbsp;";
	this.fileProgressElement.childNodes[2].className = "progressBarStatus";
	
	this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[3].style.width = "0%";
	
	this.appear();	
};

FileProgress.prototype.setProgress = function (percentage) {
	//this.appear();
	document.getElementById('bar_'+this.fileProgressID).style.width = percentage;	
	document.getElementById('per_'+this.fileProgressID).innerHTML = percentage;	
			
};
FileProgress.prototype.setWatch = function (uped,rate,retime) {
	document.getElementById('uped_'+this.fileProgressID).innerHTML = uped;	
	document.getElementById('rate_'+this.fileProgressID).innerHTML = rate;	
	document.getElementById('retime_'+this.fileProgressID).innerHTML = retime;	
	
};
FileProgress.prototype.setComplete = function () {
	//this.fileProgressElement.className = "progressContainer blue";
	//this.fileProgressElement.childNodes[3].className = "progressBarComplete";
	//this.fileProgressElement.childNodes[3].style.width = "";
	//var oSelf = this;
	//this.setTimer(setTimeout(function () {
	//	oSelf.disappear();
	//}, 10000));
	document.getElementById('tipimg_'+this.fileProgressID).innerHTML = '<img src="/resources/swfupload/up4.jpg" width="15" height="13" />';	
};
FileProgress.prototype.setError = function () {
	document.getElementById('tipimg_'+this.fileProgressID).innerHTML = '<img src="/resources/swfupload/up3.jpg" width="15" height="13" />';	
	document.getElementById('op_'+this.fileProgressID).childNodes[0].innerHTML='';
};
FileProgress.prototype.setCancelled = function (fileSize) {

	for(var i=0;i<SWFUpload.FILEIDARRAY.length;i++){
		if(SWFUpload.FILEIDARRAY[i]==this.fileProgressID){
			SWFUpload.FILEIDARRAY[i]='';
			break;
		}
	}
	var oSelf = this;
	this.setTimer(setTimeout(function () {
		oSelf.kill(fileSize);
	}, 500));
};
FileProgress.prototype.setStatus = function (status) {
	var fileID = this.fileProgressID;
	document.getElementById('mes_'+fileID).innerHTML = status;
	this.fileProgressElement.childNodes[1].style.display = '';	
};
FileProgress.prototype.kill = function (fileSize) {
	var delWrapper = this.fileProgressWrapper;
	document.getElementById(this.targetID).removeChild(delWrapper);
	var temp = SWFUpload.TOTALSIZE - fileSize;
	if(temp>0){
		SWFUpload.TOTALSIZE = temp;
	}else{
		SWFUpload.TOTALSIZE = 0;
	}
	document.getElementById('allsize').innerHTML = SWFUpload.speed.formatBytes(SWFUpload.TOTALSIZE);
}

// Show/Hide the cancel button
FileProgress.prototype.toggleCancel = function (swfUploadInstance) {
	var fileID = this.fileProgressID;
	var op = document.getElementById('op_'+fileID).childNodes[0];
	op.style.display = '';
	if (swfUploadInstance) {
		op.onclick = function () {
			swfUploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};

FileProgress.prototype.appear = function () {
	if (this.getTimer() !== null) {
		clearTimeout(this.getTimer());
		this.setTimer(null);
	}
	
	if (this.fileProgressWrapper.filters) {
		try {
			this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 100;
		} catch (e) {
			// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
			this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=100)";
		}
	} else {
		this.fileProgressWrapper.style.opacity = 1;
	}
		
	this.fileProgressWrapper.style.height = "";
	
	this.height = this.fileProgressWrapper.offsetHeight;
	this.opacity = 100;
	this.fileProgressWrapper.style.display = "";
	
};

// Fades out and clips away the FileProgress box.
FileProgress.prototype.disappear = function () {

	var reduceOpacityBy = 15;
	var reduceHeightBy = 4;
	var rate = 30;	// 15 fps

	if (this.opacity > 0) {
		this.opacity -= reduceOpacityBy;
		if (this.opacity < 0) {
			this.opacity = 0;
		}

		if (this.fileProgressWrapper.filters) {
			try {
				this.fileProgressWrapper.filters.item("DXImageTransform.Microsoft.Alpha").opacity = this.opacity;
			} catch (e) {
				// If it is not set initially, the browser will throw an error.  This will set it if it is not set yet.
				this.fileProgressWrapper.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + this.opacity + ")";
			}
		} else {
			this.fileProgressWrapper.style.opacity = this.opacity / 100;
		}
	}

	if (this.height > 0) {
		this.height -= reduceHeightBy;
		if (this.height < 0) {
			this.height = 0;
		}

		this.fileProgressWrapper.style.height = this.height + "px";
	}

	if (this.height > 0 || this.opacity > 0) {
		var oSelf = this;
		this.setTimer(setTimeout(function () {
			oSelf.disappear();
		}, rate));
	} else {
		this.fileProgressWrapper.style.display = "none";
		this.setTimer(null);
	}
};