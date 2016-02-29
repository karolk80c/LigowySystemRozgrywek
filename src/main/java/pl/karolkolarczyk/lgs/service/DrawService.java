package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoundRepository;
import pl.karolkolarczyk.lgs.repository.SeasonRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Transactional
@Service
public class DrawService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private RoundRepository roundRepository;

	private static final Logger logger = Logger.getLogger(DrawService.class);

	public void draw() {
		Season season = new Season();
		List<User> users = userRepository.findAll();
		List<String> userListLogin = generateLoginList(users);
		int iloscSpotkan = numMatches(userListLogin.size());
		if (userListLogin.size() % 2 != 0) {
			userListLogin.add("null");
		}

		int userListSize = userListLogin.size();
		int numRounds = (userListSize - 1);
		int halfSize = userListSize / 2;

		logger.info("\nIlosc uczestników: " + userListSize);
		for (String string : userListLogin) {
			logger.info(string);
		}
		logger.info("\nIlosc kolejek: " + numRounds(userListSize));
		logger.info("Ilosc spotkan: " + iloscSpotkan + "\n");

		List<String> usersInMatches = new ArrayList<>();
		if (userListLogin.size() > 0) {
			usersInMatches.addAll(userListLogin);

			usersInMatches.remove(0);

			int usersInMatchesSize = usersInMatches.size();
			List<Round> rounds = new ArrayList<>();

			for (int roundNr = 0; roundNr < numRounds; roundNr++) {
				logger.info("Kolejka " + (roundNr + 1));
				Round round = new Round();
				List<Match> matches = new ArrayList<>();
				round.setNumber((roundNr + 1));

				int usersID = roundNr % usersInMatchesSize;
				if (!("null".equals(usersInMatches.get(usersID))) && !("null".equals(userListLogin.get(0)))) {
					logger.info(usersInMatches.get(usersID) + " vs " + userListLogin.get(0));
					User user1 = userRepository.findOne(usersInMatches.get(usersID));
					User user2 = userRepository.findOne(userListLogin.get(0));
					List<User> usersList = new ArrayList<>();
					usersList.add(user1);
					usersList.add(user2);
					Match match = new Match();
					match.setUsers(usersList);
					match.setFirstName(user1.getFirstName() + " " + user1.getLastName());
					match.setSecondName(user2.getFirstName() + " " + user2.getLastName());
					match.setRound(round);
					matchRepository.save(match);
					matches.add(match);
					List<Match> user1Matches = user1.getMatches();
					List<Match> user2Matches = user2.getMatches();
					user1Matches.add(match);
					user2Matches.add(match);
					user1.setMatches(user1Matches);
					user2.setMatches(user2Matches);
					userRepository.save(user1);
					userRepository.save(user2);

				}

				for (int usersId = 1; usersId < halfSize; usersId++) {
					int firstTeam = (roundNr + usersId) % usersInMatchesSize;
					int secondTeam = (roundNr + usersInMatchesSize - usersId) % usersInMatchesSize;
					if (!("null".equals(usersInMatches.get(firstTeam)))
							&& !("null".equals(usersInMatches.get(secondTeam)))) {
						logger.info(usersInMatches.get(firstTeam) + " vs " + usersInMatches.get(secondTeam));
						User user1 = userRepository.findOne(usersInMatches.get(firstTeam));
						User user2 = userRepository.findOne(usersInMatches.get(secondTeam));
						List<User> usersList = new ArrayList<>();
						usersList.add(user1);
						usersList.add(user2);
						Match match = new Match();
						match.setFirstName(user1.getFirstName() + " " + user1.getLastName());
						match.setSecondName(user2.getFirstName() + " " + user2.getLastName());
						match.setUsers(usersList);
						match.setRound(round);
						matchRepository.save(match);
						matches.add(match);
						List<Match> user1Matches = user1.getMatches();
						List<Match> user2Matches = user2.getMatches();
						user1Matches.add(match);
						user2Matches.add(match);
						user1.setMatches(user1Matches);
						user2.setMatches(user2Matches);
						userRepository.save(user1);
						userRepository.save(user2);

					}
				}
				round.setMatches(matches);
				round.setSeason(season);
				roundRepository.save(round);
				rounds.add(round);

			}
			String seasonNumber = Calendar.getInstance().get(Calendar.YEAR) + "/"
					+ Calendar.getInstance().get(Calendar.MONTH);
			season.setNumber(seasonNumber);
			seasonRepository.save(season);
		}

	}

	public List<String> generateLoginList(List<User> users) {
		List<String> userListLogin = new ArrayList<>();
		for (User user : users) {
			for (Role role : user.getRoles()) {
				if ("ROLE_USER".equals(role.getName())) {
					userListLogin.add(user.getLogin());
				}
			}
		}

		return userListLogin;
	}

	public int numRounds(int userListSize) {
		int numRounds;
		if (userListSize % 2 == 0) {
			numRounds = userListSize - 1;
		} else {
			numRounds = userListSize;
		}
		return numRounds;
	}

	public int numMatches(int userListSize) {
		return (userListSize * (userListSize - 1)) / 2;
	}

}
