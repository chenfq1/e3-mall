/**
 * 
 */
package cn.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.e3mall.pojo.TbUser;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.TokenService;

/**
 *
 *<p>Title:TokenServiceImpl</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月14日 上午10:34:40
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis中取用户信息
		String json = jedisClient.get("SESSION:"+token);
		//取不到用户信息，登陆已经过期，返回登陆过期
		if(StringUtils.isBlank(json)){
			return E3Result.build(201, "用户登陆已过期");
		}
		//取到用户信息，更新token的过期时间
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		jedisClient.expire("SESSION:"+token, SESSION_EXPIRE);
		//返回结果，E3Result包含user信息
		return E3Result.ok(user);
	}

}
