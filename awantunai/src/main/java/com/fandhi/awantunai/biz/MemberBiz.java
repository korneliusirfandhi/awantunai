package com.fandhi.awantunai.biz;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fandhi.awantunai.model.Member;
import com.fandhi.awantunai.service.MemberService;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Component
public class MemberBiz {

	@Autowired
	private MemberService memberService;

	public void create(Member member) {
		memberService.create(member);
	}
	
	public Member getMemberByAccountNo(String accountNo) {
		List<Member> list = memberService.findMemberByAccountNo(accountNo);
		if(list.isEmpty()) {
			return null;
		}
		return memberService.findMemberByAccountNo(accountNo).get(0);
	}
		
	public void editBalance(String accountNo, Long newBalance) {
		memberService.editBalance(accountNo, newBalance);
	}
	
	public Member getMemberByAccountNoAndPin(String accountNo, String pin) {
		List<Member> list = memberService.findMemberByAccountNoAndPin(accountNo, pin);
		if(list.isEmpty()) {
			return null;
		}
		return memberService.findMemberByAccountNoAndPin(accountNo, pin).get(0);
	}
	
	/**
	 * Generate account number randomly between 10000000 and 99999999
	 * @return accountNumber
	 */
	public String generateAccountNo() {
		Random r = new Random();
		int min = 10000000;
		int max = 99999999;
		int random = r.nextInt(max - min) + min;
		Member member = getMemberByAccountNo(String.valueOf(random));
		while(member != null) {
			random = r.nextInt(max - min) + min;
			member = getMemberByAccountNo(String.valueOf(random));
		}
		return String.valueOf(random);
	}
}