/**
 * 
 */
package cn.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;

/**
 *
 *<p>Title:SearchItemServiceImpl</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月9日 下午5:58:47
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public E3Result importItems() {
		
		//查询商品列表
		List<SearchItem> list = itemMapper.getItemList();
		
		//导入索引库
		try {
			
			for (SearchItem searchItem : list) {
				
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				
				//向文档添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				
				//写入索引库
				solrServer.add(document);
			}
			
			//提交
			solrServer.commit();
			//返回成功
			return E3Result.ok();
			
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "商品导入失败");
		}
	}

}
