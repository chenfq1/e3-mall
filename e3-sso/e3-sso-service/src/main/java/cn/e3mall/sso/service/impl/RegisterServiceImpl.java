/**
 * 
 */
package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.e3mall.mapper.TbUserMapper;
import com.e3mall.pojo.TbUser;
import com.e3mall.pojo.TbUserExample;
import com.e3mall.pojo.TbUserExample.Criteria;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.RegisterService;

/**
 *
 *<p>Title:RegisterService</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月13日 上午11:39:05
 */

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Override
	public E3Result checkData(String param, int type) {
		
		//根据不同的type查询不同的查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//1 用户名 2手机号  3邮箱
		if(type==1){
			criteria.andUsernameEqualTo(param);
		}else if(type==2){
			criteria.andPhoneEqualTo(param);
		}else if(type==3){
			criteria.andEmailEqualTo(param);
		}else{
			return E3Result.build(400, "数据类型错误");
		}
		//执行查询
		List<TbUser> list = tbUserMapper.selectByExample(example);
		//判断结果中是否包含数据
		//如果有数据返回false
		if(list!=null&&list.size()>0){
			return E3Result.ok(false);
		}else
 		//如果没有数据返回true
		return E3Result.ok(true);
	}

	
	@Override
	public E3Result register(TbUser tbUser) {
		
		//数据有效性校验
		if(StringUtils.isBlank(tbUser.getUsername())||
				StringUtils.isBlank(tbUser.getPassword())||StringUtils.isBlank(tbUser.getPhone())){
			return E3Result.build(400, "用户数据不完整，注册失败");
			
		}
		E3Result result = checkData(tbUser.getUsername(), 1);
		if(!(boolean) result.getData()){
			return E3Result.build(400, "用户名已占用");
		}
		result = checkData(tbUser.getPhone(), 2);
		if(!(boolean) result.getData()){
			return E3Result.build(400, "手机号已占用");
		}
		//补全tbUser的属性
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//对password进行MD5加密
		String md5Pass = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
		tbUser.setPassword(md5Pass);
		//把这个用户数据插入到数据库中
		tbUserMapper.insert(tbUser);
		//返回添加成功
		return E3Result.ok();
	}

}
