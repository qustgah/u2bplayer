package com.ijiaban.u2bplayer.bean.v3;

public class Thumbnails {
	
	public String default_url;
	public String medium;
	public String high;
	
	public String standard;
	public String maxres;
	
	public Thumbnails() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Thumbnails(String default_url,String medium,String high) {
		// TODO 初始化
		this.default_url = default_url;
		this.medium = medium;
		this.high = high; 
	}
	public Thumbnails(String default_url,String medium,String high,String standard,String maxres) {
		// TODO 初始化
		this.default_url = default_url;
		this.medium = medium;
		this.high = high; 
	}
	
	
	public String getDefault_url() {
		return default_url;
	}
	public void setDefault_url(String default_url) {
		this.default_url = default_url;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}




	public String getStandard() {
		return standard;
	}




	public void setStandard(String standard) {
		this.standard = standard;
	}




	public String getMaxres() {
		return maxres;
	}
	public void setMaxres(String maxres) {
		this.maxres = maxres;
	}
	
	

}
