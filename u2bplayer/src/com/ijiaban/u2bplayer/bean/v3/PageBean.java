package com.ijiaban.u2bplayer.bean.v3;

import java.util.List;

import org.json.JSONArray;

public class PageBean {
	public int totalResults;
	public int resultsPerPage;
	public String nextPageToken;
	public String prePageToken;
	
	public  JSONArray jsonArray;
	
	
	public PageBean() {
		// TODO  
	}
	public PageBean(int totalResults,int resultsPerPage,String nextPageToken, String prePageToken,JSONArray jsonArray ) {
		// TODO  ≥ı ºªØ
		this.totalResults = totalResults;
		this.resultsPerPage = resultsPerPage;
		this.nextPageToken = nextPageToken;
		this.prePageToken = prePageToken;
		this.jsonArray = jsonArray;
	} 
	
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public int getResultsPerPage() {
		return resultsPerPage;
	}
	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	public String getNextPageToken() {
		return nextPageToken;
	}
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	public String getPrePageToken() {
		return prePageToken;
	}
	public void setPrePageToken(String prePageToken) {
		this.prePageToken = prePageToken;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	} 
}
