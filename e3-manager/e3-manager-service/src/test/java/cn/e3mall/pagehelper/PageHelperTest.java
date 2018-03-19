/**
 * 
 */
package cn.e3mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.e3mall.mapper.TbItemMapper;
import com.e3mall.pojo.TbItem;
import com.e3mall.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 *<p>Title:PageHelperTest</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年2月28日 下午4:01:47
 */
public class PageHelperTest {
	
	/**
	 * 
	 */
	@Test
	public void testPageHelper() throws Exception{
		// TODO Auto-generated method stub
		
		//初始spring容器
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		//从容器中获取mapper代理对象
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		
		//设置分页信息
		PageHelper.startPage(1, 10);
		
		//执行查询
		TbItemExample tbItemExample = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
		
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		System.out.println(pageInfo.getSize());
		System.out.println(pageInfo.getPages());
		System.out.println(pageInfo.getTotal());
	}
}
