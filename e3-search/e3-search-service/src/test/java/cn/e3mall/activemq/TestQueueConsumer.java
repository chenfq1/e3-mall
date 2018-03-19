/**
 * 
 */
package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *<p>Title:TestQueueConsumer</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月11日 下午7:10:10
 */
public class TestQueueConsumer {

	@Test
	public void testQueueConsumer() throws Exception{
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		
		//等待
		 System.in.read();
	}
}
