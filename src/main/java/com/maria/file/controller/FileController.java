package com.maria.file.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maria.file.domain.File;
import com.maria.file.domain.FileSearch;
import com.maria.file.service.FileService;
import com.maria.test.domain.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileController {

	@Autowired
	public FileService fileService;

	@RequestMapping(value="/download/file")
	public ModelAndView findRegister(@RequestParam("fileId") String fileId, Model model) throws Exception{
		log.info("download File...");
		FileSearch fileSearch = new FileSearch();
		fileSearch.setFileId(fileId);
		fileSearch.setFileSeq(1);
		File file = fileService.selectOne(fileSearch);
		
		ModelAndView mav = new ModelAndView();
		
	    mav.addObject("fileName", new java.io.File( file.getFilePath()));
	    mav.addObject("fileTargetName",file.getFileOrgName());
	    mav.setViewName("downloadView");
		return mav;
	}
}
