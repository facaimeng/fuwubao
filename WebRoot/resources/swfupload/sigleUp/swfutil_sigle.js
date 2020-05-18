var settings = {
	flash_url : "/swfupload/swfupload.swf",
	upload_url: "",
	post_params: {"id":"1010"},
	file_size_limit : "10 MB",
	file_types : "*.jpg;*.gif",
	file_types_description : "选择文件类型,限图片",
	file_upload_limit : 10,
	file_queue_limit : 0,
 
	debug: false,

	// Button settings
	//button_image_url: "/images/up1.jpg",
	//button_width: "58",
	//button_height: "22",
	//button_placeholder_id: "upbtn",
	//button_text: '<span style="width:467px;"></span>',
	//button_text_style: ".theFont { font-size: 16;};.heihei{background:url(/images/up1.jpg) no-repeat;}",
	//button_text_left_padding: 12,
	//button_text_top_padding: 3,
	//button_image_url: "/images/button_270x22.png",
	button_width: "60",
	button_height: "28",
	button_placeholder_id: "upbtn",
	button_text: '<span class="theFont">选择文件</span>',
	button_cursor : SWFUpload.CURSOR.HAND,
	button_text_style: ".theFont {font-size: 12;font-weight: bold;border:1px solid blue;}",
	button_text_left_padding: 3,
	button_text_top_padding: 3,
	
	
	
	file_dialog_start_handler:fileDialogStart,			
	file_queued_handler : fileQueued,
	file_queue_error_handler : fileQueueError,
	file_dialog_complete_handler : fileDialogComplete,
	upload_start_handler : uploadStart,
	upload_progress_handler : uploadProgress,
	upload_error_handler : uploadError,
	upload_success_handler : uploadSuccess,
	upload_complete_handler : uploadComplete,
	uploadDone_toURL : ""
	//queue_complete_handler : queueComplete	
};
