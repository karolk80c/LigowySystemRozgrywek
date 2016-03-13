package pl.karolkolarczyk.lgs.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "spotkanie")
public class Match {

	@Id
	@GeneratedValue
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date matchDate;

	@OneToMany(mappedBy = "match", cascade = CascadeType.REMOVE)
	private List<Set> sets;

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	@ManyToMany(mappedBy = "matches")
	private List<User> users;

	@ManyToOne(fetch = FetchType.EAGER)
	Round round;

	String firstName;
	String secondName;

	String matchPlace;
	boolean firstApproved;
	boolean secondApproved;
	int secondPoints;
	int firstPoints;
	boolean completed;

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getMatchPlace() {
		return matchPlace;
	}

	public void setMatchPlace(String matchPlace) {
		this.matchPlace = matchPlace;
	}

	public boolean isFirstApproved() {
		return firstApproved;
	}

	public void setFirstApproved(boolean firstApproved) {
		this.firstApproved = firstApproved;
	}

	public int getSecondPoints() {
		return secondPoints;
	}

	public void setSecondPoints(int secondPoints) {
		this.secondPoints = secondPoints;
	}

	public int getFirstPoints() {
		return firstPoints;
	}

	public void setFirstPoints(int firstPoints) {
		this.firstPoints = firstPoints;
	}

	public boolean isSecondApproved() {
		return secondApproved;
	}

	public void setSecondApproved(boolean secondApproved) {
		this.secondApproved = secondApproved;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
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

}
