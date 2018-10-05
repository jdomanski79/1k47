package pl.jdomanski.k47.meter;

public enum MeterType {
	ELECTRICITY("Prad"),
	WATER("Woda");
	
	private String description;
	
	private MeterType(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
