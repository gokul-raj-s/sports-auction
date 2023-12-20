package com.softwareag.spl.auction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.softwareag.spl.auction.constants.ElasticsearchConstants;

@Document(indexName = ElasticsearchConstants.INDEX_PLAYER_ASSIGNMENTS, type = ElasticsearchConstants.TYPE_TYPE)
public class PlayerAssignment {

    @Id
    private String id;
    private String playerAlias;
    private String fullName;
    private String profilePicture;
    @JsonIgnore
    private Gender gender;
    private String teamName;
    private String role;
    private int acquiredPoints;

    public PlayerAssignment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAcquiredPoints() {
        return acquiredPoints;
    }

    public void setAcquiredPoints(int acquiredPoints) {
        this.acquiredPoints = acquiredPoints;
    }
}
