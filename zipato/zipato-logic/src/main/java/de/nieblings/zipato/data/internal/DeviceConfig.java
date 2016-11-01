package de.nieblings.zipato.data.internal;

import java.util.ArrayList;
import java.util.List;

import de.nieblings.zipato.data.VirtualEndpoints;
import de.nieblings.zipato.data.attribut.Attribut;

public class DeviceConfig {

	private String id;
	
	private String viewName;
	
	private String type;

	private String status;
	
	private String onlineStatus;
	
	private String raum;
	
	private VirtualEndpoints device;
	
	private List<Attribut> attribute=new ArrayList<Attribut>();

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Attribut> getAttribute() {
		return attribute;
	}

	public VirtualEndpoints getDevice() {
		return device;
	}

	public void setDevice(VirtualEndpoints device) {
		this.device = device;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}

}
