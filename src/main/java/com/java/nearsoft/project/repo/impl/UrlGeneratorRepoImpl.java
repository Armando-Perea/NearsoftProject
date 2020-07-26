package com.java.nearsoft.project.repo.impl;

import org.springframework.stereotype.Repository;

import com.java.nearsoft.project.model.Alias;
import com.java.nearsoft.project.model.UrlModel;
import com.java.nearsoft.project.model.UrlModelList;
import com.java.nearsoft.project.repository.UrlGeneratorRepository;
import com.java.nearsoft.project.util.NearSoftUtils;


@Repository
public class UrlGeneratorRepoImpl implements UrlGeneratorRepository {

	
	@Override
	public Alias createUrl(UrlModel urlModel) {
		return NearSoftUtils.getAlias(urlModel);
	}

	@Override
	public UrlModel getUrl(String alias) {
		return NearSoftUtils.getUrlFromAlias(alias);
	}
	
	@Override
	public UrlModelList getAllUrl() {
		return NearSoftUtils.getAllUrl();
	}

	@Override
	public String clearUrls() {
		return NearSoftUtils.clearUrlList();
	}

	@Override
	public String deleteUrlByEngine(String engine) {
		return NearSoftUtils.deleteUrlByEngine(engine);
	}
	
	@Override
	public String deleteUrlByAlias(String alias) {
		return NearSoftUtils.deleteUrlByAlias(alias);
	}
	
}
