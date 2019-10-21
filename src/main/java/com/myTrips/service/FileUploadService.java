package com.myTrips.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
This class is resposible with upload file resource.
 */

@Service
public class FileUploadService {
	
	private static final String ROOT_DIR = "E:\\test";
	//method used for getting the multipart file and store it to ROOT_DIR with a specific name
	public void store(String photoName, MultipartFile file) {
		
		try(OutputStream outputStream = new FileOutputStream(new File(ROOT_DIR+photoName));
			InputStream inputStream = file.getInputStream();)//getting the file as input stream
		{
			
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error saving file", e);
		}
	}
	public Resource loadAsResource(String filename) {
		return new FileSystemResource(ROOT_DIR+filename);
	}
	
	
}
