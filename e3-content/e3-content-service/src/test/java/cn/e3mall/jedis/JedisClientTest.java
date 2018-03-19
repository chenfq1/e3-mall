/**
 * 
 */
package cn.e3mall.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

/**
 *
 *<p>Title:JedisClientTest</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月8日 下午5:46:13
 */
public class JedisClientTest {
	
	@Test
	public void testJedisClient(){
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("mytest", "jedisClient");
		String s = jedisClient.get("mytest");
		System.out.println(s);
	}
}
