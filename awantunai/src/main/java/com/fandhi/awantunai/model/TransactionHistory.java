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

@Entity
@Table(name = "TransactionHistory")
public class TransactionHistory {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "AccountNo1")    
    private String accountNo1;

    @Column(name = "AccountNo2")    
    private String accountNo2;

    @Column(name = "TrxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trxDate;

	@Column(name = "Debet")
	private Long debet;

	@Column(name = "Credit")
	private Long credit;

	@Column(name = "LastBalance")
	private Long lastBalance;

	@Column(name = "Type", length = 20)
	private String type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo1() {
		return accountNo1;
	}

	public void setAccountNo1(String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	public String getAccountNo2() {
		return accountNo2;
	}

	public void setAccountNo2(String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	public Date getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}

	public Long getDebet() {
		return debet;
	}

	public void setDebet(Long debet) {
		this.debet = debet;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public Long getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(Long lastBalance) {
		this.lastBalance = lastBalance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
	public enum TrxType {
		TRANSFER_SEND, TRANSFER_RECEIVE, WITHDRAWAL, DEPOSIT
	}
}
