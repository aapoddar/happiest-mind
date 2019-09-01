package com.example.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bean.ResponseCommon;
import com.example.bean.ResponseSearch;
import com.example.service.FileService;



@RestController
public class FileController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
    private FileService fileStorageService;
	
	/**
	 * 
	 * @param file
	 * @return
	 * This API is for uploading a file to the database
	 */
	@PostMapping(value ="/file/uploadFile", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //{ "multipart/form-data" }
    public ResponseEntity<ResponseCommon> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			LOGGER.info("uploadFile of controller");
			ResponseCommon response = fileStorageService.uploadFile(file);
			LOGGER.info("Upload file to the Database completed.");
			return new ResponseEntity<ResponseCommon>(response, response.getStatus().getStatusCode());
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	/**
	 * 
	 * This API is for searching a value based on the
	 * contents of the file persisted in the database.
	 */
	
	@GetMapping(value = "/file/search/{searchString}",produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ResponseSearch> search(@PathVariable String searchString) {
		try {
			LOGGER.info("Search from the file content started. Search String is = "+searchString);
			ResponseSearch response = fileStorageService.search(searchString);
			return new ResponseEntity<ResponseSearch>(response, response.getStatus().getStatusCode());
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	

}
