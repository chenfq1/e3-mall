/**
 * 
 */
package cn.e3mall.search.message;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;

/**
 *
 *<p>Title:ItemAddMessageListener</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月11日 下午7:42:33
 */
public class ItemAddMessageListener implements MessageListener {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public void onMessage(Message message) {
		//从消息中取得商品id
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			long itemId = new Long(text);
			
			Thread.sleep(1000);
			//根据商品id查询商品信息
			SearchItem searchItem = itemMapper.getItemById(itemId);
			//创建一个文档对象
			SolrInputDocument document = new SolrInputDocument();
			//向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			
			//把文档写入索引库
			solrServer.add(document);
			//提交
			solrServer.commit();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
