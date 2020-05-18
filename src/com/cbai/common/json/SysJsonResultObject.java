package com.cbai.common.json;

public class SysJsonResultObject { 
	
	private String error;
    
	private boolean hasError;
	
	private Integer results;  
	
	private Object rows;
	
    public SysJsonResultObject() { }

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public Integer getResults() {
		return results;
	}

	public void setResults(Integer results) {
		this.results = results;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	} 

	 
}
