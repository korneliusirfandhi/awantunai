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

/**
 * 
 * @author kornelius.irfandhi
 *
 */

public class ResponseMessage {

	private final String result;
	
	private final String lastBalance;

	public ResponseMessage(String result, String lastBalance) {
		this.result = result;
		this.lastBalance = lastBalance;
	}

	public String getResult() {
		return result;
	}

	public String getLastBalance() {
		return lastBalance;
	}
	
	
}
