package pl.karolkolarczyk.lgs.enums;

public enum Place {
	A("Skrzyd³o A"), F("Skrzyd³o F"), G("Skrzyd³o G"), OTHER("Poza budynkiem");
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
