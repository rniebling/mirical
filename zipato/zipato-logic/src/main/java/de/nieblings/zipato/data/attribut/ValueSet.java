package de.nieblings.zipato.data.attribut;

public class ValueSet {
	
	  private String value;
	  private String timestamp;
	  private String pendingValue;
	  private String pendingTimestamp;

	  public String getValue() { return this.value; }

	  public void setValue(String value) { this.value = value; }


	  public String getTimestamp() { return this.timestamp; }

	  public void setTimestamp(String timestamp) { this.timestamp = timestamp; }


	  public String getPendingValue() { return this.pendingValue; }

	  public void setPendingValue(String pendingValue) { this.pendingValue = pendingValue; }

	  public String getPendingTimestamp() { return this.pendingTimestamp; }

	  public void setPendingTimestamp(String pendingTimestamp) { this.pendingTimestamp = pendingTimestamp; }
	
	
}
