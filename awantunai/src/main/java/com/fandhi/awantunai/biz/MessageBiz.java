package com.fandhi.awantunai.biz;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.fandhi.awantunai.model.Member;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Component
public class MessageBiz {

	private static final Logger logger = LoggerFactory.getLogger(MessageBiz.class);

	@Autowired
	private MemberBiz memberBiz;

	public List<Map<String, Object>> errorMessage(Map<String, Object> map) {
		List<Map<String, Object>> listError = new ArrayList<Map<String, Object>>();
		Map<String, Object> error = new HashMap<String, Object>();
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getValue() == null || entry.getValue().equals("")) {
				error = new HashMap<String, Object>();
				error.put("error", entry.getKey() + " is empty");
				listError.add(error);
			}
		}
		return listError;
	}

	private String sha256Encrypt(String text) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] hash = sha256.digest(text.getBytes("UTF-8"));
			String sha256res = DatatypeConverter.printHexBinary(hash).toLowerCase();
			return sha256res;
	    } catch (Exception e) {
	    	logger.error("Failed to encrypt SHA-256 : {}", e.getMessage());
	    }
	    return "";
	}
	
	private String encrypt(String text) {
		try {
			String md5 = DigestUtils.md5DigestAsHex(sha256Encrypt(text).getBytes());
			return md5;
	    } catch (Exception e) {
	    	logger.error("Failed to encrypt : {}", e.getMessage());
	    }
	    return "";
	}
	
	/**
	 * 
	 * @param pin
	 * @param accountNo
	 * @return MD5(MD5(SHA-256(pin)) + SHA-256(accountNo))
	 */
	public String generateSign(String pin, String accountNo) {
		return encrypt(encrypt(pin) + sha256Encrypt(accountNo));
	}
	
	/**
	 * 
	 * @param pin
	 * @return MD5(SHA-256(pin))
	 */
	public String generateEncryptedPin(String pin) {
		return encrypt(pin);
	}

	public boolean isValidSign(String accountNo, String sign) {
		Member member = memberBiz.getMemberByAccountNo(accountNo);
		String encrypt = encrypt(member.getPin() + sha256Encrypt(member.getAccountNo()));
		if(encrypt.equals(sign)) {
			return true;
		}
		return false;
	}
	
}