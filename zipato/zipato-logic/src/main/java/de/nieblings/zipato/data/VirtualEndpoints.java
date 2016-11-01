package de.nieblings.zipato.data;

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
"uuid",
"room",
"name",
"link",
"order",
"description",
"tags",
"extra"
})
public class VirtualEndpoints {

@JsonProperty("uuid")
private String uuid;
@JsonProperty("room")
private String room;
@JsonProperty("name")
private String name;
@JsonProperty("link")
private String link;
@JsonProperty("order")
private String order;
@JsonProperty("description")
private String description;
@JsonProperty("tags")
private List<String> tags = new ArrayList<String>();
@JsonProperty("extra")
private String extra;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
*
* @return
* The uuid
*/
@JsonProperty("uuid")
public String getUuid() {
return uuid;
}

/**
*
* @param uuid
* The uuid
*/
@JsonProperty("uuid")
public void setUuid(String uuid) {
this.uuid = uuid;
}

/**
*
* @return
* The room
*/
@JsonProperty("room")
public String getRoom() {
return room;
}

/**
*
* @param room
* The room
*/
@JsonProperty("room")
public void setRoom(String room) {
this.room = room;
}

/**
*
* @return
* The name
*/
@JsonProperty("name")
public String getName() {
return name;
}

/**
*
* @param name
* The name
*/
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

/**
*
* @return
* The link
*/
@JsonProperty("link")
public String getLink() {
return link;
}

/**
*
* @param link
* The link
*/
@JsonProperty("link")
public void setLink(String link) {
this.link = link;
}

/**
*
* @return
* The order
*/
@JsonProperty("order")
public String getOrder() {
return order;
}

/**
*
* @param order
* The order
*/
@JsonProperty("order")
public void setOrder(String order) {
this.order = order;
}

/**
*
* @return
* The description
*/
@JsonProperty("description")
public String getDescription() {
return description;
}

/**
*
* @param description
* The description
*/
@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

/**
*
* @return
* The tags
*/
@JsonProperty("tags")
public List<String> getTags() {
return tags;
}

/**
*
* @param tags
* The tags
*/
@JsonProperty("tags")
public void setTags(List<String> tags) {
this.tags = tags;
}

/**
*
* @return
* The extra
*/
@JsonProperty("extra")
public String getExtra() {
return extra;
}

/**
*
* @param extra
* The extra
*/
@JsonProperty("extra")
public void setExtra(String extra) {
this.extra = extra;
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