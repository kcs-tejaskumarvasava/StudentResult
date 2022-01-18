package com.gov.student.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gov.student.core.Document;
import com.gov.student.dto.DocumentVO;
import com.gov.student.dto.ResponseVO;
import com.gov.student.enums.ResponseStatus;
import com.gov.student.repository.DocumentRepository;
import com.gov.student.service.DocumentService;
import com.gov.student.utils.Messages;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Value("${document.upload.path}")
	public String documentUploadPath;
	
	@Autowired
	private  DocumentRepository documentRepository;
	
	@Override
	public ResponseVO<?> uploadDocument(DocumentVO documentVo) {
			
		try
		{
			Document appReqStatusId = null;
			ResponseVO<?> vo=validateDocumentRequest(documentVo);
			if(vo==null)
			{
				Document doc=new Document();
				String fileName=StringUtils.join(documentUploadPath,documentVo.getDocument().getOriginalFilename());
				
				doc.setDocumentName(documentVo.getDocument().getOriginalFilename());
				doc.setDocumentPath(fileName);
				doc.setTitle(documentVo.getTitle());
				appReqStatusId=documentRepository.save(doc);

				if (Objects.nonNull(appReqStatusId)) {
					savefile(fileName, documentVo);
				}

				return ResponseVO.create(HttpStatus.OK.value(), ResponseStatus.SUCSSESS.name(),
						documentVo.getId() == null ? Messages.DOCUMENT_UPLOADED_SUCSSFULLY : Messages.UPDATE_SUCSSFULLY,doc);
				
			}
			return vo;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseVO.create(HttpStatus.EXPECTATION_FAILED.value(),ResponseStatus.FAIL.name() , "Exception is occured");
		}
		
	}

	private void savefile(String fileName, DocumentVO documentVo) {
		
		try {
			FileUtils.writeByteArrayToFile(new File(fileName), documentVo.getDocument().getBytes());
		} catch (IOException e) {
			e.printStackTrace();		}
	}

	private ResponseVO<?> validateDocumentRequest(DocumentVO documentVo) {
		if(StringUtils.isBlank(documentVo.getDocumentName()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Document  name should not be null");
		}
		
		if(StringUtils.isBlank(documentVo.getTitle()))
		{
			return ResponseVO.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResponseStatus.FAIL.name(),
					"Document title should not be null");
		}
		
		if (!StringUtils.endsWithAny(documentVo.getDocument().getOriginalFilename(), ".jpg", ".jpeg", ".png",
				".pdf", ".doc", ".docx")) {
			return ResponseVO.create(HttpStatus.CONFLICT.value(), ResponseStatus.FAIL.name(),
					Messages.DOCUMENT_TYPE_FAILURE);
		}
		
		return null;
	}

	@Override
	public ByteArrayInputStream downloadDocument(Long id) {
		ByteArrayInputStream bais = null;
		try
		{
			Optional<Document> documentOpt= documentRepository.findOneById(id);
			if(documentOpt.isPresent())
			{
				bais = new ByteArrayInputStream(
						FileUtils.readFileToByteArray(new File(documentOpt.get().getDocumentPath())));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return bais;
	}

}
