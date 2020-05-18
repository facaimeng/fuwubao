package com.cbai.model.rongyin.pc.member.vo;

import java.util.Date;

import com.cbai.model.common.data.State;
 

public class MemberVO {
	
    private Integer memid;
    
    private String memnum; 
    
    private String usrcustid; 
    
    private String phone;
    
    private State sex;

    private String phoneLable;
    
    private String nameLable;

    private String nickname;
    
    private String surname;
    
    private String realname;

    private String password; 
    
    private State state;

	private String lgtype; //登陆类型 cookie或者直接登陆 
	
	private Date regtime;
	
    public MemberVO() {}

	public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        
        if(this.phone!=null&&this.phone.length()>7){
        	this.phoneLable =  this.phone.substring(0,this.phone.length()-(this.phone.substring(3)).length())+"****"+this.phone.substring(7);
        }else{
        	this.phoneLable =  this.phone;
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname; 
	}
 

	public String getLgtype() {
		return lgtype;
	}

	public void setLgtype(String lgtype) {
		this.lgtype = lgtype;
	}

	public String getPhoneLable() {
		return phoneLable;
	} 
	
	public String getMemnum() {
		return memnum;
	}

	public void setMemnum(String memnum) {
		this.memnum = memnum;
	}

	public String getUsrcustid() {
		return usrcustid;
	}

	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}

	public State getSex() {
		return sex;
	}

	public void setSex(State sex) {
		this.sex = sex;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getNameLable() {
		return nameLable;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	} 
	
}