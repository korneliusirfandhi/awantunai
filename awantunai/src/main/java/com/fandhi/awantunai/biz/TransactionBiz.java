package com.fandhi.awantunai.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fandhi.awantunai.model.Member;
import com.fandhi.awantunai.model.TransactionHistory;
import com.fandhi.awantunai.service.TransactionHistoryService;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Component
public class TransactionBiz {

	private final Long HOLD_BALANCE = 50000L; 
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;

	@Autowired
	private MemberBiz memberBiz;
	
	public void create(TransactionHistory transactionHistory) {
		transactionHistoryService.create(transactionHistory);
	}
	
	public Long generateBalance(Long balance, String accountNo) {
		Member member = memberBiz.getMemberByAccountNo(accountNo);
		Long oldBalance = member.getBalance();
		Long newBalance = oldBalance + balance;
		
		// update balance on member
		memberBiz.editBalance(accountNo, newBalance);
		
		return newBalance;
	}

	public boolean isValidTransaction(Long balance, String accountNo) {
		Member member = memberBiz.getMemberByAccountNo(accountNo);
		if(balance + HOLD_BALANCE > member.getBalance()) {
			return false;
		}
		return true;
	}
	
}