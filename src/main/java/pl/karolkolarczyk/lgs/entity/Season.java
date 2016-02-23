package pl.karolkolarczyk.lgs.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Sezon")
public class Season {

	@Id
	@GeneratedValue
	int id;

	@OneToMany(mappedBy = "season")
	private List<Round> rounds;

	int number;

	public int getNumber() {
		return number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + ((rounds == null) ? 0 : rounds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Season other = (Season) obj;
		if (id != other.id) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (rounds == null) {
			if (other.rounds != null) {
				return false;
			}
		} else if (!rounds.equals(other.rounds)) {
			return false;
		}
		return true;
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
