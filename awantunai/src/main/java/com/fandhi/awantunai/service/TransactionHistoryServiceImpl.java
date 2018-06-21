package com.fandhi.awantunai.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fandhi.awantunai.model.TransactionHistory;


/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionHistoryServiceImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public void create(TransactionHistory transactionHistory) {
		sessionFactory.getCurrentSession().save(transactionHistory);
		logger.error(">>>>> Create Transaction History Successfully <<<<<");
	}

	@SuppressWarnings("unchecked")
	public List<TransactionHistory> findHistoryByAccountNo(String accountNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionHistory.class);
		criteria.add(Restrictions.eq("accountNo", accountNo));
		criteria.addOrder(Order.desc("trxDate"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TransactionHistory> findHistoryByAccountNoAndType(String accountNo, String type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionHistory.class);
		criteria.add(Restrictions.eq("accountNo", accountNo));
		criteria.add(Restrictions.eq("type", type));
		criteria.addOrder(Order.desc("trxDate"));
		return criteria.list();
	}

}
