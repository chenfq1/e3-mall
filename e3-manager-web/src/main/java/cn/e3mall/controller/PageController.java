/**
 * 
 */
package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *<p>Title:PageController</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年2月28日 下午2:43:18
 */

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String toPage(@PathVariable String page){
		return page;
	}
}
