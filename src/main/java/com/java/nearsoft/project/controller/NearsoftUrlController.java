package com.java.nearsoft.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.java.nearsoft.project.model.Alias;
import com.java.nearsoft.project.model.UrlModel;
import com.java.nearsoft.project.model.UrlModelList;
import com.java.nearsoft.project.repo.impl.UrlGeneratorRepoImpl;

import lombok.extern.log4j.Log4j;

/*
 * Class: NearsoftUrlController
 * Description: This class controller enables the RESTs Operations for the URL functoinality specified.
 */
@Log4j
@RestController
@RequestMapping("nearsoft/system/url-generator")
public class NearsoftUrlController {

	@Autowired
	UrlGeneratorRepoImpl urlGeneratorRepoImpl;
	
	@GetMapping("/getUrlByAlias/{url}")
	@ResponseStatus(HttpStatus.OK)
	public UrlModel getUrlByAlias(@PathVariable String url){
		log.info("getUrl Controller");
	 return urlGeneratorRepoImpl.getUrlByAlias(url);
	}
	
	@GetMapping("/getAllUrl")
	@ResponseStatus(HttpStatus.OK)
	public UrlModelList getAllUrl(){
		log.info("getUrl Controller");
	 return urlGeneratorRepoImpl.getAllUrl();
	}
	
	@PostMapping("/createUrl")
	@ResponseStatus(HttpStatus.OK)
	public Alias createUrl(@RequestBody UrlModel urlModel){
		log.info("createUrl Controller");
	 return urlGeneratorRepoImpl.createUrl(urlModel);
	}
	
	@DeleteMapping("/clearUrls")
	@ResponseStatus(HttpStatus.OK)
	public String clearUrls(){
		log.info("clearUrls Controller");
		return urlGeneratorRepoImpl.clearUrls();
	}
	
	@DeleteMapping("/deleteUrlByEngine/{engine}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUrlByEngine(@PathVariable String engine){
		log.info("deleteUrlByEngine Controller");
		return urlGeneratorRepoImpl.deleteUrlByEngine(engine);
	}
	
	@DeleteMapping("/deleteUrlByAlias/{alias}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteUrlByAlias(@PathVariable String alias){
		log.info("deleteUrlByAlias Controller");
		return urlGeneratorRepoImpl.deleteUrlByAlias(alias);
	}

}
