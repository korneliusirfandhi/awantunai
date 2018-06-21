import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fandhi.awantunai.biz.MessageBiz;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class MessageBizTest {

	private static final Logger logger = LoggerFactory.getLogger(MessageBizTest.class);
	
	@Autowired
	private MessageBiz messageBiz;
	
	@Test
	@Ignore
	public void testGetAccount() {
		String pin = "123456";
		String accountNo = "95929005";
		String sign = messageBiz.generateSign(pin, accountNo);
		logger.error(">>>>>> SIGN : {} <<<<<<", sign);
		Assert.assertEquals("52012b69e7dff1ca28a5ade909fa2fcf", sign);
	}


}
