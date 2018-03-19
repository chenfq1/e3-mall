package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import com.e3mall.mapper.TbContentMapper;
import com.e3mall.pojo.TbContent;
import com.e3mall.pojo.TbContentExample;
import com.e3mall.pojo.TbContentExample.Criteria;

/**
 * 内容管理Service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_KEY;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public E3Result addContent(TbContent content) {
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.insert(content);
		
		//缓存同步,删除缓存中相应的数据
		jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
		return E3Result.ok();
	}

	/**
	 * 根据内容分类id查询内容列表
	 * <p>Title: getContentListByCid</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return
	 * @see cn.e3mall.content.service.ContentService#getContentListByCid(long)
	 */
	@Override
	public List<TbContent> getContentListByCid(long cid) {
		
		//查詢缓存
		try {
			String json = jedisClient.hget(CONTENT_KEY, cid+"");
			//判断json是否为空
			if(StringUtils.isNotBlank(json)){
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		//向缓存中添加数据
		try{
			jedisClient.hset(CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

}
