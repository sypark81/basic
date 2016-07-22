package com.maria.test.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.maria.file.UploadAttachmentResponse;
import com.maria.file.service.FileService;
import com.maria.test.domain.User;
import com.maria.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public FileService fileService;

	
	@RequestMapping(value="/")
	public String findIndex(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{

		log.info("redirect loginForm...");
		return "redirect:loginForm";
	}
	
	@RequestMapping(value="/loginForm")
	public String findLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{

		log.info("find loginForm...");
		return "loginForm";
	}
	
	
	
	@RequestMapping(value="/member/register")
	public String findRegister(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		log.info("find register...");
	    User user = new User();
		model.addAttribute("user",user);
		return "member/register";
	}
	
	@RequestMapping(value="/member/insertUser")
	public String insertUser(User user, Model model) throws Exception{
		log.info("try insertUser...");
		List<MultipartFile> userAttachFile = user.getFile();
		String fileId ="";
		boolean fileYn = false;
		
		if(userAttachFile != null && userAttachFile.size() > 0 ){
			
			for (MultipartFile multipartFile : userAttachFile) {
				
				
				if(multipartFile.getOriginalFilename() == null ||  multipartFile.getOriginalFilename().isEmpty()) continue;
				
				if(multipartFile.getOriginalFilename().length() > 0  && fileYn==false){
					fileId = fileService.selectMaxFileId();
					user.setPhotoFileId(fileId);
					fileYn = true;
				}
					
					
				String sourceFilename = multipartFile.getOriginalFilename();
				String sourceFileNameExtension = FilenameUtils.getExtension(sourceFilename).toLowerCase();
						
				File destinationFile;
		        String destinationFileName;
		        
		        do {
		            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
		            destinationFile = new File("C:/attachments/" + destinationFileName);
		        } while (destinationFile.exists());
		        destinationFile.getParentFile().mkdirs();
		        multipartFile.transferTo(destinationFile);
		        
		        com.maria.file.domain.File fileVo = new com.maria.file.domain.File();
		        fileVo.setFileId(fileId);
		        fileVo.setFileOrgName(multipartFile.getOriginalFilename());
		        fileVo.setFileName(destinationFile.getName());
		        fileVo.setFilePath(destinationFile.getPath());
		        fileVo.setFileSize(multipartFile.getSize());
		        fileVo.setFileTypeNm(multipartFile.getContentType());
		        
		        fileService.insert(fileVo);
		        
		        log.info("파일아이디 : "+ fileVo.getFileId());
		        log.info("파일seq : "+ fileVo.getFileSeq());
			}
			
		}
			
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		int count = userService.insert(user);
		
		log.info("시바랄"+count);
		
		if(count == 0 ){
			model.addAttribute("message", "가입에 실패하였습니다.");
			return "member/register";
		}
			
		model.addAttribute("message", "가입 완료하였습니다.");
		return "loginForm";
	}

}
