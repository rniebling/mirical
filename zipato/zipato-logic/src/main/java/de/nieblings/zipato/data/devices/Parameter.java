
package de.nieblings.zipato.data.devices;

import java.util.HashMap;
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
    "name",
    "description",
    "defaultValue",
    "required",
    "allowMultiple",
    "dataType",
    "allowableValues",
    "paramType",
    "paramAccess"
})
public class Parameter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("defaultValue")
    private Object defaultValue;
    @JsonProperty("required")
    private Boolean required;
    @JsonProperty("allowMultiple")
    private Boolean allowMultiple;
    @JsonProperty("dataType")
    private String dataType;
    @JsonProperty("allowableValues")
    private Object allowableValues;
    @JsonProperty("paramType")
    private String paramType;
    @JsonProperty("paramAccess")
    private Object paramAccess;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The defaultValue
     */
    @JsonProperty("defaultValue")
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * 
     * @param defaultValue
     *     The defaultValue
     */
    @JsonProperty("defaultValue")
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 
     * @return
     *     The required
     */
    @JsonProperty("required")
    public Boolean getRequired() {
        return required;
    }

    /**
     * 
     * @param required
     *     The required
     */
    @JsonProperty("required")
    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * 
     * @return
     *     The allowMultiple
     */
    @JsonProperty("allowMultiple")
    public Boolean getAllowMultiple() {
        return allowMultiple;
    }

    /**
     * 
     * @param allowMultiple
     *     The allowMultiple
     */
    @JsonProperty("allowMultiple")
    public void setAllowMultiple(Boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
    }

    /**
     * 
     * @return
     *     The dataType
     */
    @JsonProperty("dataType")
    public String getDataType() {
        return dataType;
    }

    /**
     * 
     * @param dataType
     *     The dataType
     */
    @JsonProperty("dataType")
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 
     * @return
     *     The allowableValues
     */
    @JsonProperty("allowableValues")
    public Object getAllowableValues() {
        return allowableValues;
    }

    /**
     * 
     * @param allowableValues
     *     The allowableValues
     */
    @JsonProperty("allowableValues")
    public void setAllowableValues(Object allowableValues) {
        this.allowableValues = allowableValues;
    }

    /**
     * 
     * @return
     *     The paramType
     */
    @JsonProperty("paramType")
    public String getParamType() {
        return paramType;
    }

    /**
     * 
     * @param paramType
     *     The paramType
     */
    @JsonProperty("paramType")
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * 
     * @return
     *     The paramAccess
     */
    @JsonProperty("paramAccess")
    public Object getParamAccess() {
        return paramAccess;
    }

    /**
     * 
     * @param paramAccess
     *     The paramAccess
     */
    @JsonProperty("paramAccess")
    public void setParamAccess(Object paramAccess) {
        this.paramAccess = paramAccess;
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
