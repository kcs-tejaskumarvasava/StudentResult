package com.gov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.UserVO;
import com.gov.student.service.UserService;

@StudentSecuredController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("user/addEditUser")
	public  ResponseVO addEditUser( UserVO userVo)
	{
		//System.out.println("in controller"+userVo.getUsername());
		return userService.addEditUserDetails(userVo);
	}
	
	
}
