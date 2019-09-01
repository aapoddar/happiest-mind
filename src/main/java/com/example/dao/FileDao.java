package com.example.dao;

import java.io.InputStream;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bean.FileInfo;

@Repository
public class FileDao {
	
	  private static final String INSERT_SQL = "insert into Dictionary (CONTENT) values (?)";
	  private static final String GET_SQL = "select * from Dictionary";
	  private static final String DICTIONARY_CONTENT ="CONTENT";
	  private static final String DICTIONARY_ID ="ID";

	
	  @Autowired
	  private JdbcTemplate jdbcTemplate;
	  
	 
	public void save(Reader reader) throws Exception {
	      jdbcTemplate.update(INSERT_SQL, reader);
	}
	
	
	public FileInfo fetch() {
		List<FileInfo> persons = jdbcTemplate.query(GET_SQL, (resultSet, i) -> {
			return toFileInfo(resultSet);
		});

		if (persons.size() > 0)
			return persons.get(0);
		else
			return null;

	}

	  private FileInfo toFileInfo(ResultSet resultSet) throws SQLException {
		  FileInfo fInfo = new FileInfo();
		  fInfo.setId(resultSet.getLong(DICTIONARY_ID));
	      InputStream contentStream = resultSet.getClob(DICTIONARY_CONTENT)
	                                           .getAsciiStream();
	      String content =
	              new Scanner(contentStream, "UTF-8").useDelimiter("\\A").next();
	      fInfo.setContent(content);
	      return fInfo;
	  }
	


}
