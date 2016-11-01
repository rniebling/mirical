package de.nieblings.zipato.data.devices;

public class DeviceState {

  private String uuid;

  public String getUuid() { return this.uuid; }

  public void setUuid(String uuid) { this.uuid = uuid; }

  private State state;

  public State getState() { return this.state; }

  public void setState(State state) { this.state = state; }
}