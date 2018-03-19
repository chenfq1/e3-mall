/**
 * 
 */
package cn.e3mall.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 *
 *<p>Title:ActivemqSpring</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月11日 下午5:53:46
 */
public class ActivemqSpring {
	
	@Test
	public void sendMessage() throws Exception{
		
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		
		//从容器获取一个jmstemplate
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		//从容器获取destination对象
		Queue queue = (Queue) applicationContext.getBean("queueDestination");
		//使用jmstemplate对象发送消息，需要知道destination
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				TextMessage message = session.createTextMessage("spring activeMQ");
				
				return message;
			}
		});
		
	}
}
