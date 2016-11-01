
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
    "showIcon",
    "icon",
    "userIcon",
    "iconColors"
})
public class Properties {

    @JsonProperty("showIcon")
    private ShowIcon showIcon;
    @JsonProperty("icon")
    private Icon icon;
    @JsonProperty("userIcon")
    private UserIcon userIcon;
    @JsonProperty("iconColors")
    private IconColors iconColors;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The showIcon
     */
    @JsonProperty("showIcon")
    public ShowIcon getShowIcon() {
        return showIcon;
    }

    /**
     * 
     * @param showIcon
     *     The showIcon
     */
    @JsonProperty("showIcon")
    public void setShowIcon(ShowIcon showIcon) {
        this.showIcon = showIcon;
    }

    /**
     * 
     * @return
     *     The icon
     */
    @JsonProperty("icon")
    public Icon getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    @JsonProperty("icon")
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The userIcon
     */
    @JsonProperty("userIcon")
    public UserIcon getUserIcon() {
        return userIcon;
    }

    /**
     * 
     * @param userIcon
     *     The userIcon
     */
    @JsonProperty("userIcon")
    public void setUserIcon(UserIcon userIcon) {
        this.userIcon = userIcon;
    }

    /**
     * 
     * @return
     *     The iconColors
     */
    @JsonProperty("iconColors")
    public IconColors getIconColors() {
        return iconColors;
    }

    /**
     * 
     * @param iconColors
     *     The iconColors
     */
    @JsonProperty("iconColors")
    public void setIconColors(IconColors iconColors) {
        this.iconColors = iconColors;
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
