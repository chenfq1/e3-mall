/**
 * 
 */
package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e3mall.mapper.TbItemCatMapper;
import com.e3mall.pojo.TbItemCat;
import com.e3mall.pojo.TbItemCatExample;
import com.e3mall.pojo.TbItemCatExample.Criteria;

import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.service.ItemCatService;

/**
 *
 *<p>Title:ItemCatServiceImpl</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月1日 下午4:09:09
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	/* (non-Javadoc)
	 * @see cn.e3mall.service.ItemCatService#getItemCatlist(long)
	 */
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatlist(long parentId) {
		
		//1根据parentid查询节点列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		
		//2转化成EasyUITreeNode列表
		List<EasyUITreeNode> result = new ArrayList<>();
		for (TbItemCat node : list) {
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(node.getId());
			treeNode.setText(node.getName());
			treeNode.setState(node.getIsParent()?"closed":"open");
			//添加到结果列表
			result.add(treeNode);
		}
		return result;
	}

}
