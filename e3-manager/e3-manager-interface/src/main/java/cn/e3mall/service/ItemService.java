/**
 * 
 */
package cn.e3mall.service;

import com.e3mall.pojo.TbItem;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.EasyUIDataGridResult;

/**
 *
 *<p>Title:ItemService</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年2月26日 下午8:43:12
 */
public interface ItemService {
	
	TbItem getItemById(Long id);
	
	EasyUIDataGridResult getItemList(int page,int rows);
	
	E3Result addItem(TbItem tbItem,String desc);
}
