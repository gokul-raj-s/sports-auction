package com.softwareag.spl.auction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.softwareag.spl.auction.constants.ElasticsearchConstants;

@Document(indexName = ElasticsearchConstants.INDEX_COMMON, type = ElasticsearchConstants.TYPE_TYPE)
public class Common {

    @Id
    private String id;
    private int expectedTeamSize;
    private int expectedNoOfWomenPlayers;
    private int negativePoints;
    private int bonusPoints;
    private int basePointsPerTeam;
    private int basePointsPerPlayer;

    public Common() {
    }

    public int getExpectedTeamSize() {
        return expectedTeamSize;
    }

    public void setExpectedTeamSize(int expectedTeamSize) {
        this.expectedTeamSize = expectedTeamSize;
    }

    public int getExpectedNoOfWomenPlayers() {
        return expectedNoOfWomenPlayers;
    }

    public void setExpectedNoOfWomenPlayers(int expectedNoOfWomenPlayers) {
        this.expectedNoOfWomenPlayers = expectedNoOfWomenPlayers;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public int getBasePointsPerTeam() {
        return basePointsPerTeam;
    }

    public void setBasePointsPerTeam(int basePointsPerTeam) {
        this.basePointsPerTeam = basePointsPerTeam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBasePointsPerPlayer() {
        return basePointsPerPlayer;
    }

    public void setBasePointsPerPlayer(int basePointsPerPlayer) {
        this.basePointsPerPlayer = basePointsPerPlayer;
    }
}
