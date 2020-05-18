package com.cbai.model.rongyin.sys.loan.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cbai.common.json.JsonResultObject;
import com.cbai.model.rongyin.ibatis.entity.LoanInfo;
import com.cbai.model.rongyin.ibatis.entity.LoanMan;
import com.cbai.model.rongyin.ibatis.entity.MemBidLogs;
import com.cbai.model.rongyin.ibatis.entity.Projects;
import com.cbai.model.rongyin.sys.loan.vo.LoanRepayPlanDetailVO;
import com.cbai.model.rongyin.sys.users.vo.LoanmanSearchVO;

public interface LoanMgService {
	
	public List<Projects> projectsList(Map<String, Object> params) throws SQLException;
	
	public List<LoanMan> loanManList(LoanmanSearchVO loanmanSearchVO) throws SQLException;
	
    public boolean isHfLnidRepeat(String hfLnId) throws SQLException;
    
    /**发布标的（本系统）**/
    public void saveLoan(LoanInfo loanInfo, Projects projetcs) throws SQLException;
    
    /**更新标的信息 **/
    public void updateLoan(LoanInfo loanInfo, Projects projetcs) throws SQLException;
   
    /**标的审核(发标到汇付)**/
    public JsonResultObject updateCheckPass(LoanInfo loanInfo) throws SQLException, Exception;
    
    /**满标停投 **/
    public JsonResultObject saveReplayPlan(Integer lnid)throws Exception;
    
    /**逐个还款**/
    public JsonResultObject saveRepayment(LoanRepayPlanDetailVO repayPlanDetail, String outCustId,  Integer lrpdid) throws Exception;
    
    /**放款给借款人**/
    public JsonResultObject saveLoans(Integer mblid, Integer lnid) throws Exception;
    
    /**将资金定向给定向接收人**/
    public JsonResultObject saveDirecTrf(LoanInfo loanInfo) throws Exception;
    
    /**撤销投标**/
    public JsonResultObject saveAutoTenderCancle(MemBidLogs memBidLogs)throws Exception;

}
