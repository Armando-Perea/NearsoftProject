package com.java.nearsoft.project.repository;


import org.springframework.stereotype.Repository;

import com.java.nearsoft.project.model.Alias;
import com.java.nearsoft.project.model.UrlModel;
import com.java.nearsoft.project.model.UrlModelList;

@Repository
public interface UrlGeneratorRepository {

	
	public Alias createUrl(UrlModel urlModel);
	
	public UrlModel getUrlByAlias(String url);
	
	public UrlModelList getAllUrl();
	
	public String clearUrls();
	
	public String deleteUrlByEngine(String engine);
	
	public String deleteUrlByAlias(String alias);
	
	

}
