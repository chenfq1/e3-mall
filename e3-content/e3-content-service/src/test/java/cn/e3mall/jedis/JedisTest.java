/**
 * 
 */
package cn.e3mall.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 *
 *<p>Title:JedisTest</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月8日 下午5:08:56
 */
public class JedisTest {
	
	@Test
	public void TestJedis(){
	
		Jedis jedis = new Jedis("192.168.25.129", 6379);
		jedis.set("test", "124");
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
	
	}
}
