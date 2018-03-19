import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 */

/**
 *
 *<p>Title:JedisUtils</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年1月4日 下午11:18:34
 */
public class JedisUtils {
	
	private final static JedisPool POOL;
	
	static{
		//连接池配置对象
				JedisPoolConfig config = new JedisPoolConfig();
				//简单设置
				config.setMaxTotal(50);
				//设置连接池中保有的最大连接数
				config.setMaxIdle(10);
				
				//获取连接池对象
				POOL = new JedisPool(config, "192.168.127.130", 6379);
	}
	
	public static Jedis getJedis(){
		return POOL.getResource();
	}

}
