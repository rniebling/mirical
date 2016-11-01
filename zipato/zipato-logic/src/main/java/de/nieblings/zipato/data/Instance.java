package de.nieblings.zipato.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Instance")
@XmlAccessorType (XmlAccessType.FIELD)
public class Instance implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 7466629342028357855L;
private String eventType;

  public String getEventType() { return this.eventType; }

  public void setEventType(String eventType) { this.eventType = eventType; }

  private String code;

  public String getCode() { return this.code; }

  public void setCode(String code) { this.code = code; }
  @XmlElement(name="id")
  private String transactionId;

  public String getTransactionId() { return this.transactionId; }

  public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

  private String dataJson;

  public String getDataJson() { return this.dataJson; }

  public void setDataJson(String dataJson) { this.dataJson = dataJson; }

  private String message;

  public String getMessage() { return this.message; }

  public void setMessage(String message) { this.message = message; }

  private String type;

  public String getType() { return this.type; }

  public void setType(String type) { this.type = type; }

  private String level;

  public String getLevel() { return this.level; }

  public void setLevel(String level) { this.level = level; }

  private String updateType;

  public String getUpdateType() { return this.updateType; }

  public void setUpdateType(String updateType) { this.updateType = updateType; }

  private String clientSessionId;

  public String getClientSessionId() { return this.clientSessionId; }

  public void setClientSessionId(String clientSessionId) { this.clientSessionId = clientSessionId; }

  private String timestamp;

  public String getTimestamp() { return this.timestamp; }

  public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}

