/**
 * 
 */
package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.EasyUITreeNode;
import cn.e3mall.service.ItemCatService;

/**
 *
 *<p>Title:ItemCatController</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月1日 下午4:29:16
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getCatList(@RequestParam(value="id",defaultValue = "0")long parentId){
		List<EasyUITreeNode> result = itemCatService.getItemCatlist(parentId);
		return result;
	}

}
