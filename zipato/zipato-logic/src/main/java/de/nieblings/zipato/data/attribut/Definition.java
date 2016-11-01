package de.nieblings.zipato.data.attribut;

public class Definition
{
  private int id;

  public int getId() { return this.id; }

  public void setId(int id) { this.id = id; }

  private String attribute;

  public String getAttribute() { return this.attribute; }

  public void setAttribute(String attribute) { this.attribute = attribute; }

  private String attributeType;

  public String getAttributeType() { return this.attributeType; }

  public void setAttributeType(String attributeType) { this.attributeType = attributeType; }

  private String cluster;

  public String getCluster() { return this.cluster; }

  public void setCluster(String cluster) { this.cluster = cluster; }

  private boolean readable;

  public boolean getReadable() { return this.readable; }

  public void setReadable(boolean readable) { this.readable = readable; }

  private boolean reportable;

  public boolean getReportable() { return this.reportable; }

  public void setReportable(boolean reportable) { this.reportable = reportable; }

  private boolean writable;

  public boolean getWritable() { return this.writable; }

  public void setWritable(boolean writable) { this.writable = writable; }
}