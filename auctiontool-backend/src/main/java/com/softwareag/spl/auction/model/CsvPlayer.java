package com.softwareag.spl.auction.model;

import com.opencsv.bean.CsvBindByName;

public class CsvPlayer {

    @CsvBindByName(column = "Alias", required = true)
    private String alias;

    @CsvBindByName(column = "Full Name", required = true)
    private String fullName;

    @CsvBindByName(column = "Nick Name (if any)")
    private String nickName;

    @CsvBindByName(column = "Picture Name", required = true)
    private String profilePicture;

    @CsvBindByName(column = "Gender", required = true)
    private String gender;

    @CsvBindByName(column = "SPL Debut Year", required = true)
    private String splDebutYear;

    @CsvBindByName(column = "Strengths")
    private String strengths;

    @CsvBindByName(column = "Achievements")
    private String achievements;

    public CsvPlayer() {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSplDebutYear() {
        return splDebutYear;
    }

    public void setSplDebutYear(String splDebutYear) {
        this.splDebutYear = splDebutYear;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
}
