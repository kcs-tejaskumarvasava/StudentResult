package com.gov.student.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gov.student.core.User;
import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.UserVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.repository.UserRepository;
import com.gov.student.service.UserService;
import com.gov.student.utils.Messages;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseVO  addEditUserDetails(UserVO userVo) {
		
		ResponseVO<?> resvo=validateRequest(userVo);
		if(resvo==null)
		{
			System.out.println("in save impl");
			User user=new User();
			if (!ObjectUtils.isEmpty(userVo.getId())) {
				
				user.setUsername(userVo.getUsername());
				user.setPassword(null);
			}
			
			user.setUsername(userVo.getUsername());
			user.setPassword(passwordEncoder.encode(userVo.getPassword()));
			userRepository.save(user);
			return ResponseVO.create(HttpStatus.OK.value(), "User added", "");
		}
		return resvo;
	}

	private ResponseVO<?> validateRequest(UserVO userVo) {
		
		if(StringUtils.isBlank(userVo.getUsername()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Username cant't be blank");
		}
		if(StringUtils.isBlank(userVo.getPassword()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"password cant't be blank");
		}
		
		return null;
	}

}
