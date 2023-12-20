package com.softwareag.spl.auction.manager.api;

import com.softwareag.spl.auction.model.*;

import java.util.List;

public interface SplAuctionManager {

    Common getCommonAuctionInformation();
    List<Team> getAllTeams();
    List<Player> getAllPlayers();
    List<PlayerAssignment> getAllPlayerAssignments();
    List<PlayerAssignment> getPlayerAssignmentByTeam(String teamName);
    Player getRandomPlayer();
    void createPlayerAssignment(PlayerAssignment playerAssignment);
    void createPlayers(List<CsvPlayer> csvPlayers);

}
