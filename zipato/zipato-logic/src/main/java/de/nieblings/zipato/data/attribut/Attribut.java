package de.nieblings.zipato.data.attribut;

public class Attribut {
	private String uuid;

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Definition definition;

	public Definition getDefinition() {
		return this.definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	private Config config;

	public Config getConfig() {
		return this.config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	private ClusterEndpoint clusterEndpoint;

	public ClusterEndpoint getClusterEndpoint() {
		return this.clusterEndpoint;
	}

	public void setClusterEndpoint(ClusterEndpoint clusterEndpoint) {
		this.clusterEndpoint = clusterEndpoint;
	}

	private Endpoint endpoint;

	public Endpoint getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}

	private Device device;

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	private Network network;

	public Network getNetwork() {
		return this.network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	private int attributeId;

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	private Value value;

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	private Room room;

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	private Boolean showIcon;

	public Boolean getShowIcon() {
		return this.showIcon;
	}

	public void setShowIcon(Boolean showIcon) {
		this.showIcon = showIcon;
	}

	private UiType uiType;

	public UiType getUiType() {
		return this.uiType;
	}

	public void setUiType(UiType uiType) {
		this.uiType = uiType;
	}
}
