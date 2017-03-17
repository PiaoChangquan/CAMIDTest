package org.CAMID.test.cep.inputadapter;
public class SensorMap {

	private String id;
	private String timestamp;
	private double value;

	public SensorMap(String id, String Timestamp, double Value) {
		this.id = id;
		this.timestamp = Timestamp;
		this.value = Value;
	}
	public SensorMap(){
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SensorData [id=" + id + ", timestamp=" + timestamp + ", value=" + value + "]";
	}

	

}
