package com.gov.student.service;

import com.gov.student.dto.ResponseVO;
import com.gov.student.dto.UserVO;

public interface UserService {

	ResponseVO  addEditUserDetails(UserVO userVo);

}
