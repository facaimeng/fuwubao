package com.cbai.model.rongyin.pc.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.cbai.common.ibatis.dao.IbatisBaseDao;
import com.cbai.model.rongyin.ibatis.entity.MemAccount;
import com.cbai.model.rongyin.ibatis.entity.MemCards;

import com.cbai.model.rongyin.ibatis.entity.Members;
import com.cbai.model.rongyin.pc.member.service.MemberService;
  

public class MemberServiceImpl implements MemberService{
	
	private IbatisBaseDao baseDao;  
	  
	public Integer saveRegist(Members members,Long avlMoney) throws Exception{
		
		Integer memberId = (Integer) baseDao.insert("pc_mem.insert", members);
		MemAccount memAccount = new MemAccount();
		memAccount.setMemid(memberId);
		memAccount.setAllassets(0l);
		memAccount.setYdayrepay(0l);
		memAccount.setAllrepay(0l);
		if(avlMoney!=null){
			memAccount.setAvlmoney(avlMoney);
		}else{
			memAccount.setAvlmoney(0l);
		} 
		memAccount.setAvlprofit(0l);
		memAccount.setAllinmoney(0l);  
		memAccount.setAlloutmoney(0l);
		memAccount.setAllinprofit(0l);
		memAccount.setAlloutprofit(0l); 
		
		baseDao.insert("pc_mem_account.insert", memAccount);
    	return memberId;
	}
	

	@Override
	public void saveCard(MemCards memCards) throws Exception {
		
		baseDao.insert("pc_mem_account.insert-card", memCards);
		
		baseDao.update("pc_mem.upBind", memCards.getMemid()); 
	}
	

	@Override
	public Integer updateMemberSys(Members members,String oldphone) throws Exception {
		// TODO Auto-generated method stub
		
		baseDao.update("members.update", members);
		
		Map  map = new HashMap();
		
		map.put("memid", members.getMemid());
		
		map.put("avlmoney", members.getAvlmoney()); 
		
		baseDao.update("pc_mem_account.upByMemid", map);
		
		if(!oldphone.equals(members.getPhone())){
			
			map.put("phone", members.getPhone());
			
			map.put("oldphone", oldphone);
			
			map.put("mytype", "2");
			
			baseDao.update("members.upDad", map);
		}  
		return null;
	}


	
	@Override
	public Integer saveCashOut(String usrcustid,String transAmt,String orderId) throws Exception {
		//TODO 
		return null;
	}
	
	public void setBaseDao(IbatisBaseDao baseDao) {
		this.baseDao = baseDao;
	}



	 
}
