package com.java.nearsoft.project.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UrlModelList {
	
	Map<String, String> urlContent = new HashMap<String,String>();

}
