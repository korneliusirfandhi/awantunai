import java.util.List;

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

import com.fandhi.awantunai.model.Member;
import com.fandhi.awantunai.service.MemberService;

/**
 * 
 * @author kornelius.irfandhi
 *
 */
@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class MemberServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Autowired
	private MemberService memberService;
	
	@Test
	@Ignore
	public void testGetAccount() {
		List<Member> list = memberService.findMemberByAccountNo("82075319");
		logger.error(">>>>> Get {} Data <<<<<", list.size());
		Assert.assertEquals(1, list.size());
	}


}
