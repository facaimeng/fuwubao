package com.cbai.model.rongyin.payment.huifu.service;

import com.cbai.model.rongyin.payment.huifu.vo.HuifuAutoTenderCancleVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuCashOutBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuInitiativeTenderBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuLoansBackVo;
import com.cbai.model.rongyin.payment.huifu.vo.HuifuNetSaveBackVo;

public interface HuifuCallBackService {
	
	 /**
	  * 充值完成回调处理
	  * 
	  * @throws Exception
	  */
     public void saveNetSaveCallback(HuifuNetSaveBackVo huifuNetSaveBackVo) throws Exception;
     
 	 /**
 	  * 提现完成回调处理
 	  * 
 	  * @throws Exception
 	  */
     public void saveCashOutCallback(HuifuCashOutBackVo huifuCashOutBackVo) throws Exception;
     
     /**
      * 放款完成回调处理
      * @param huifuLoansBackVo
      * @throws Exception
      */
     public void saveLoans(HuifuLoansBackVo huifuLoansBackVo)throws Exception;
  	
     /**
  	  * 投标完成回调处理
  	  * 
  	  * @throws Exception
  	  */
     public void buyInitiativeTender(HuifuInitiativeTenderBackVo huifuInitiativeTenderBackVo)throws Exception;
 
     /**
   	  * 后台撤销投标回调处理
   	  * 
   	  * @throws Exception
   	  */
     public void saveAutoTenderCancel(HuifuAutoTenderCancleVo huifuAutoTenderCancleVo)throws Exception;
}
