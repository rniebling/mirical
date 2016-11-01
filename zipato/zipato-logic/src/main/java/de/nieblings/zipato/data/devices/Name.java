
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
    "type",
    "qualifiedType",
    "position",
    "required",
    "description",
    "allowableValues",
    "items"
})
public class Name {

    @JsonProperty("type")
    private String type;
    @JsonProperty("qualifiedType")
    private String qualifiedType;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("required")
    private Boolean required;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("allowableValues")
    private AllowableValues allowableValues;
    @JsonProperty("items")
    private Object items;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The qualifiedType
     */
    @JsonProperty("qualifiedType")
    public String getQualifiedType() {
        return qualifiedType;
    }

    /**
     * 
     * @param qualifiedType
     *     The qualifiedType
     */
    @JsonProperty("qualifiedType")
    public void setQualifiedType(String qualifiedType) {
        this.qualifiedType = qualifiedType;
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
     *     The allowableValues
     */
    @JsonProperty("allowableValues")
    public AllowableValues getAllowableValues() {
        return allowableValues;
    }

    /**
     * 
     * @param allowableValues
     *     The allowableValues
     */
    @JsonProperty("allowableValues")
    public void setAllowableValues(AllowableValues allowableValues) {
        this.allowableValues = allowableValues;
    }

    /**
     * 
     * @return
     *     The items
     */
    @JsonProperty("items")
    public Object getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    @JsonProperty("items")
    public void setItems(Object items) {
        this.items = items;
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
