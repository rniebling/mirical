package de.nieblings.zipato.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success","jsessionid", "nonce", "errors",  "error" })
public class LoginResponse implements Serializable{

	private static final long serialVersionUID = -8197670379199877894L;
	@JsonProperty("jsessionid")	
	private String jsessionid;
	@JsonProperty("nonce")
	private String nonce;
	@JsonProperty("errors")
	private List<String> errors = new ArrayList<String>();
	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("error")
	private String error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The jsessionid
	 */
	@JsonProperty("jsessionid")
	public String getJsessionid() {
		return jsessionid;
	}

	/**
	 *
	 * @param jsessionid
	 *            The jsessionid
	 */
	@JsonProperty("jsessionid")
	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public LoginResponse withJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
		return this;
	}

	/**
	 *
	 * @return The nonce
	 */
	@JsonProperty("nonce")
	public String getNonce() {
		return nonce;
	}

	/**
	 *
	 * @param nonce
	 *            The nonce
	 */
	@JsonProperty("nonce")
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public LoginResponse withNonce(String nonce) {
		this.nonce = nonce;
		return this;
	}

	/**
	 *
	 * @return The errors
	 */
	@JsonProperty("errors")
	public List<String> getErrors() {
		return errors;
	}

	/**
	 *
	 * @param errors
	 *            The errors
	 */
	@JsonProperty("errors")
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public LoginResponse withErrors(List<String> errors) {
		this.errors = errors;
		return this;
	}

	/**
	 *
	 * @return The success
	 */
	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	/**
	 *
	 * @param success
	 *            The success
	 */
	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public LoginResponse withSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	/**
	 *
	 * @return The error
	 */
	@JsonProperty("error")
	public String getError() {
		return error;
	}

	/**
	 *
	 * @param error
	 *            The error
	 */
	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

	public LoginResponse withError(String error) {
		this.error = error;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public LoginResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
