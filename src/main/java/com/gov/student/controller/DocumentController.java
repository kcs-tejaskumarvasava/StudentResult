package com.gov.student.controller;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gov.student.dto.DocumentVO;
import com.gov.student.dto.ResponseVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.service.DocumentService;

@RestController
@StudentSecuredController
public class DocumentController {

	@Autowired
	 public DocumentService documentService;
	
	private static final String IMAGE_KEY = "image";
	
	@PostMapping("/uploadDocument")
	public ResponseVO uploadDocument(DocumentVO documentVo)
	{
		return documentService.uploadDocument(documentVo);
	}
	
	@GetMapping("/downloadFile")
	public ResponseVO<Map<String, byte[]>> downloadDocument(@RequestParam("id") Long id)
	{
		try
		{
			ByteArrayInputStream bos = documentService.downloadDocument(id);
			if (Objects.nonNull(bos)) {
				return ResponseVO.<Map<String, byte[]>>create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.value(),
						"Download succssfully", Collections.singletonMap(IMAGE_KEY, IOUtils.toByteArray(bos)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseVO.<Map<String, byte[]>>create(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ResponseStatus.FAIL.name(),"Download fail", Collections.singletonMap(IMAGE_KEY, null));
	}
}
