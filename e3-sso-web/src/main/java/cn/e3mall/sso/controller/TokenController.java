/**
 * 
 */
package cn.e3mall.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.TokenService;

/**
 *根据token查询用户信息controller
 *<p>Title:TokenController</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月14日 上午10:48:54
 */
@Controller
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="/user/token/{token}",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback){
		E3Result result = tokenService.getUserByToken(token);
		if(StringUtils.isNotBlank(callback)){
			return callback+"("+JsonUtils.objectToJson(result)+");";
		}
		return JsonUtils.objectToJson(result);
	}

}
