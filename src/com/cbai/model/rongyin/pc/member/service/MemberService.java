package com.cbai.model.rongyin.pc.member.service; 

import com.cbai.model.rongyin.ibatis.entity.MemCards;
import com.cbai.model.rongyin.ibatis.entity.Members;

public interface MemberService { 
	
	public Integer saveRegist(Members members,Long avlMoney) throws Exception; 
	
	public Integer saveCashOut(String usrcustid, String transAmt, String orderId) throws Exception;
	
	public void saveCard(MemCards memCards) throws Exception; 
	
	public Integer updateMemberSys(Members members,String oldphone) throws Exception; 
	
}
