
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
    "apiVersion",
    "swaggerVersion",
    "basePath",
    "resourcePath",
    "produces",
    "consumes",
    "protocols",
    "authorizations",
    "apis",
    "models",
    "description",
    "position"
})
public class Devices {

    @JsonProperty("apiVersion")
    private String apiVersion;
    @JsonProperty("swaggerVersion")
    private String swaggerVersion;
    @JsonProperty("basePath")
    private String basePath;
    @JsonProperty("resourcePath")
    private String resourcePath;
    @JsonProperty("produces")
    private List<Object> produces = new ArrayList<Object>();
    @JsonProperty("consumes")
    private List<Object> consumes = new ArrayList<Object>();
    @JsonProperty("protocols")
    private List<Object> protocols = new ArrayList<Object>();
    @JsonProperty("authorizations")
    private List<Object> authorizations = new ArrayList<Object>();
    @JsonProperty("apis")
    private List<Api> apis = new ArrayList<Api>();
    @JsonProperty("models")
    private Models models;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("position")
    private Integer position;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The apiVersion
     */
    @JsonProperty("apiVersion")
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * 
     * @param apiVersion
     *     The apiVersion
     */
    @JsonProperty("apiVersion")
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 
     * @return
     *     The swaggerVersion
     */
    @JsonProperty("swaggerVersion")
    public String getSwaggerVersion() {
        return swaggerVersion;
    }

    /**
     * 
     * @param swaggerVersion
     *     The swaggerVersion
     */
    @JsonProperty("swaggerVersion")
    public void setSwaggerVersion(String swaggerVersion) {
        this.swaggerVersion = swaggerVersion;
    }

    /**
     * 
     * @return
     *     The basePath
     */
    @JsonProperty("basePath")
    public String getBasePath() {
        return basePath;
    }

    /**
     * 
     * @param basePath
     *     The basePath
     */
    @JsonProperty("basePath")
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * 
     * @return
     *     The resourcePath
     */
    @JsonProperty("resourcePath")
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * 
     * @param resourcePath
     *     The resourcePath
     */
    @JsonProperty("resourcePath")
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
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
    public List<Object> getAuthorizations() {
        return authorizations;
    }

    /**
     * 
     * @param authorizations
     *     The authorizations
     */
    @JsonProperty("authorizations")
    public void setAuthorizations(List<Object> authorizations) {
        this.authorizations = authorizations;
    }

    /**
     * 
     * @return
     *     The apis
     */
    @JsonProperty("apis")
    public List<Api> getApis() {
        return apis;
    }

    /**
     * 
     * @param apis
     *     The apis
     */
    @JsonProperty("apis")
    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

    /**
     * 
     * @return
     *     The models
     */
    @JsonProperty("models")
    public Models getModels() {
        return models;
    }

    /**
     * 
     * @param models
     *     The models
     */
    @JsonProperty("models")
    public void setModels(Models models) {
        this.models = models;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
