package com.ijiaban.u2bplayer.bean.v3;
/**
 * https://www.googleapis.com/youtube/v3/i18nRegions?part=snippet&key={YOUR_API_KEY} 
 * {
 "kind": "youtube#i18nRegionListResponse",
 "etag": "\"gMjDJfS6nsym0T-NKCXALC_u_rM/KMPDGzAo8EyiG_aXap8NiR1qeAo\"",
 "items": [
  {
   "kind": "youtube#i18nRegion",
   "etag": "\"gMjDJfS6nsym0T-NKCXALC_u_rM/CLiuk5zYz2ASxM5L8dZ2bDT8N5c\"",
   "id": "US",
   "snippet": {
    "gl": "US",
    "name": "Worldwide (All)"
   }
  },
 * @author Administrator
 *
 */
public class I18nRegions {
	public String id;
	public String gl;
	public String name;
	
	public I18nRegions() {
		// TODO Auto-generated constructor stub
	}
	public I18nRegions(String id,String gl,String name) {
		// TODO  
		this.id = id;
		this.gl = gl;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getGl() {
		return gl;
	}
	public void setGl(String gl) {
		this.gl = gl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
}
