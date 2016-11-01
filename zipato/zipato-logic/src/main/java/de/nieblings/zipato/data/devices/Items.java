
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
    "ref",
    "qualifiedType"
})
public class Items {

    @JsonProperty("type")
    private String type;
    @JsonProperty("ref")
    private Object ref;
    @JsonProperty("qualifiedType")
    private String qualifiedType;
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
     *     The ref
     */
    @JsonProperty("ref")
    public Object getRef() {
        return ref;
    }

    /**
     * 
     * @param ref
     *     The ref
     */
    @JsonProperty("ref")
    public void setRef(Object ref) {
        this.ref = ref;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
