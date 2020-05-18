package com.cbai.model.rongyin.sys.content.vo;

import com.cbai.model.common.data.State;

public class NewsSearchVO {
	
	private String ntype;
	
	private String title;
    
    private String source;
    
    private String author;
     

	public NewsSearchVO() { 
	}

	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
    

}
