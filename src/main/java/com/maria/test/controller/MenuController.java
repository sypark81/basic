package com.maria.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.maria.json.JsonResponse;
import com.maria.json.WebResponse;
import com.maria.test.domain.Menu;
import com.maria.test.domain.User;
import com.maria.test.service.MenuService;
import com.maria.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuController {

	@Autowired
	public MenuService menuService;

	@ResponseBody
	@RequestMapping(value = "/menu/menuFind")
	public WebResponse findMenuList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		log.info("메뉴호출 ");
		List<Menu> menuList = menuService.selectList();
		return new JsonResponse(menuList);
	}
}
