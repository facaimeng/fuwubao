package com.cbai.model.rongyin.ibatis.entity;

import java.util.Date;

import com.cbai.common.utils.OptUtil;
import com.cbai.model.common.data.State;
import com.cbai.model.rongyin.sys.users.vo.MemAccountVO;
import com.cbai.model.rongyin.sys.users.vo.MemDadVO;

public class Members {
    
	private Integer memid;

    private String memnum;

    private String openid;

    private String phone;

    private String nickname;

    private String realname;

    private String idnum;

    private String logpwd;

    private String paypwd;

    private String usrcustid;

    private String usrcustname;

    private String headimgurl;

    private String email;
    
    private String sex;
    
    private Date birth;

    private State state;
    
    private String stname; 

    private Integer invitecount;

    private String dadmemid;
    
    private String dadname;
    
    private String dadtype;

    private String cfrom;

    private Integer lpwderrorcount;

    private Integer ppwderrorcount;

    private State ifloanman;
    
    private State iffreeze;
    
    private State ifbind;
    
    private State ifbus;
    
    private String ifsub;

    private String address;

    private String urgentman;

    private Date locktime;

    private Date regtime;

    private Date authtime;
    
    private MemAccountVO accinfo;
    
    private MemDadVO dadinfo;
    
    private Long avlmoney;
	
	private Long bidingmoney; 
	
	private Long allbidmoney;

    public Integer getMemid() {
        return memid;
    }

    public void setMemid(Integer memid) {
        this.memid = memid;
    }

    public String getMemnum() {
        return memnum;
    }

    public void setMemnum(String memnum) {
        this.memnum = memnum;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getLogpwd() {
        return logpwd;
    }

    public void setLogpwd(String logpwd) {
        this.logpwd = logpwd;
    }

    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }

    public String getUsrcustid() {
        return usrcustid;
    }

    public void setUsrcustid(String usrcustid) {
        this.usrcustid = usrcustid;
    }

    public String getUsrcustname() {
        return usrcustname;
    }

    public void setUsrcustname(String usrcustname) {
        this.usrcustname = usrcustname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    } 

    public Integer getInvitecount() {
        return invitecount;
    }

    public void setInvitecount(Integer invitecount) {
        this.invitecount = invitecount;
    }

    public String getDadmemid() {
        return dadmemid;
    }

    public void setDadmemid(String dadmemid) {
        this.dadmemid = dadmemid;
    }

    public String getCfrom() {
        return cfrom;
    }

    public void setCfrom(String cfrom) {
        this.cfrom = cfrom;
    }

    public Integer getLpwderrorcount() {
        return lpwderrorcount;
    }

    public void setLpwderrorcount(Integer lpwderrorcount) {
        this.lpwderrorcount = lpwderrorcount;
    }

    public Integer getPpwderrorcount() {
        return ppwderrorcount;
    }

    public void setPpwderrorcount(Integer ppwderrorcount) {
        this.ppwderrorcount = ppwderrorcount;
    }

    public State getIfloanman() {
        return ifloanman;
    }

    public void setIfloanman(State ifloanman) {
        this.ifloanman = ifloanman;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrgentman() {
        return urgentman;
    }

    public void setUrgentman(String urgentman) {
        this.urgentman = urgentman;
    }

    public Date getLocktime() {
        return locktime;
    }

    public void setLocktime(Date locktime) {
        this.locktime = locktime;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getAuthtime() {
        return authtime;
    }

    public void setAuthtime(Date authtime) {
        this.authtime = authtime;
    }
    public String getStname() {
		return this.state.getName();
	}

	public State getIffreeze() {
		return iffreeze;
	}

	public void setIffreeze(State iffreeze) {
		this.iffreeze = iffreeze;
	}

	public String getIfsub() {
		return ifsub;
	}

	public void setIfsub(String ifsub) {
		this.ifsub = ifsub;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getDadname() {
		return dadname;
	}

	public void setDadname(String dadname) {
		this.dadname = dadname;
	}

	public State getIfbind() {
		return ifbind;
	}

	public void setIfbind(State ifbind) {
		this.ifbind = ifbind;
	}

	public String getDadtype() {
		return dadtype;
	}

	public void setDadtype(String dadtype) {
		this.dadtype = dadtype;
	}

	public MemDadVO getDadinfo() {
		return dadinfo;
	}

	public void setDadinfo(MemDadVO dadinfo) {
		this.dadinfo = dadinfo;
	}

	public MemAccountVO getAccinfo() {
		return accinfo;
	}

	public void setAccinfo(MemAccountVO accinfo) {
		this.accinfo = accinfo;
	}

	public Long getAvlmoney() {
		return avlmoney;
	}

	public void setAvlmoney(Long avlmoney) {
		this.avlmoney = avlmoney;
	}

	public Long getBidingmoney() {
		return bidingmoney;
	}

	public void setBidingmoney(Long bidingmoney) {
		this.bidingmoney = bidingmoney;
	}

	public Long getAllbidmoney() {
		return allbidmoney;
	}

	public void setAllbidmoney(Long allbidmoney) {
		this.allbidmoney = allbidmoney;
	}

	public State getIfbus() {
		return ifbus;
	}

	public void setIfbus(State ifbus) {
		this.ifbus = ifbus;
	} 
	
}