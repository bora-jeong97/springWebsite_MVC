package com.newlecture.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러를 담는 폴더같은 역할
@RequestMapping("/")
public class HomeController {

	
	// 3. 텍스트만 출력하는 경우 responseBody를 이용해 직접 출력할 수 있다
	@RequestMapping("index")
	@ResponseBody
	public String index() {
		return "Hello index1";
	}

}
	
	/*
	// 1. 타일즈를 활용하는 방법
	@RequestMapping("index")
	public String index() {
		
		return "root.index()";
		
	}

	
	// 2. 뷰 리절버 없이 직접 출력 하기 위해 하는 방법
	@RequestMapping("index")
	public void index(HttpServletResponse response) {
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("Hello Index!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//return "root.index"; // tiles 화면
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView("root.index");
		mv.addObject("data", "Hello Spring MVC");
		// mv.setViewName("WEB-INF/view/index.jsp");
	//	InternalResourceViewResolver
		
		return mv;
	}
*/
