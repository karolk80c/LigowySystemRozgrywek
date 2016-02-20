package pl.karolkolarczyk.lgs.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Uzytkownik")
public class User {

	@Id
	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String emailAdress;

	@Column(nullable = false)
	private String contactNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "uzytkownik_mecz")
	private List<Match> matches;
	
	private boolean enabled;

	private Integer wonSmallPoints;

	private Integer wonSets;

	private Integer lostSmallPoints;

	private Integer lostSets;

	private Integer wonMatches;

	private Integer lostMatches;

	private Integer balanceMatches;

	private Integer balanceSets;

	private Integer balanceSmallPoints;

	@PrePersist
	public void triggers() {
		createDate = new Date();
	}

	public Integer getBalanceMatches() {
		return balanceMatches;
	}

	public void setBalanceMatches(Integer balanceMatches) {
		this.balanceMatches = balanceMatches;
	}

	public Integer getBalanceSets() {
		return balanceSets;
	}

	public void setBalanceSets(Integer balanceSets) {
		this.balanceSets = balanceSets;
	}

	public Integer getBalanceSmallPoints() {
		return balanceSmallPoints;
	}

	public void setBalanceSmallPoints(Integer balanceSmallPoints) {
		this.balanceSmallPoints = balanceSmallPoints;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getWonSmallPoints() {
		return wonSmallPoints;
	}

	public void setWonSmallPoints(Integer wonSmallPoints) {
		this.wonSmallPoints = wonSmallPoints;
	}

	public Integer getLostSmallPoints() {
		return lostSmallPoints;
	}

	public void setLostSmallPoints(Integer lostSmallPoints) {
		this.lostSmallPoints = lostSmallPoints;
	}

	public Integer getWonSets() {
		return wonSets;
	}

	public void setWonSets(Integer wonSets) {
		this.wonSets = wonSets;
	}

	public Integer getLostSets() {
		return lostSets;
	}

	public void setLostSets(Integer lostSets) {
		this.lostSets = lostSets;
	}

	public Integer getWonMatches() {
		return wonMatches;
	}

	public void setWonMatches(Integer wonMatches) {
		this.wonMatches = wonMatches;
	}

	public Integer getLostMatches() {
		return lostMatches;
	}

	public void setLostMatches(Integer lostMatches) {
		this.lostMatches = lostMatches;
	}

}
