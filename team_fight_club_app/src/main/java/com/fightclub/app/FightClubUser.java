package com.fightclub.app;

public class FightClubUser {

    private Long clubUserId;
    private String clubUserName;

    public FightClubUser(Long clubUserId, String clubUserName) {
        this.clubUserId = clubUserId;
        this.clubUserName = clubUserName;
    }

    public Long getClubUserId() {
        return clubUserId;
    }

    public void setClubUserId(Long clubUserId) {
        this.clubUserId = clubUserId;
    }

    public String getClubUserName() {
        return clubUserName;
    }

    public void setClubUserName(String clubUserName) {
        this.clubUserName = clubUserName;
    }
}
