package com.fandhi.awantunai.service;

import java.util.List;

import com.fandhi.awantunai.model.TransactionHistory;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
public interface TransactionHistoryService {

	public void create(TransactionHistory transactionHistory);
	
	public List<TransactionHistory> findHistoryByAccountNo(String accountNo);
	
	public List<TransactionHistory> findHistoryByAccountNoAndType(String accountNo, String type);
	
}
