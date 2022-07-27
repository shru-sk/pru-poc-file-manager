package com.fileManager.fileManager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileManager.fileManager.response.FileUploadResponse;
import com.fileManager.fileManager.util.FileUploadUtil;

@CrossOrigin
@RestController
public class FileUploadController {
	
	@Autowired
	FileUploadUtil fileUploadUtil;
	

	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 long size = multipartFile.getSize();
		  String filecode = fileUploadUtil.saveFile(fileName, multipartFile);
		  FileUploadResponse response = new FileUploadResponse();
	        response.setFileName(fileName);
	        response.setSize(size);
	        response.setDownloadUri("/downloadFile/" + filecode);
	        return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
