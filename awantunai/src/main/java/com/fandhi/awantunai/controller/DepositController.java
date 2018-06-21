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

import com.fandhi.awantunai.biz.MessageBiz;
import com.fandhi.awantunai.biz.TransactionBiz;
import com.fandhi.awantunai.model.TransactionHistory;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Controller
@RequestMapping(value = "/api/v1/transaction")
public class DepositController {

	private static final Logger logger = LoggerFactory.getLogger(DepositController.class);

	@Autowired
	private MessageBiz messageBiz;

	@Autowired
	private TransactionBiz transactionBiz;

	@RequestMapping(value = "/deposit", method=RequestMethod.POST)
	@ResponseBody
	public String deposit(
			@RequestParam("accountNo") String accountNo,
			@RequestParam("amount") Long amount,
			@RequestParam("sign") String sign
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
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
				boolean isValidSign = messageBiz.isValidSign(accountNo, sign);
				if(isValidSign) {
					TransactionHistory trx = new TransactionHistory();
					trx.setAccountNo1(accountNo);
					trx.setCredit(amount);
					trx.setType(TransactionHistory.TrxType.DEPOSIT.toString());
					trx.setLastBalance(transactionBiz.generateBalance(amount, accountNo));
					trx.setTrxDate(new Date());
					transactionBiz.create(trx);
					response.put("result", "OK");
					response.put("lastBalance", trx.getLastBalance());
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