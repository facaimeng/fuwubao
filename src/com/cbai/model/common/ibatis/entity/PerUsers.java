package com.cbai.model.common.ibatis.entity;

import java.util.Date;

import com.cbai.model.common.data.State;
 

public class PerUsers {
   
	private Integer userid; 
	
	private Integer accid;
	
	private Integer pdid;
	
    private String realname;
    
    private String usrcustid;
    
    private String sex;
    
    private String depname;

    private Date birthday;

    private String comefrom;

    private Date joindate;

    private Date quitdate;
    
    private String quitmemo;

    private String idnum;

    private String phone;

    private String qqnum;
    
    private String rate1;
    
    private String rate2;

    private String memo;

    private Date addtime; 
    
    private State state;
    
    private String stname;
    
    private String roleJson;
    
    private String loginname;
    
    private String password;
    
    private Date lastlogin; 
    
    private PerAccount perAccount;
    
    public PerUsers() { 
    	
	}
  

	public String getRealname() {
		return realname;
	} 
	public void setRealname(String realname) {
		this.realname = realname;
	}
 
	public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getQuitdate() {
        return quitdate;
    }

    public void setQuitdate(Date quitdate) {
        this.quitdate = quitdate;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    } 
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public String getQuitmemo() {
		return quitmemo;
	}

	public void setQuitmemo(String quitmemo) {
		this.quitmemo = quitmemo;
	}
  

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	} 

	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public String getLoginname() {
		return loginname;
	}


	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public Date getLastlogin() {
		return lastlogin;
	}


	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}


	public PerAccount getPerAccount() {
		return perAccount;
	}


	public void setPerAccount(PerAccount perAccount) {
		this.perAccount = perAccount;
	}


	public Integer getAccid() {
		return accid;
	}


	public void setAccid(Integer accid) {
		this.accid = accid;
	}


	public String getDepname() {
		return depname;
	}


	public void setDepname(String depname) {
		this.depname = depname;
	}


	public Integer getPdid() {
		return pdid;
	}


	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getRoleJson() {
		return roleJson;
	}


	public void setRoleJson(String roleJson) {
		this.roleJson = roleJson;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	} 

	public String getStname() {
		return this.state.getName(); 
	}


	public String getUsrcustid() {
		return usrcustid;
	}


	public void setUsrcustid(String usrcustid) {
		this.usrcustid = usrcustid;
	}


	public String getRate1() {
		return rate1;
	}


	public void setRate1(String rate1) {
		this.rate1 = rate1;
	}


	public String getRate2() {
		return rate2;
	}


	public void setRate2(String rate2) {
		this.rate2 = rate2;
	} 
    
}