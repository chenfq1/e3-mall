/**
 * 
 */
package cn.e3mall.search.mapper;

import java.util.List;

import cn.e3mall.pojo.SearchItem;

/**
 *
 *<p>Title:ItemMapper</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月9日 下午5:04:32
 */
public interface ItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
