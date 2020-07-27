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

/*
 * Class: NearSoftUtils
 * Description: This class contains useful operations to create, get and delete information from the Nearsoft Service.
 */
@Log4j
@Service
public class NearSoftUtils {

	/*
	 * Method: removeVowels
	 * Description: It removes Vowels from the provided string.
	 * Arguments: alias
	 * Returns: String
	 */
	public static String removeVowels(String alias) {
		String newAlias;
		newAlias =  alias.replaceAll("(?iu)[aeiouáéíóúü]", "").trim();
		return newAlias;
	}
	
	/*
	 * Method: removeSpecialCharacters
	 * Description: It removes Special Characters from the provided string.
	 * Arguments: special
	 * Returns: String
	 */
	public static String removeSpecialCharacters(String special) {
		String result = special.replaceAll("[^\\p{L}\\p{Z}]","");
		return result;
	}
	
	/*
	 * Method: removeNumbers
	 * Description: It removes numbers from the provided string.
	 * Arguments: special
	 * Returns: String
	 */
	public static String removeNumbers(String special) {
		String result = special.replaceAll("\\d","");
		return result;
	}
	
	/*
	 * Method: generateRandomString
	 * Description: It generates a random string using from 0 to z.
	 * Arguments: No args
	 * Returns: Random String
	 */
	public static String generateRandomString() {
	    int leftLimit = 48; // numeral '0' from ascii
	    int rightLimit = 122; // letter 'z' from ascii
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
	
	/*
	 * Method: get5AlphaString
	 * Description: It gets only 5 char from the provided String specified for google process
	 * Arguments: alpha
	 * Returns: 5 char String
	 */
	public static String get5AlphaString(String alpha) {
		String fiveChar = alpha.substring(0, Math.min(alpha.length(), 5));
		return fiveChar;
	}
	
	/*
	 * Method: get7AlphaNumericString
	 * Description: It gets only 7 char from the provided String specified for yahoo process
	 * Arguments: alpha
	 * Returns: 7 char String
	 */
	public static String get7AlphaNumericString(String alpha) {
		String sevenChar = alpha.substring(0, Math.min(alpha.length(), 7));
		return sevenChar;
	}
	
	/*
	 * Method: getAlias
	 * Description: It gets the alias based on the provided engine
	 * Arguments: UrlModel
	 * Returns: Alias Object
	 */
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
			NearSoftApp.aliasMap.put(urlModel.getUrl(), alias.getAlias());
		}
		else {
			alias.setAlias(NearSoftApp.aliasMap.get(urlModel.getUrl()));
		}
		
		return alias;
	}
	
	/*
	 * Method: getUrlFromAlias
	 * Description: It gets the url based on the provided alias
	 * Arguments: alias
	 * Returns: UrlModel
	 */
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
		}
		return urlModel;
	}
	
	/*
	 * Method: getAllUrl
	 * Description: It gets the url map kept in memory
	 * Arguments: No Args
	 * Returns: UrlModelList
	 */
	public static UrlModelList getAllUrl() {
		UrlModelList urlModelList = new UrlModelList();
		urlModelList.setUrlContent(NearSoftApp.aliasMap);
		return urlModelList;
	}
	
	/*
	 * Method: clearUrlList
	 * Description: It clears the url map kept in memory
	 * Arguments: No Args
	 * Returns: String
	 */
	public static String clearUrlList() {
		UrlModelList urlModelList = new UrlModelList();
		NearSoftApp.aliasMap = urlModelList.getUrlContent();
		return NearSoftConstants.URL_LIST_CLEARED;
	}
	
	/*
	 * Method: deleteUrlByEngine
	 * Description: It deletes the url based on the engine provided
	 * Arguments: engine
	 * Returns: String
	 */
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
	
	/*
	 * Method: deleteUrlByAlias
	 * Description: It deletes the url based on the alias provided
	 * Arguments: engine
	 * Returns: String
	 */
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
	
	
	/*
	 * Method: getGoogleAlias
	 * Description: It gets the google alias
	 * Arguments: No Args
	 * Returns: String
	 */
	public static String getGoogleAlias() {
		String aliasGoogle;
		aliasGoogle = NearSoftUtils.generateRandomString();
		aliasGoogle = NearSoftUtils.removeNumbers(aliasGoogle);
		aliasGoogle = NearSoftUtils.get5AlphaString(aliasGoogle);
		return aliasGoogle;
	}
	
	/*
	 * Method: getYahooAlias
	 * Description: It gets the yahoo alias
	 * Arguments: No Args
	 * Returns: String
	 */
	public static String getYahooAlias() {
		String aliasYahoo;
		aliasYahoo = NearSoftUtils.generateRandomString();
		aliasYahoo = NearSoftUtils.get7AlphaNumericString(aliasYahoo);
		return aliasYahoo;
	}
	
	/*
	 * Method: getOtherAlias
	 * Description: It gets the alias different from google or yahoo
	 * Arguments: No Args
	 * Returns: String
	 */
	public static String getOtherAlias(String url) {
		String aliasOther=url;
		aliasOther  = NearSoftUtils.removeVowels(aliasOther);
		aliasOther  = NearSoftUtils.removeSpecialCharacters(aliasOther);
		aliasOther  = NearSoftUtils.removeSpecialCharacters(aliasOther);
		return aliasOther;
	}


}
