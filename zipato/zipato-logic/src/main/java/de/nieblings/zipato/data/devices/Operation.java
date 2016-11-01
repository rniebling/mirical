
package de.nieblings.zipato.data.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "method",
    "summary",
    "notes",
    "responseClass",
    "nickname",
    "position",
    "produces",
    "consumes",
    "protocols",
    "authorizations",
    "parameters",
    "responseMessages",
    "deprecated"
})
public class Operation {

    @JsonProperty("method")
    private String method;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("responseClass")
    private String responseClass;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("produces")
    private List<Object> produces = new ArrayList<Object>();
    @JsonProperty("consumes")
    private List<Object> consumes = new ArrayList<Object>();
    @JsonProperty("protocols")
    private List<Object> protocols = new ArrayList<Object>();
    @JsonProperty("authorizations")
    private List<Authorization> authorizations = new ArrayList<Authorization>();
    @JsonProperty("parameters")
    private List<Parameter> parameters = new ArrayList<Parameter>();
    @JsonProperty("responseMessages")
    private List<Object> responseMessages = new ArrayList<Object>();
    @JsonProperty("deprecated")
    private Object deprecated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The method
     */
    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     *     The method
     */
    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 
     * @return
     *     The summary
     */
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The notes
     */
    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    /**
     * 
     * @param notes
     *     The notes
     */
    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 
     * @return
     *     The responseClass
     */
    @JsonProperty("responseClass")
    public String getResponseClass() {
        return responseClass;
    }

    /**
     * 
     * @param responseClass
     *     The responseClass
     */
    @JsonProperty("responseClass")
    public void setResponseClass(String responseClass) {
        this.responseClass = responseClass;
    }

    /**
     * 
     * @return
     *     The nickname
     */
    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     * @param nickname
     *     The nickname
     */
    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 
     * @return
     *     The position
     */
    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The produces
     */
    @JsonProperty("produces")
    public List<Object> getProduces() {
        return produces;
    }

    /**
     * 
     * @param produces
     *     The produces
     */
    @JsonProperty("produces")
    public void setProduces(List<Object> produces) {
        this.produces = produces;
    }

    /**
     * 
     * @return
     *     The consumes
     */
    @JsonProperty("consumes")
    public List<Object> getConsumes() {
        return consumes;
    }

    /**
     * 
     * @param consumes
     *     The consumes
     */
    @JsonProperty("consumes")
    public void setConsumes(List<Object> consumes) {
        this.consumes = consumes;
    }

    /**
     * 
     * @return
     *     The protocols
     */
    @JsonProperty("protocols")
    public List<Object> getProtocols() {
        return protocols;
    }

    /**
     * 
     * @param protocols
     *     The protocols
     */
    @JsonProperty("protocols")
    public void setProtocols(List<Object> protocols) {
        this.protocols = protocols;
    }

    /**
     * 
     * @return
     *     The authorizations
     */
    @JsonProperty("authorizations")
    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    /**
     * 
     * @param authorizations
     *     The authorizations
     */
    @JsonProperty("authorizations")
    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }

    /**
     * 
     * @return
     *     The parameters
     */
    @JsonProperty("parameters")
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * 
     * @param parameters
     *     The parameters
     */
    @JsonProperty("parameters")
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * 
     * @return
     *     The responseMessages
     */
    @JsonProperty("responseMessages")
    public List<Object> getResponseMessages() {
        return responseMessages;
    }

    /**
     * 
     * @param responseMessages
     *     The responseMessages
     */
    @JsonProperty("responseMessages")
    public void setResponseMessages(List<Object> responseMessages) {
        this.responseMessages = responseMessages;
    }

    /**
     * 
     * @return
     *     The deprecated
     */
    @JsonProperty("deprecated")
    public Object getDeprecated() {
        return deprecated;
    }

    /**
     * 
     * @param deprecated
     *     The deprecated
     */
    @JsonProperty("deprecated")
    public void setDeprecated(Object deprecated) {
        this.deprecated = deprecated;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
