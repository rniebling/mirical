
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
    "id",
    "name",
    "qualifiedType",
    "properties",
    "description",
    "baseModel",
    "discriminator",
    "subTypes"
})
public class NameAndRoom {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("qualifiedType")
    private String qualifiedType;
    @JsonProperty("properties")
    private Properties__ properties;
    @JsonProperty("description")
    private String description;
    @JsonProperty("baseModel")
    private String baseModel;
    @JsonProperty("discriminator")
    private Object discriminator;
    @JsonProperty("subTypes")
    private List<Object> subTypes = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

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
     *     The properties
     */
    @JsonProperty("properties")
    public Properties__ getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *     The properties
     */
    @JsonProperty("properties")
    public void setProperties(Properties__ properties) {
        this.properties = properties;
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
     *     The baseModel
     */
    @JsonProperty("baseModel")
    public String getBaseModel() {
        return baseModel;
    }

    /**
     * 
     * @param baseModel
     *     The baseModel
     */
    @JsonProperty("baseModel")
    public void setBaseModel(String baseModel) {
        this.baseModel = baseModel;
    }

    /**
     * 
     * @return
     *     The discriminator
     */
    @JsonProperty("discriminator")
    public Object getDiscriminator() {
        return discriminator;
    }

    /**
     * 
     * @param discriminator
     *     The discriminator
     */
    @JsonProperty("discriminator")
    public void setDiscriminator(Object discriminator) {
        this.discriminator = discriminator;
    }

    /**
     * 
     * @return
     *     The subTypes
     */
    @JsonProperty("subTypes")
    public List<Object> getSubTypes() {
        return subTypes;
    }

    /**
     * 
     * @param subTypes
     *     The subTypes
     */
    @JsonProperty("subTypes")
    public void setSubTypes(List<Object> subTypes) {
        this.subTypes = subTypes;
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
