package de.nieblings.zipato.data.devices;

import java.util.Date;

public class State
{
  private Date timestamp;

  public Date getTimestamp() { return this.timestamp; }

  public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

  private boolean trouble;

  public boolean getTrouble() { return this.trouble; }

  public void setTrouble(boolean trouble) { this.trouble = trouble; }

  private String modem;
  
  private Date receiveTimestamp;

  public Date getReceiveTimestamp() { return this.receiveTimestamp; }

  public void setReceiveTimestamp(Date receiveTimestamp) { this.receiveTimestamp = receiveTimestamp; }

  private int batteryTimestamp;

  public int getBatteryTimestamp() { return this.batteryTimestamp; }

  public void setBatteryTimestamp(int batteryTimestamp) { this.batteryTimestamp = batteryTimestamp; }

  private int batteryLevel;

  public int getBatteryLevel() { return this.batteryLevel; }

  public void setBatteryLevel(int batteryLevel) { this.batteryLevel = batteryLevel; }

  private int sentTimestamp;

  public int getSentTimestamp() { return this.sentTimestamp; }

  public void setSentTimestamp(int sentTimestamp) { this.sentTimestamp = sentTimestamp; }

  private String onlineState;

  public String getOnlineState() { return this.onlineState; }

  public void setOnlineState(String onlineState) { this.onlineState = onlineState; }

  private boolean started;

  
  private boolean mainsPower;

  public boolean getMainsPower() { return this.mainsPower; }

  public void setMainsPower(boolean mainsPower) { this.mainsPower = mainsPower; }

  private boolean online;

  public boolean getOnline() { return this.online; }

  public void setOnline(boolean online) { this.online = online; }

public String getModem() {
	return modem;
}

public void setModem(String modem) {
	this.modem = modem;
}

public boolean isStarted() {
	return started;
}

public void setStarted(boolean started) {
	this.started = started;
}
}
