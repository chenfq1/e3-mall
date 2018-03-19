/**
 * 
 */
package cn.e3mall.service;

import java.util.List;

import cn.e3mall.pojo.EasyUITreeNode;

/**
 *
 *<p>Title:ItemCatService</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月1日 下午4:06:20
 */
public interface ItemCatService {

	List<EasyUITreeNode> getItemCatlist(long parentId);
}
