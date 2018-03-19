/**
 * 
 */
package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

/**
 *导入商品到索引库
 *<p>Title:SearchItemController</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月9日 下午7:09:40
 */
@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importItemIndex(){
		E3Result e3Result = searchItemService.importItems();
		return e3Result;
	}
}
