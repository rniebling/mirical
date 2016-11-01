package de.nieblings.zipato.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "username", "token", "serial" })
public class LoginRequest {
	@JsonProperty("username")	
   private String username;
	@JsonProperty("token")	
   private String token;
	@JsonProperty("serial")	
   private String serial;
   
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String getSerial() {
	return serial;
}
public void setSerial(String serial) {
	this.serial = serial;
}
}
