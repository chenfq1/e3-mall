/**
 * 
 */
package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 *
 *<p>Title:TestSolr</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月9日 下午5:19:49
 */
public class TestSolr {

	@Test
	public void addDocument() throws Exception{
		
		//创建一个solrserver对象，创建一个链接，参数solr服务的url
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/solr/collection1");
		//创建一个文档对像SolrInputDocument
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		//向文档对象中添加域。文档必须包含一个id域，所有的域名称必须在schema.xml中定义
		solrInputDocument.addField("id", "doc01");
		solrInputDocument.addField("item_title", "测试商品");
		solrInputDocument.addField("item_price", 1000);
		//把文档写入索引库
		solrServer.add(solrInputDocument);
		//提交
		solrServer.commit();
	}
}
