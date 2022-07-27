package com.fileManager.fileManager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fileManager.fileManager.util.FileDeleteUtil;
import com.fileManager.fileManager.util.FileDownloadUtil;

@CrossOrigin
@RestController
public class FileDeleteController {
    
	@Autowired
	FileDeleteUtil deleteUtil ;
	@GetMapping("delete/{fileCode}")
	public ResponseEntity<String> deleteFile(@PathVariable("fileCode") String filecode) {
		
		String isDeleted ;
		
		try {
			isDeleted = deleteUtil.removeFileFromPath(filecode);
		}
		catch(IOException e){
			return  ResponseEntity.internalServerError().build();
		}
		if ( !isDeleted.equalsIgnoreCase("Y") ) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
	
		return new ResponseEntity<>("File Deleted", HttpStatus.OK);
       // return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
