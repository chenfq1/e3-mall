/**
 * 
 */
package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e3mall.pojo.TbContent;

import cn.e3mall.content.service.ContentService;

/**
 *
 *<p>Title:IndexController</p>
 *<p>Description:首页展示controller</p>
 *
 * @author chenfq
 * date 2018年3月6日 下午11:23:38
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		
		//查询内容列表
		List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
		model.addAttribute("ad1List", ad1List);
		
		return "index";
	}
}
