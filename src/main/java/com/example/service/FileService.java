package com.example.service;

import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.bean.CustomStatus;
import com.example.bean.FileInfo;
import com.example.bean.ResponseCommon;
import com.example.bean.ResponseSearch;
import com.example.dao.FileDao;

/**
 * @author aapoddar
 * 
 * Service class responsible for servicing the incoming request.
 *
 */
@Service
public class FileService {
	
	@Autowired
	FileDao fileDao;
	
	public ResponseCommon uploadFile(MultipartFile file) throws Exception {
		ResponseCommon response = new ResponseCommon();
		CustomStatus status = new CustomStatus ();
		
		if(ObjectUtils.isEmpty(file)){
			status.setStatusCode(HttpStatus.BAD_REQUEST);
			status.setStatusMesaage("No File Found in the request.");
			response.setStatus(status);
		}
		else{
			Reader reader = new InputStreamReader(file.getInputStream());
			fileDao.save(reader);
			status.setStatusCode(HttpStatus.OK);
			status.setStatusMesaage("File has been uploaded into the database sucessfully.");
			response.setStatus(status);
		}
		return response;
	}
	
	public ResponseSearch search(String searchString) throws Exception {
		
		ResponseSearch response = new ResponseSearch();
		CustomStatus status = new CustomStatus ();
		boolean isStrFound = false;
		
		FileInfo fInfo =  fileDao.fetch();
		if(ObjectUtils.isEmpty(fInfo)){
			
			status.setStatusCode(HttpStatus.NOT_FOUND);
			status.setStatusMesaage("No File Found in the DB.");
			response.setStatus(status);
			
		}
		else{
			String content = fInfo.getContent();
			if(content.contains(searchString)){
				isStrFound = true;
				status.setStatusMesaage("The specified key word found in the dictionary file.");
			}
			else{
				status.setStatusMesaage("The specified key word is not found in the dictionary file.");
			}
			status.setStatusCode(HttpStatus.OK);
			response.setStatus(status);
			
		}
		response.setIsSearchStrFound(isStrFound);
		return response;
	}

}
