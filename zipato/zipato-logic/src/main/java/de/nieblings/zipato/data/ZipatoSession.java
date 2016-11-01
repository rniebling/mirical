package de.nieblings.zipato.data;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Cookie;

public class ZipatoSession {
	static String REST_URL = "https://my.zipato.com:443/zipato-web/v2/";

	
	private String jessionid;
	private Cookie cookie;
	private Client client;
	
	ZipatoSession( Client client){
	   this.client=client;
	}
	

	public String getJessionid() {
		return jessionid;
	}

	public void setJessionid(String jessionid) {
		 this.cookie=new Cookie("JSESSIONID",jessionid);
		 this.jessionid=jessionid;
	}

	public static String getREST_URL() {
		return REST_URL;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public Cookie getCookie() {
		return cookie;
	}


	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	};

}
