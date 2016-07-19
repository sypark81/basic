package com.maria.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.maria.test.domain.User;
import com.maria.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	public UserService userService;

	@RequestMapping(value = "/member/userFind")
	public String findUserList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("유저호출 ");

		List<User> userList = userService.selectList();

		model.addAttribute("name", "syPark");
		model.addAttribute("userList", userList);

		return "member/userList";
	}

	@RequestMapping(value = "/member/userForm")
	public String findUserForm(@RequestParam("id") String id, Model model) throws Exception {
		log.info("선택된 유저 조회");

		User user = userService.selectOne(id);

		model.addAttribute("user", user);

		return "member/userForm";
	}

	@RequestMapping(value="/member/updateUser")
	public String updateUser(User user, Model model) throws Exception{
		log.info("선택된 유저 수정");
		int count = userService.updateUser(user);
		if(count > 0 ) model.addAttribute("message", "처리완료"); else model.addAttribute("message","수정실패");
		return "member/userForm";
	}
	
	@RequestMapping(value="/member/deleteUser")
	public ModelAndView deleteUser(User user, ModelAndView model) throws Exception{
		log.info("선택된 유저 삭제");
		int count = userService.deleteUser(user);
		if(count > 0 ) model.addObject("message", "처리완료"); else model.addObject("message","삭제실패");
		RedirectView rv = new RedirectView("userFind");
		rv.setExposeModelAttributes(false);
		model.setView(rv);
		return model;
	}
	
	
}
