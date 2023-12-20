package com.softwareag.spl.auction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.softwareag.spl.auction.constants.ElasticsearchConstants;

@Document(indexName = ElasticsearchConstants.INDEX_TEAMS, type = ElasticsearchConstants.TYPE_TYPE)
public class Team {

    @Id
    private String name;
    private String teamLogo;
    private int balancePoints;
    private int numberOfPlayers;
    private int numberOfWomenPlayers;
    private int maximumBidForNextPlayer;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public int getBalancePoints() {
        return balancePoints;
    }

    public void setBalancePoints(int balancePoints) {
        this.balancePoints = balancePoints;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfWomenPlayers() {
        return numberOfWomenPlayers;
    }

    public void setNumberOfWomenPlayers(int numberOfWomenPlayers) {
        this.numberOfWomenPlayers = numberOfWomenPlayers;
    }

    public int getMaximumBidForNextPlayer() {
        return maximumBidForNextPlayer;
    }

    public void setMaximumBidForNextPlayer(int maximumBidForNextPlayer) {
        this.maximumBidForNextPlayer = maximumBidForNextPlayer;
    }
}
