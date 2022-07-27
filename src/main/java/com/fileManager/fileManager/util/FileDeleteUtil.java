package com.fileManager.fileManager.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class FileDeleteUtil {
	private Path foundFile;
	
	public String removeFileFromPath(String filecode) throws IOException {
		 
		
		 Path dirPath = Paths.get("Files-Upload");
		  
	        Files.list(dirPath).forEach(file -> {
	            if (file.getFileName().toString().startsWith(filecode)) {
	            	 try {
	            		foundFile = file;
						Files.delete(file);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	 
	                return;
	            }
	            
	        }
	        
	        		);
	 
	        if (foundFile == null) {
	            return "file not found";
	        }
			return "Y";       
	                 
	       
	}

	
}
