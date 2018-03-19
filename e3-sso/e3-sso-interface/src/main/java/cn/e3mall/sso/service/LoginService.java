/**
 * 
 */
package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

/**
 *
 *<p>Title:LoginService</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月13日 下午5:43:22
 */
public interface LoginService {
	
	E3Result userLogin(String name,String password);
}
