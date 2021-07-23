package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;
	

	
	// 생성자
	// 메소드-gs
	// 메소드-일반

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");

		// Dao 사용  --> @Autowired
		//PhoneDao phoneDao = new PhoneDao();    

		// Dao의 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();

		// model담기 (택배박스에 담기) --> DispatcherServlet전달된다 ---> request의 attritube영역에 넣는다
		model.addAttribute("personList", personList);

		// view
		return "/WEB-INF/views/list.jsp"; // DispatcherServlet 야 /WEB-INF/views/list.jsp 에 포워드해줘!!
	}
	
	

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");

		return "/WEB-INF/views/writeForm.jsp"; // DispatcherServlet 야 /WEB-INF/views/writeForm.jsp 에 포워드해줘!!
	}

	// 쓰기
	// @ModelAttribute 일때
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.write]");

		// @ModelAttribute --> new PersonVo()
		// --> 기본생성자 + setter
		//System.out.println(personVo);

		// Dao 사용  --> @Autowired
		//PhoneDao phoneDao = new PhoneDao();

		// Dao 의 personInser() 이용해서 데이터 저장
		int count = phoneDao.personInsert(personVo);

		// view --> 리다이렉트
		return "redirect:/list";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int personId) {
		System.out.println("[PhoneController.delete]");
		System.out.println(personId);

		// Dao 사용  --> @Autowired
		//PhoneDao phoneDao = new PhoneDao();

		// Dao 의 personDelete() 이용해서 데이터 삭제
		//int count = phoneDao.personDelete(personId);

		// view --> 리다이렉트
		return "redirect:/list";
	}

	// 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("no") int personId) {
		System.out.println("[PhoneController.updateForm]");
		System.out.println(personId);

		// Dao 사용  --> @Autowired
		//PhoneDao phoneDao = new PhoneDao();

		// Dao 의 personDelete() 이용해서 데이터 삭제
		//PersonVo personVo = phoneDao.getPerson(personId);

		// model담기 (택배박스에 담기) --> DispatcherServlet전달된다 ---> request의 attritube영역에 넣는다
		//model.addAttribute("personVo", personVo);

		// view --> jsp파일
		return "/WEB-INF/views/updateForm.jsp"; // DispatcherServlet 야 /WEB-INF/views/writeForm.jsp 에 포워드해줘!!

	}

	// 수정
	// @ModelAttribute 일때
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.update]");

		// @ModelAttribute --> new PersonVo()
		// --> 기본생성자 + setter
		System.out.println(personVo);

		// Dao 사용
		PhoneDao phoneDao = new PhoneDao();

		// Dao 의 personInser() 이용해서 데이터 저장
		//int count = phoneDao.personUpdate(personVo);

		// view --> 리다이렉트
		return "redirect:/list";
	}

	
	
	
	
	/*********************************************************************************/
	// PathVariable 테스트
	@RequestMapping(value = "/board/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("PathVariable [read]");

		// localhost:8088/phonebook5/board/read/1
		// localhost:8088/phonebook5/board/read/2
		// localhost:8088/phonebook5/board/read/100

		System.out.println(boardNo);

		return "";
	}

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp"; // DispatcherServlet 야 /WEB-INF/views/test.jsp 에 포워드해줘!!
	}

}
