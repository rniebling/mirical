
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
    "IconConfig",
    "ZipaboxConfigObject",
    "NameAndRoom"
})
public class Models {

    @JsonProperty("IconConfig")
    private de.nieblings.zipato.data.devices.IconConfig IconConfig;
    @JsonProperty("ZipaboxConfigObject")
    private de.nieblings.zipato.data.devices.ZipaboxConfigObject ZipaboxConfigObject;
    @JsonProperty("NameAndRoom")
    private de.nieblings.zipato.data.devices.NameAndRoom NameAndRoom;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The IconConfig
     */
    @JsonProperty("IconConfig")
    public de.nieblings.zipato.data.devices.IconConfig getIconConfig() {
        return IconConfig;
    }

    /**
     * 
     * @param IconConfig
     *     The IconConfig
     */
    @JsonProperty("IconConfig")
    public void setIconConfig(de.nieblings.zipato.data.devices.IconConfig IconConfig) {
        this.IconConfig = IconConfig;
    }

    /**
     * 
     * @return
     *     The ZipaboxConfigObject
     */
    @JsonProperty("ZipaboxConfigObject")
    public de.nieblings.zipato.data.devices.ZipaboxConfigObject getZipaboxConfigObject() {
        return ZipaboxConfigObject;
    }

    /**
     * 
     * @param ZipaboxConfigObject
     *     The ZipaboxConfigObject
     */
    @JsonProperty("ZipaboxConfigObject")
    public void setZipaboxConfigObject(de.nieblings.zipato.data.devices.ZipaboxConfigObject ZipaboxConfigObject) {
        this.ZipaboxConfigObject = ZipaboxConfigObject;
    }

    /**
     * 
     * @return
     *     The NameAndRoom
     */
    @JsonProperty("NameAndRoom")
    public de.nieblings.zipato.data.devices.NameAndRoom getNameAndRoom() {
        return NameAndRoom;
    }

    /**
     * 
     * @param NameAndRoom
     *     The NameAndRoom
     */
    @JsonProperty("NameAndRoom")
    public void setNameAndRoom(de.nieblings.zipato.data.devices.NameAndRoom NameAndRoom) {
        this.NameAndRoom = NameAndRoom;
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
