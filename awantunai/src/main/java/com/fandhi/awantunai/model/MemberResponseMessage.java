package com.fandhi.awantunai.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 
 * @author kornelius.irfandhi
 *
 */

public class MemberResponseMessage {

	private final String result;
	
	private final String accountNo;

	private final Map<String, String> error;

	public MemberResponseMessage(String result, String accountNo, Map<String, String> error) {
		this.result = result;
		this.accountNo = accountNo;
		this.error = error;
	}

	public String getResult() {
		return result;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public Map<String, String> getError() {
		return error;
	}
	
}
