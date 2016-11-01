package de.nieblings.zipato.data.attribut;

import java.util.Date;

public class Value
{
  private String value;

  public String getValue() { return this.value; }

  public void setValue(String value) { this.value = value; }

  private Date timestamp;

  public Date getTimestamp() { return this.timestamp; }

  public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
