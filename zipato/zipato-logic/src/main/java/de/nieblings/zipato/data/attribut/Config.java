package de.nieblings.zipato.data.attribut;

public class Config
{
  private String name;

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private boolean master;

  public boolean getMaster() { return this.master; }

  public void setMaster(boolean master) { this.master = master; }

  private boolean hidden;

  public boolean getHidden() { return this.hidden; }

  public void setHidden(boolean hidden) { this.hidden = hidden; }

  private boolean reported;

  public boolean getReported() { return this.reported; }

  public void setReported(boolean reported) { this.reported = reported; }

  private String expire;

  public String getExpire() { return this.expire; }

  public void setExpire(String expire) { this.expire = expire; }

  private String compression;

  public String getCompression() { return this.compression; }

  public void setCompression(String compression) { this.compression = compression; }

  private String type;

  public String getType() { return this.type; }

  public void setType(String type) { this.type = type; }

  private String unit;

  public String getUnit() { return this.unit; }

  public void setUnit(String unit) { this.unit = unit; }

  private EnumValues enumValues;

  public EnumValues getEnumValues() { return this.enumValues; }

  public void setEnumValues(EnumValues enumValues) { this.enumValues = enumValues; }

  private Double scale;

  public Double getScale() { return this.scale; }

  public void setScale(Double scale) { this.scale = scale; }

  private int precision;

  public int getPrecision() { return this.precision; }

  public void setPrecision(int precision) { this.precision = precision; }

  private Integer room;

  public Integer getRoom() { return this.room; }

  public void setRoom(Integer room) { this.room = room; }
}
