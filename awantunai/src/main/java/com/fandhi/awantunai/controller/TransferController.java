package com.fandhi.awantunai.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fandhi.awantunai.biz.MemberBiz;
import com.fandhi.awantunai.biz.MessageBiz;
import com.fandhi.awantunai.biz.TransactionBiz;
import com.fandhi.awantunai.model.Member;
import com.fandhi.awantunai.model.TransactionHistory;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Controller
@RequestMapping(value = "/api/v1/transaction/transfer")
public class TransferController {

	private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

	@Autowired
	private MessageBiz messageBiz;

	@Autowired
	private TransactionBiz transactionBiz;

	@Autowired
	private MemberBiz memberBiz;

	@RequestMapping(value = "/inquiry", method=RequestMethod.POST)
	@ResponseBody
	public String inquiry(
			@RequestParam("accountNo1") String accountNo1,
			@RequestParam("accountNo2") String accountNo2,
			@RequestParam("amount") Long amount,
			@RequestParam("sign") String sign
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo1", accountNo1);
		map.put("accountNo2", accountNo2);
		map.put("amount", amount);
		map.put("sign", sign);
		
		// checking field & value		
		List<Map<String, Object>> listError = messageBiz.errorMessage(map);
		
		String resp = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> response = new HashMap<String, Object>();

		if(!listError.isEmpty()) {
			try {
				response.put("errors", listError);
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON");
			}
		} else {
			try {
				boolean isValidSign = messageBiz.isValidSign(accountNo1, sign);
				if(isValidSign) {
					boolean isValid = transactionBiz.isValidTransaction(amount, accountNo1);
					if(isValid) {
						Member recipient = memberBiz.getMemberByAccountNo(accountNo2);
						if(recipient == null) {
							response.put("error", "Nomor Rekening Tujuan Tidak Ditemukan");						
						} else {
							response.put("result", "OK");
							response.put("accountNo", accountNo2);
							response.put("accountOwner", recipient.getName());
							response.put("amount", amount);
						}
					} else {
						response.put("error", "Nominal Saldo Tidak Mencukupi");
					}
				} else {
					response.put("error", "Invalid Sign");
				}
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON");
			}			
		}
		return resp;
	}

	@RequestMapping(value = "/proceed", method=RequestMethod.POST)
	@ResponseBody
	public String proceed(
			@RequestParam("accountNo1") String accountNo1,
			@RequestParam("accountNo2") String accountNo2,
			@RequestParam("amount") Long amount,
			@RequestParam("sign") String sign
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo1", accountNo1);
		map.put("accountNo2", accountNo2);
		map.put("amount", amount);
		map.put("sign", sign);
		
		// checking field & value		
		List<Map<String, Object>> listError = messageBiz.errorMessage(map);
		
		String resp = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> response = new HashMap<String, Object>();

		if(!listError.isEmpty()) {
			try {
				response.put("errors", listError);
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON");
			}
		} else {
			try {
				boolean isValidSign = messageBiz.isValidSign(accountNo1, sign);
				if(isValidSign) {
					// create history for sender
					TransactionHistory trx1 = new TransactionHistory();
					trx1.setAccountNo1(accountNo1);
					trx1.setAccountNo2(accountNo2);				
					trx1.setDebet(amount);
					trx1.setType(TransactionHistory.TrxType.TRANSFER_SEND.toString());
					trx1.setLastBalance(transactionBiz.generateBalance(amount * -1, accountNo1));
					trx1.setTrxDate(new Date());
					transactionBiz.create(trx1);
					
					// create history for recipient
					TransactionHistory trx2 = new TransactionHistory();
					trx2 = new TransactionHistory();
					trx2.setAccountNo1(accountNo2);
					trx2.setAccountNo2(accountNo1);
					trx2.setCredit(amount);
					trx2.setType(TransactionHistory.TrxType.TRANSFER_RECEIVE.toString());
					trx2.setLastBalance(transactionBiz.generateBalance(amount, accountNo2));
					trx2.setTrxDate(trx1.getTrxDate());
					transactionBiz.create(trx2);
					
					response.put("result", "OK");
					response.put("lastBalance", trx1.getLastBalance());	
				} else {
					response.put("error", "Invalid Sign");
				}
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON");
			}			
		}
		return resp;
	}

}