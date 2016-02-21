package pl.karolkolarczyk.lgs.entity;

import java.util.Date;
import java.util.List;

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

	@OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
	private List<Set> sets;

	@ManyToMany(mappedBy = "matches", fetch = FetchType.EAGER)
	private List<User> users;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "round_id")
	Round round;

	String firstName;
	String secondName;
	String firstPoints;

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstPoints() {
		return firstPoints;
	}

	public void setFirstPoints(String firstPoints) {
		this.firstPoints = firstPoints;
	}

	public String getSecondPoints() {
		return secondPoints;
	}

	public void setSecondPoints(String secondPoints) {
		this.secondPoints = secondPoints;
	}

	String secondPoints;

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

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
