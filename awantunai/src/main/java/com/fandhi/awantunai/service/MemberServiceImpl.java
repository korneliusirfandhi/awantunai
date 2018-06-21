package com.fandhi.awantunai.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fandhi.awantunai.model.Member;


/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public void create(Member member) {
		sessionFactory.getCurrentSession().save(member);
		logger.error(">>>>> Create Member Successfully <<<<<");
	}

	@SuppressWarnings("unchecked")
	public List<Member> findMemberByAccountNo(String accountNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.add(Restrictions.eq("accountNo", accountNo));
		return criteria.list();
	}

	public void editBalance(String accountNo, Long newBalance) {
		try {
	        String sql = "UPDATE Member SET Balance = :newBalace WHERE AccountNo = :accountNo";
	        Query query = sessionFactory.getCurrentSession().createQuery(sql);
	        query.setParameter("newBalace", newBalance);
	        query.setParameter("accountNo", accountNo);
	        query.executeUpdate();
	    } catch (Exception e) {
	    	logger.error(">>>>> Failed to update balance - {} : {} <<<<<", accountNo, e.getMessage());
	    }
	}

	@SuppressWarnings("unchecked")
	public List<Member> findMemberByAccountNoAndPin(String accountNo, String pin) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.add(Restrictions.eq("accountNo", accountNo));
		criteria.add(Restrictions.eq("pin", pin));
		return criteria.list();
	}

}
