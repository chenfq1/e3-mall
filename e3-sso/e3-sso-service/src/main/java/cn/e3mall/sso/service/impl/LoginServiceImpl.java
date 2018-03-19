/**
 * 
 */
package cn.e3mall.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.e3mall.mapper.TbUserMapper;
import com.e3mall.pojo.TbUser;
import com.e3mall.pojo.TbUserExample;
import com.e3mall.pojo.TbUserExample.Criteria;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.LoginService;

/**
 *
 *<p>Title:LoginServiceImpl</p>

 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月13日 下午5:48:42
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	
	@Override
	public E3Result userLogin(String name, String password) {
//		1、判断用户名密码是否正确。
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(name);
		
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list == null||list.size()==0){
			return E3Result.build(400, "用户名或密码错误");
		}
		//取用户信息
		TbUser user = list.get(0);
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			return E3Result.build(400, "用户名或密码错误");
		}
//		2、登录成功后生成token。Token相当于原来的jsessionid，字符串，可以使用uuid。
		String token = UUID.randomUUID().toString();
//		3、把用户信息保存到redis。Key就是token，value就是TbUser对象转换成json。
		//把密码设置为null
		user.setPassword(null);
		String json = JsonUtils.objectToJson(user);
//		4、使用String类型保存Session信息。可以使用“前缀:token”为key
		jedisClient.set("SESSION"+":"+token, json);
//		5、设置key的过期时间。模拟Session的过期时间。一般半个小时。
		jedisClient.expire("SESSION"+":"+token, SESSION_EXPIRE);
//		6、返回e3Result包装token。
		return E3Result.ok(token);
	}

}
