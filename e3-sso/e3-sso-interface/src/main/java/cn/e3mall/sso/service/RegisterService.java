/**
 * 
 */
package cn.e3mall.sso.service;

import com.e3mall.pojo.TbUser;

import cn.e3mall.common.utils.E3Result;

/**
 *
 *<p>Title:RegisterService</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月13日 上午11:38:10
 */
public interface RegisterService {
	
	E3Result checkData(String param,int type);
	E3Result register(TbUser tbUser);
}
