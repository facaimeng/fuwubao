var suc_upcount=0;//成功上传文件个数
function fileQueued(file) {
	try {	
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.toggleCancel(this);
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

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("文件大小超出范围!");
			//this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("请选择文件!");
			//this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("请上传指定文件!");
			//this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("未知错误!");
			}
			//this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}
function fileDialogComplete(numFilesSelected, numFilesQueued) {	
	try {
		if (numFilesSelected > 0) {
			SWFUpload.TOTALSIZE = SWFUpload.QueueSIZE + SWFUpload.TOTALSIZE;
			SWFUpload.QueueSIZE = 0;
			var allper = Math.ceil((SWFUpload.ALLUPLOADEDSIZE / SWFUpload.TOTALSIZE) * 100);
			this.customSettings.allBar.style.width = allper+'%';
			this.customSettings.allper.innerHTML = allper+'%';		
			this.customSettings.allsize.innerHTML = SWFUpload.speed.formatBytes(SWFUpload.TOTALSIZE);
			this.customSettings.allInfo.style.display = '';	
				
		}
	} catch (ex)  {
        this.debug(ex);
	}

}
function uploadStart(file) {
	try {
		 document.getElementById('op_'+file.id).childNodes[0].innerHTML='';
		 document.getElementById('pbar_'+file.id).style.display = '';
		 document.getElementById('retime_'+file.id).innerHTML = SWFUpload.speed.formatTime(file.timeRemaining);		 
		 document.getElementById('pwatch_'+file.id).style.display = '';
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = SWFUpload.speed.formatPercent(file.percentUploaded);		
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		 
		progress.setProgress(percent);
		progress.setWatch(SWFUpload.speed.formatBytes(file.sizeUploaded),SWFUpload.speed.formatBPS(file.currentSpeed),SWFUpload.speed.formatTime(file.timeRemaining));
		
		if(percent=='100%'){
			progress.setStatus("服务器处理中..."); 			
		}
		
	} catch (ex) {
		this.debug(ex);
	}
}
function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget); 
		serverData = JSON.parse(serverData);  
		if(serverData.code=='1'){
			progress.setComplete(); 
			progress.setStatus("上传成功!");
			SWFUpload.SUCUPCOUNT = SWFUpload.SUCUPCOUNT + 1;
			SWFUpload.SUCUPSIZE = file.size + SWFUpload.SUCUPSIZE;
		}else if(serverData.code=='0'){
			progress.setError(); 
			progress.setStatus(serverData.message);
		}else{
			progress.setError(); 
			progress.setStatus("未知错误,请重试!"); 
		} 
		document.getElementById('pbar_'+file.id).style.display = 'none';
		document.getElementById('pwatch_'+file.id).style.display = 'none';
		SWFUpload.ALLUPLOADEDSIZE = file.size + SWFUpload.ALLUPLOADEDSIZE;
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	try {		
		var progress = new FileProgress(file, this.customSettings.progressTarget);		
		progress.setError(); 
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("上传路径或服务器未知错误!"); 
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("上传失败,请重试!."); 
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("文件上传I/O读写错误,请重试!"); 
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("请确定您是否有此权限!"); 
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("文件上传超过限制数!"); 
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("请检查上传文件格式!."); 
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			
			if (this.getStats().files_queued === 0) {

			}			
			progress.setStatus("取消中...");
			progress.setCancelled(file.size);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("上传未知错误终止!");
			break;
		default:
			progress.setStatus("未知错误,请重试!");
 			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
	var allper = Math.ceil((SWFUpload.ALLUPLOADEDSIZE / SWFUpload.TOTALSIZE) * 100);
	
	this.customSettings.allBar.style.width = allper+'%';
	
	this.customSettings.allper.innerHTML = allper+'%';		
	this.customSettings.allsize.innerHTML = SWFUpload.speed.formatBytes(SWFUpload.TOTALSIZE);
	
	//this.customSettings.upcount.innerHTML = this.getStats().successful_uploads; 
	//this.customSettings.upsize.innerHTML = SWFUpload.speed.formatBytes(SWFUpload.ALLUPLOADEDSIZE);	
	
	this.customSettings.upcount.innerHTML = SWFUpload.SUCUPCOUNT;	 
	this.customSettings.upsize.innerHTML = SWFUpload.speed.formatBytes(SWFUpload.SUCUPSIZE); 
}

function queueComplete(numFilesUploaded) {
}
