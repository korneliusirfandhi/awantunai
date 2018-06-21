package com.fandhi.awantunai.service;

import java.util.List;

import com.fandhi.awantunai.model.Member;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
public interface MemberService {

	public void create(Member member);
	
	public List<Member> findMemberByAccountNo(String accountNo);
	
	public void editBalance(String accountNo, Long newBalance);

	public List<Member> findMemberByAccountNoAndPin(String accountNo, String pin);

}
