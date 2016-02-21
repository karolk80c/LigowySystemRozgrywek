package pl.karolkolarczyk.lgs.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Sezon")
public class Season {

	@Id
	@GeneratedValue
	int id;

	@OneToMany(mappedBy = "season", fetch = FetchType.EAGER)
	private List<Round> rounds;

	int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}
