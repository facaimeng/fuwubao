function fileDialogStart() {  
	 var mtfile = document.getElementById("videoFile");
	 mtfile.value = "";

	 this.cancelUpload();
}
function fileQueued(file) {
	try {	 
  		document.getElementById('videoFile').value=file.name;
 	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
			return;
		}
		var mes = document.getElementById('per');
 
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
 			mes.innerHTML="文件大小超出范围!";
			
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
 			mes.innerHTML="请选择文件!";
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
 			mes.innerHTML="请上传指定文件!";
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				mes.innerHTML="未知错误!";
 			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
		alert(mes.innerHTML);
	} catch (ex) {
        this.debug(ex);
    }
}
function fileDialogComplete(numFilesSelected, numFilesQueued) {	
	try {
		if (numFilesSelected > 0) {
			 
		}
	} catch (ex)  {
        this.debug(ex);
	}
}
function uploadStart(file) {
	try {
 		document.getElementById('barTable').style.display='';
 	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
  		var percent = SWFUpload.speed.formatPercent(file.percentUploaded); 
		document.getElementById('bar').style.width = percent;	
		document.getElementById('per').innerHTML = percent;	
		 
		if(percent=='100%'){ 
			document.getElementById('per').innerHTML = '服务器处理中...';			
		}
		//document.getElementById('rate').innerHTML = SWFUpload.speed.formatBPS(file.currentSpeed);	
	} catch (ex) {
		this.debug(ex);
	}
}
function uploadSuccess(file, serverData) {  
	try {
		 
		if(serverData=='suc.upload'){
			document.getElementById('per').innerHTML = '上传成功!';		
			window.location.href=settings.uploadDone_toURL;
		}else{
			var err;
			switch (serverData) { 
				case "error.upload":
					err = "上传失败,请重试!"; 
					break;
				case "error.up_ext":
					err = "请上传指定类型的文件!"; 
					break;  
				case "error.up_size":
					err = "文件大小超出限制!"; 
					break; 
				case "error.up_encoding":
					err = "页面字符编码出错!"; 
					break;
				case "error.up_nofile":
					err = "请选择上传文件!"; 
					break;
				default:
					err = "未知错误,请重试!"; 
	 				break;
			} 
			document.getElementById('per').innerHTML = err;
			window.location.href=settings.uploadDone_toURL;
 		} 
 		
	} catch (ex) {
		this.debug(ex);
	}
}


function uploadError(file, errorCode, message) {

	try {
		var mes = document.getElementById('per');
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			mes.innerHTML="服务器未知错误!";
 			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			mes.innerHTML="上传失败,请重试!";
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			mes.innerHTML="文件上传I/O读写错误!";
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			mes.innerHTML="请确定您是否有此权限!";
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			mes.innerHTML="文件上传超过限制数!";
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			mes.innerHTML="请检查上传文件格式!";
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:	
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			mes.innerHTML="上传未知错误终止!";
			break;
		default:
			mes.innerHTML="未知错误,请重试!";
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
 	

}

function queueComplete(numFilesUploaded) {
 
}
 