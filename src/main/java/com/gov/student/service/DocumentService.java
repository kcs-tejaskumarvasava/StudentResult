package com.gov.student.service;

import java.io.ByteArrayInputStream;

import com.gov.student.dto.DocumentVO;
import com.gov.student.dto.ResponseVO;

public interface DocumentService {

	ResponseVO uploadDocument(DocumentVO documentVo);

	ByteArrayInputStream downloadDocument(Long id);

}
