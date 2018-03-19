/**
 * 
 */
package cn.e3mall.search.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 *<p>Title:MyMessageListener</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月11日 下午6:19:22
 */
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		//取消息内容
		try {
			String text = textMessage.getText();
			System.out.println(text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
