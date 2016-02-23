package pl.karolkolarczyk.lgs.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Spotkanie")
public class Match {

	@Id
	@GeneratedValue
	private int id;

	@Temporal(TemporalType.DATE)
	private Date matchDate;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private List<Cokolwiek> cokolwieks;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((firstPoints == null) ? 0 : firstPoints.hashCode());
		result = prime * result + id;
		result = prime * result + ((matchDate == null) ? 0 : matchDate.hashCode());
		result = prime * result + ((round == null) ? 0 : round.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((secondPoints == null) ? 0 : secondPoints.hashCode());
		result = prime * result + ((cokolwieks == null) ? 0 : cokolwieks.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Match other = (Match) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (firstPoints == null) {
			if (other.firstPoints != null) {
				return false;
			}
		} else if (!firstPoints.equals(other.firstPoints)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (matchDate == null) {
			if (other.matchDate != null) {
				return false;
			}
		} else if (!matchDate.equals(other.matchDate)) {
			return false;
		}
		if (round == null) {
			if (other.round != null) {
				return false;
			}
		} else if (!round.equals(other.round)) {
			return false;
		}
		if (secondName == null) {
			if (other.secondName != null) {
				return false;
			}
		} else if (!secondName.equals(other.secondName)) {
			return false;
		}
		if (secondPoints == null) {
			if (other.secondPoints != null) {
				return false;
			}
		} else if (!secondPoints.equals(other.secondPoints)) {
			return false;
		}
		if (cokolwieks == null) {
			if (other.cokolwieks != null) {
				return false;
			}
		} else if (!cokolwieks.equals(other.cokolwieks)) {
			return false;
		}
		if (users == null) {
			if (other.users != null) {
				return false;
			}
		} else if (!users.equals(other.users)) {
			return false;
		}
		return true;
	}

	@ManyToMany(mappedBy = "matches")
	private List<User> users;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "round_id")
	Round round;

	String firstName;
	String secondName;

	Integer secondPoints;
	Integer firstPoints;

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Integer getFirstPoints() {
		return firstPoints;
	}

	public void setFirstPoints(Integer firstPoints) {
		this.firstPoints = firstPoints;
	}

	public Integer getSecondPoints() {
		return secondPoints;
	}

	public void setSecondPoints(Integer secondPoints) {
		this.secondPoints = secondPoints;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Cokolwiek> getCokolwieks() {
		return cokolwieks;
	}

	public void setCokolwieks(List<Cokolwiek> cokolwieks) {
		this.cokolwieks = cokolwieks;
	}

}
