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
import com.fandhi.awantunai.model.Member;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Controller
@RequestMapping(value = "/api/v1/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberBiz memberBiz;

	@Autowired
	private MessageBiz messageBiz;
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	@ResponseBody
	public String createMember(
			@RequestParam("name") String name,
			@RequestParam("nik") String nik,
			@RequestParam("npwp") String npwp,
			@RequestParam("pin") String pin
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("nik", nik);
		map.put("npwp", npwp);
		map.put("pin", pin);
		
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
				String encryptedPin = messageBiz.generateEncryptedPin(pin);
				if(encryptedPin.equals("")) {
					response.put("error", "Failed to Generate PIN");					
				} else {
					Member member = new Member();
					member.setName(name);
					member.setNik(nik);
					member.setNpwp(npwp);
					member.setAccountNo(memberBiz.generateAccountNo());
					member.setJoinDate(new Date());
					member.setBalance(0L);
					member.setPin(encryptedPin);
					memberBiz.create(member);
					response.put("accountNo", member.getAccountNo());
					response.put("result", "OK");
				}
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON : {}", e.getMessage());
			}			
		}
		return resp;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(
			@RequestParam("accountNo") String accountNo,
			@RequestParam("pin") String pin
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
		map.put("pin", pin);
		
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
				String encryptedPin = messageBiz.generateEncryptedPin(pin);
				if(encryptedPin.equals("")) {
					response.put("error", "Failed to Generate PIN");					
				} else {
					Member member = memberBiz.getMemberByAccountNoAndPin(accountNo, encryptedPin);
					if(member != null) {
						response.put("result", "OK");						
					} else {
						response.put("error", "Invalid AccountNo / PIN");												
					}
				}
				resp = om.writeValueAsString(response);
			} catch (Exception e) {
				logger.error("Error converting JSON : {}", e.getMessage());
			}			
		}
		return resp;
	}
	
}