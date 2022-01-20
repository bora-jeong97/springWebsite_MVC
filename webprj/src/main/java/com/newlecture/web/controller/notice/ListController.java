package com.newlecture.web.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.jdbc.JDBCNoticeService;

public class ListController implements Controller {

	//아래 dispatcher-servlet.xml에서 온  setter는 class 객체로 존제할 수 있다
	@Autowired
	private NoticeService noticeService;
	
/*	
	// setter 생성
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
		System.out.println("어노테이션을 이 곳에 하면 추가 코드를 작성해서 붙일 수 있다. ");
	}
*/


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		ModelAndView mv = new ModelAndView("notice.list");

		// mv.setViewName("WEB-INF/view/notice/list.jsp");
	//	InternalResourceViewResolver
		List<Notice> list = noticeService.getList(1, "TITLE", "");
		mv.addObject("list", list);
		
		return mv;
	}

}
