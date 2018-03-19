/**
 * 
 */
package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

/**
 *
 *<p>Title:PictureController</p>
 *<p>Description:图片上传的controller</p>
 *
 * @author chenfq
 * date 2018年3月6日 下午3:33:35
 */
@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String fileUpload(MultipartFile uploadFile){
		//1.把图片上传到图片服务器
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String exName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			//2.得到图片的地址和文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(),exName);
			
			//3.补全完整url
			url = IMAGE_SERVER_URL+url;
			
			//4.封装map返回
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			
			String json = JsonUtils.objectToJson(result);
			return json;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//5、返回map
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			String jsonErr = JsonUtils.objectToJson(result);
			return jsonErr;
		}
		
	}

}
