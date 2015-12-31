package com.yayao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制类
 * @author yy
 *
 */
@Scope("prototype")
@Controller("jsoup")
public class JsoupController {
	
	/**
	 * 异步提交
	 */
	/*@RequestMapping(value="/addCustomerContent",method=RequestMethod.POST)
	public ResponseEntity<Customer> addCustomerContent(@ModelAttribute Customer customer){
			customerService.addCustomerContent(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}*/
	@RequestMapping(value="/siteproxy",method=RequestMethod.POST)
	public @ResponseBody Map<String ,String> addCustomerContent(HttpServletRequest request){
		String url = request.getParameter("url");
		String elem = request.getParameter("elem");
		Map<String,String> map=new HashMap<String,String>();
		String strbuffer;
		strbuffer="<li class=\"item\">" + url + "</li><br/>";
		strbuffer+="<li class=\"item\">" + elem + "</li><br/>";
		try{
			Document doc = Jsoup.connect(url).get();
			if(!elem.equals("all")){
			Elements items = doc.select(elem);
			for (Element item : items) {
				  Elements links = item.select("a");
				  for(Element link: links){
					  link.attr("href",link.attr("abs:href"));
				  }
				  
				  Elements imgs = item.select("img");
				  for(Element img: imgs){
					  img.attr("src",img.attr("abs:src"));
				  }
				  String html = item.html();
				  strbuffer+="<li class=\"item\">" + html + "</li><br/>";
			}
			}else{
				
				String html = doc.html();
				strbuffer+="<li class=\"item\">" + html + "</li><br/>";
				
				map.put("indexurl", url);
				map.put("index", strbuffer);
				Elements as = doc.select("a");
				for (int i = 0; i < as.size(); i++) {
					      Element a = as.get(i);
						  String aurl = a.attr("abs:href");
						  Document adoc = Jsoup.connect(aurl).get();
						  String ahtml = adoc.html();
						  String str="<li class=\"item\">" + ahtml + "</li><br/>";
						  
						  map.put("contenturl"+Integer.valueOf(i).toString(),aurl);
						  map.put("content"+Integer.valueOf(i).toString(),str);
					  }
				map.put("contentsize", Integer.valueOf(as.size()).toString());
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return  map;
	}
}
