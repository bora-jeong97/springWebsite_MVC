package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller            // 컨트롤러를 담는 폴더같은 역할
public class HomeController {

	@RequestMapping("/index")
	public String index() {
		
		return "root.index";  //tiles 화면
	}
	
	@RequestMapping("/help")
	public void help() {
		System.out.println("2번째 홈 메소드당");
	}
	
	
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data", "Hello Spring MVC");
//		// mv.setViewName("WEB-INF/view/index.jsp");
//	//	InternalResourceViewResolver
//		
//		return mv;
//	}

}
