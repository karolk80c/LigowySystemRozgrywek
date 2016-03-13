package pl.karolkolarczyk.lgs.enums;

public enum Place {
	A("Segment A"), F("Segment F"), G("Segment G"), OTHER("Poza budynkiem");
	String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Place(String name) {
		this.name = name;
	}
}
