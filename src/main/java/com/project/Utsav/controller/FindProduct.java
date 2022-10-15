package com.project.Utsav.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.json.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonElement;

@RestController
public class FindProduct {

	@GetMapping("/getProducts/{productName}")
	public ArrayList<HashMap<String,Object>> getProducts(@PathVariable("productName") String productName){
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://compare3.p.rapidapi.com/search/"+productName;
	    Map<String, String> params = new HashMap<String, String>();
//	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
//	                                        .queryParam("productName",);
	    HttpHeaders headers = new HttpHeaders();
    	headers.set("X-RapidAPI-Key", "");
    	headers.set("X-RapidAPI-Host", "");
    	HttpEntity request = new HttpEntity(headers);
//	    String uriBuilder = builder.build().encode().toUriString();
	    ResponseEntity<String> response = restTemplate.exchange(url , HttpMethod.GET, request,
	                    String.class);
	    JSONParser parser = new JSONParser();
	    ArrayList<HashMap<String,Object>> listOfProductDetails = new ArrayList<HashMap<String,Object>>();
		try {
			Object productObject = (Object) parser.parse(response.getBody());
			JSONArray productJsonArray = (JSONArray) productObject;
			
			for(int i=0; i<productJsonArray.size();i++) {
				JSONObject jsonObject = (JSONObject) productJsonArray.get(i);
				Set<Map.Entry<String, JSONArray>> productEntity = jsonObject.entrySet();
				int p=0;
				for(Map.Entry<String, JSONArray> productEntityObject : productEntity) {
					HashMap<String, Object> tempObject = new HashMap<String,Object>();
					JSONObject productJsonObjectChild = (JSONObject) productEntityObject.getValue().get(0);
					Set<Map.Entry<String, JSONArray>> productJsonObjectChildInstance = productJsonObjectChild.entrySet();
					for(Entry<String, JSONArray> productJsonObjectChildInstanceIndex : productJsonObjectChildInstance) {
//						System.out.println(entr.getKey() +" --->"+entr.getValue());
						
						tempObject.put(productJsonObjectChildInstanceIndex.getKey(), 
								(Object)productJsonObjectChildInstanceIndex.getValue());
						
					}
						
					listOfProductDetails.add(tempObject);
				}
//				System.out.println(listOfProductDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return listOfProductDetails;
	}
	
	
}
