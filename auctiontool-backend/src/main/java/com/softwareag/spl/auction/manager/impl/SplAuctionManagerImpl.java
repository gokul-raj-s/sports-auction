package com.softwareag.spl.auction.manager.impl;

import com.softwareag.spl.auction.constants.ElasticsearchConstants;
import com.softwareag.spl.auction.manager.api.SplAuctionManager;
import com.softwareag.spl.auction.model.*;
import com.softwareag.spl.auction.repository.CommonRepository;
import com.softwareag.spl.auction.repository.PlayerAssignmentRepository;
import com.softwareag.spl.auction.repository.PlayerRepository;
import com.softwareag.spl.auction.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SplAuctionManagerImpl implements SplAuctionManager {

    Random random = new Random();

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerAssignmentRepository playerAssignmentRepository;

    @Override
    public Common getCommonAuctionInformation() {
        Optional<Common> optional = commonRepository.findById(ElasticsearchConstants.COMMON_INFO_ID);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        Iterable<Team> allTeams = teamRepository.findAll();
        List<Team> teams = new ArrayList<>();
        for (Team team : allTeams) {
            teams.add(getTeamDetails(team));
        }
        return teams;
    }

    private Team getTeamDetails(Team team){
        Common commonAuctionInformation = getCommonAuctionInformation();
        List<PlayerAssignment> playerAssignmentByTeam = getPlayerAssignmentByTeam(team.getName());
        // Set number of players
        team.setNumberOfPlayers(playerAssignmentByTeam.size());
        // Set number of women players
        long count = playerAssignmentByTeam.stream().filter(playerAssignment -> playerAssignment.getGender() == Gender.FEMALE).count();
        team.setNumberOfWomenPlayers((int) count);
        // Compute and set Balance points
        int balancePoints = commonAuctionInformation.getBasePointsPerTeam() - playerAssignmentByTeam.stream().mapToInt(PlayerAssignment::getAcquiredPoints).sum();
        team.setBalancePoints(balancePoints);
        // Compute and set maximum bid for next player
        int noOfPlayersToBid = commonAuctionInformation.getExpectedTeamSize() - team.getNumberOfPlayers();
        int maximumBid = balancePoints - ((commonAuctionInformation.getBasePointsPerPlayer() - commonAuctionInformation.getBonusPoints()) * (noOfPlayersToBid - 1));
        team.setMaximumBidForNextPlayer(maximumBid);
        return team;
    }

    @Override
    public List<Player> getAllPlayers() {
        Iterable<Player> iterable = playerRepository.findAll();
        List<Player> players = new ArrayList<>();
        iterable.forEach(players::add);
        return players;
    }

    @Override
    public List<PlayerAssignment> getAllPlayerAssignments() {
        Iterable<PlayerAssignment> iterable = playerAssignmentRepository.findAll();
        return getPlayerAssignments(iterable);
    }

    private List<PlayerAssignment> getPlayerAssignments(Iterable<PlayerAssignment> iterable) {
        List<PlayerAssignment> playerAssignments = new ArrayList<>();
        iterable.forEach(playerAssignment -> {
            playerRepository.findById(playerAssignment.getPlayerAlias()).ifPresent(player -> {
                playerAssignment.setFullName(player.getFullName());
                playerAssignment.setProfilePicture(player.getProfilePicture());
                playerAssignment.setGender(player.getGender());
            });
            playerAssignments.add(playerAssignment);
        });
        return playerAssignments;
    }

    @Override
    public List<PlayerAssignment> getPlayerAssignmentByTeam(String teamName) {
        int pageNumber = 0;
        int resultsPerPage = 1000;
        boolean continuePaging = true;
        List<PlayerAssignment> list = new ArrayList<>();
        while (continuePaging) {
            Page<PlayerAssignment> page = playerAssignmentRepository.findByTeamName(teamName, PageRequest.of(pageNumber, resultsPerPage));
            list.addAll(page.getContent());
            if (pageNumber < page.getTotalPages()) {
                pageNumber++;
            } else {
                continuePaging = false;
            }
        }
        return getPlayerAssignments(list);
    }

    @Override
    public Player getRandomPlayer() {
        // Get all player aliases
        // Find out aliases of all players who are already assigned to a team
        // Find the difference between the two
        // Now, determine a random index within this size and return the player corresponding to this index
        List<String> allPlayerAliases = getAllPlayers().stream().map(Player::getAlias).collect(Collectors.toList());
        List<String> assignedPlayerAliases = getAllPlayerAssignments().stream().map(PlayerAssignment::getPlayerAlias).collect(Collectors.toList());
        allPlayerAliases.removeAll(assignedPlayerAliases);
        if(!allPlayerAliases.isEmpty()){
            int randomIndex = random.nextInt(allPlayerAliases.size());
            Optional<Player> optional = playerRepository.findById(allPlayerAliases.get(randomIndex));
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return null;
    }

    @Override
    public void createPlayerAssignment(PlayerAssignment playerAssignment) {
        Common commonAuctionInformation = getCommonAuctionInformation();
        if (playerAssignment.getAcquiredPoints() == commonAuctionInformation.getBasePointsPerPlayer()) {
            playerAssignment.setAcquiredPoints(playerAssignment.getAcquiredPoints() - commonAuctionInformation.getBonusPoints());
        }
        Optional<Team> teamOptional = teamRepository.findById(playerAssignment.getTeamName());
        int maxBid = 0;
        if (teamOptional.isPresent()) {
            Team team = getTeamDetails(teamOptional.get());
            maxBid = team.getMaximumBidForNextPlayer();
        }
        if (playerAssignment.getAcquiredPoints() <= maxBid) {
            playerAssignmentRepository.save(playerAssignment);
        }
    }

    @Override
    public void createPlayers(List<CsvPlayer> csvPlayers) {
        List<Player> players = new ArrayList<>();
        csvPlayers.forEach(csvPlayer -> {
            Player player = new Player(csvPlayer);
            players.add(player);
        });
        playerRepository.saveAll(players);
    }

}
