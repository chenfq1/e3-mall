/**
 * 
 */
package cn.e3mall.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

/**
 *
 * <p>
 * Title:ActiveMQTest
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author chenfq date 2018年3月11日 下午2:50:46
 */
public class ActiveMQTest {

	@Test
	public void testQueueProducer() throws Exception {
		// 1.创建connectionFactory对象，需要指定服务端ip和端口信息
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		// 2.使用connectionFactory创建connection
		Connection connection = connectionFactory.createConnection();
		// 3.启动connection
		connection.start();
		// 4.使用connection创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.使用session对象创建destination对象（topic，queue），此处为queue
		Queue queue = session.createQueue("test-queue");
		// 6.使用session对象创建一个生产者producer
		MessageProducer producer = session.createProducer(queue);
		// 7.创建一个message对象，创建一个textmessage对象
		TextMessage textMessage = session.createTextMessage("testMessage");
		// 8.使用producer发送message
		producer.send(textMessage);

		// 第九步：关闭资源。
		producer.close();
		session.close();
		connection.close();
	}

	@Test
	public void testQueueConsumer() throws Exception {

		// 1.创建connectionFactory对象，需要指定服务端ip和端口信息
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		// 2.使用connectionFactory创建connection
		Connection connection = connectionFactory.createConnection();
		// 3.启动connection
		connection.start();
		// 4.使用connection创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.使用session对象创建destination对象,应与生产者的queue的名称一致
		Queue queue = session.createQueue("test-queue");

		// 6.使用session创建一个消费者consumer
		MessageConsumer consumer = session.createConsumer(queue);

		// 7.接收消息
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message arg0) {
				// TODO Auto-generated method stub
				TextMessage textMessage = (TextMessage) arg0;
				try {
					String message = textMessage.getText();
					System.out.println(message);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 等待键盘输入
		System.in.read();

		// 关闭资源
		consumer.close();
		session.close();
		connection.close();
	}

}
