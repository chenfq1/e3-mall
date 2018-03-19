import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 */

/**
 *
 *<p>Title:JedisTest</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年1月4日 下午10:13:23
 */
public class JedisTest {
	
	/**
	 *	单例链接redis
	 */
	@Test
	public void run1() {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("192.168.127.130", 6379);
		System.out.println(jedis.get("name"));
	}
	
	/**
	 * Jedis连接池
	 */
	@Test
	public void run2(){
		
		//连接池配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		//简单设置
		config.setMaxTotal(50);
		//设置连接池中保有的最大连接数
		config.setMaxIdle(10);
		
		//获取连接池对象
		JedisPool pool = new JedisPool(config, "192.168.127.130", 6379);
		//从池中获取链接对象
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("name"));
		
		//连接归还池中
		jedis.close();
	}
}
