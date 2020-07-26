package com.java.nearsoft.project.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.java.nearsoft.project.NearSoftApp;
import com.java.nearsoft.project.model.Alias;
import com.java.nearsoft.project.model.UrlModel;
import com.java.nearsoft.project.model.UrlModelList;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class NearSoftUtils {
	
	
	public static String setEngine(UrlModel urlModel) {
		String engine = urlModel.getUrl().contains(NearSoftConstants.GOOGLE_ENGINE) ? NearSoftConstants.GOOGLE_ENGINE : 
						(urlModel.getUrl().contains(NearSoftConstants.YAHOO_ENGINE) ? NearSoftConstants.YAHOO_ENGINE : 
						NearSoftConstants.OTHER_ENGINE);
		return engine;
	}
	
	public static String removeVowels(String alias) {
		String newAlias;
		newAlias =  alias.replaceAll("(?iu)[aeiouáéíóúü]", "").trim();
		return newAlias;
	}
	
	public static String removeSpecialCharacters(String special) {
		String result = special.replaceAll("[^\\p{L}\\p{Z}]","");
		return result;
	}
	
	public static String removeNumbers(String special) {
		String result = special.replaceAll("\\d","");
		return result;
	}
	
	public static String generateRandomString() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 20;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    double randomDouble = Math.random();
	    randomDouble = randomDouble * 50 + 1;
	    int randomInt = (int) randomDouble;
	    generatedString = randomInt+generatedString;
	    return generatedString;
	}
	
	public static String get5AlphaString(String alpha) {
		String fiveChar = alpha.substring(0, Math.min(alpha.length(), 5));
		return fiveChar;
	}
	
	public static String get7AlphaNumericString(String alpha) {
		String sevenChar = alpha.substring(0, Math.min(alpha.length(), 7));
		return sevenChar;
	}
	
	public static Alias getAlias(UrlModel urlModel) {
		Alias alias = new Alias();
		log.info("urlModel: "+urlModel.toString());
		boolean alreadyExists = NearSoftApp.aliasMap.containsKey(urlModel.getUrl());
		if(!alreadyExists) {
			if(urlModel.getUrl().contains(NearSoftConstants.GOOGLE_ENGINE)) {
				alias.setAlias(NearSoftUtils.getGoogleAlias());
			}
			else if(urlModel.getUrl().contains(NearSoftConstants.YAHOO_ENGINE)) {
				alias.setAlias(NearSoftUtils.getYahooAlias());
			}
			else {
				alias.setAlias(NearSoftUtils.getOtherAlias(urlModel.getUrl()));
			}
			NearSoftApp.aliasMap.put(NearSoftUtils.removeSpecialCharacters(urlModel.getUrl()), alias.getAlias());
		}
		else {
			alias.setAlias(NearSoftApp.aliasMap.get(urlModel.getUrl()));
		}
		
		return alias;
	}
	
	public static UrlModel getUrlFromAlias(String alias) {
		UrlModel urlModel = new UrlModel();
		boolean alreadyExists = NearSoftApp.aliasMap.containsValue(alias);
		if(!alreadyExists) {
			urlModel.setUrl(NearSoftConstants.URL_NOT_FOUND);
		}
		else {

			Set<String> keys = new HashSet<>();
			for (Entry<String, String> entry : NearSoftApp.aliasMap.entrySet()) {
		        if (entry.getValue().equals(alias)) {
		            keys.add(entry.getKey());
		            urlModel.setUrl(entry.getKey());
		        }
		    }
			System.out.println("urlModel: "+urlModel.toString());
		}
		
		return urlModel;
	}
	
	public static UrlModelList getAllUrl() {
		UrlModelList urlModelList = new UrlModelList();
		urlModelList.setUrlContent(NearSoftApp.aliasMap);
		return urlModelList;
	}
	
	public static String clearUrlList() {
		UrlModelList urlModelList = new UrlModelList();
		NearSoftApp.aliasMap = urlModelList.getUrlContent();
		return NearSoftConstants.URL_LIST_CLEARED;
	}
	
	public static String deleteUrlByEngine(String engine) {
		boolean existsKey = NearSoftApp.aliasMap.containsKey(engine);
		String response=NearSoftConstants.URL_NOT_ON_LIST;
		if(existsKey) {
			Iterator<Entry<String, String>> aliasIterator = NearSoftApp.aliasMap.entrySet().iterator();
			while (aliasIterator.hasNext()) {
			    Map.Entry<String, String> entry = aliasIterator.next();
			    if(entry.getKey().equals(engine)){
			    	aliasIterator.remove();
			    	return NearSoftConstants.URL_REMOVED;
			    }   
			}
		}
		return response;
	}
	
	public static String deleteUrlByAlias(String alias) {
		boolean existsValue = NearSoftApp.aliasMap.containsValue(alias);
		String response=NearSoftConstants.ALIAS_NOT_ON_LIST;
		if(existsValue) {
			Iterator<Entry<String, String>> aliasIterator = NearSoftApp.aliasMap.entrySet().iterator();
			while (aliasIterator.hasNext()) {
			    Map.Entry<String, String> entry = aliasIterator.next();
			    if(entry.getValue().equals(alias)){
			    	aliasIterator.remove();
			    	return NearSoftConstants.URL_REMOVED;
			    }
			}
		}
		return response;
	}
	
	public static String getGoogleAlias() {
		String aliasGoogle;
		aliasGoogle = NearSoftUtils.generateRandomString();
		aliasGoogle = NearSoftUtils.removeNumbers(aliasGoogle);
		aliasGoogle = NearSoftUtils.get5AlphaString(aliasGoogle);
		return aliasGoogle;
	}
	
	public static String getYahooAlias() {
		String aliasYahoo;
		aliasYahoo = NearSoftUtils.generateRandomString();
		aliasYahoo = NearSoftUtils.get7AlphaNumericString(aliasYahoo);
		return aliasYahoo;
	}
	
	public static String getOtherAlias(String url) {
		String aliasOther=url;
		aliasOther  = NearSoftUtils.removeVowels(aliasOther);
		aliasOther  = NearSoftUtils.removeSpecialCharacters(aliasOther);
		aliasOther  = NearSoftUtils.removeSpecialCharacters(aliasOther);
		return aliasOther;
	}

//	public static void main(String[] args) {
//		String randomGoogle;
//		randomGoogle = NearSoftUtils.generateRandomString();
//		randomGoogle = NearSoftUtils.removeNumbers(randomGoogle);
//		randomGoogle = NearSoftUtils.get5AlphaString(randomGoogle);
//		System.out.println("FOR GOOGLE: "+randomGoogle);
//		System.out.println();
//		String randomYahoo;
//		randomYahoo = NearSoftUtils.generateRandomString();
//		randomYahoo = NearSoftUtils.get7AlphaNumericString(randomYahoo);
//		System.out.println("FOR YAHOO : "+randomYahoo);
//		System.out.println();
//		String randomOther="www.0c13an$sde%e23ps&.c0m";
//		randomOther  = NearSoftUtils.removeVowels(randomOther);
//		randomOther  = NearSoftUtils.removeSpecialCharacters(randomOther);
//		randomOther  = NearSoftUtils.removeSpecialCharacters(randomOther);
//		System.out.println("FOR OTHER : "+randomOther);
//	}

}
