package com.softwareag.spl.auction.model;

import com.softwareag.spl.auction.constants.ElasticsearchConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Document(indexName = ElasticsearchConstants.INDEX_PLAYERS, type = ElasticsearchConstants.TYPE_TYPE)
public class Player {

    @Id
    private String  alias;
    private String fullName;
    private List<String> nickNames;
    private String splDebut;
    private String profilePicture;
    private Gender gender;
    private List<String> strengths;
    private List<String> achievements;

    public Player() {
    }

    public Player(CsvPlayer csvPlayer) {
        setAlias(csvPlayer.getAlias());
        setFullName(csvPlayer.getFullName());
        setNickNamesFromString(csvPlayer.getNickName());
        setSplDebut(csvPlayer.getSplDebutYear());
        setProfilePicture(csvPlayer.getProfilePicture());
        setGender(csvPlayer.getGender());
        setStrengthsFromString(csvPlayer.getStrengths());
        setAchievementsFromString(csvPlayer.getAchievements());
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNamesFromString(String hashSeparatedNickNames) {
        setNickNames(separateOnHash(hashSeparatedNickNames));
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    public String getSplDebut() {
        return splDebut;
    }

    public void setSplDebut(String splDebut) {
        this.splDebut = splDebut;
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

    public void setGender(String gender) {
        switch (gender) {
            case "MALE":
                setGender(Gender.MALE);
                break;
            case "FEMALE":
                setGender(Gender.FEMALE);
                break;
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengthsFromString(String hashSeparatedStrengths) {
        setStrengths(separateOnHash(hashSeparatedStrengths));
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievementsFromString(String hashSeparatedAchievements) {
        setAchievements(separateOnHash(hashSeparatedAchievements));
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    private List<String> separateOnHash(String string) {
        if (string != null && !string.isEmpty()) {
            return Arrays.stream(string.split("#")).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
