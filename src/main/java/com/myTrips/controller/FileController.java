package com.myTrips.controller;

import com.myTrips.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*
This class is a controller and is responsible for displaying inline content
image in the browser. This is accessed thru thymeleaf engine from trips-page web
page.
Images that can be displayed are having a maximum of 10MB.

 */

@Controller
public class FileController {

	
	@Autowired
	private FileUploadService fileService;
	
	@GetMapping(value ="/files/{filename:.+}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = fileService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\""+ file.getFilename() +"\"").body(file);
	}
	
}

	

