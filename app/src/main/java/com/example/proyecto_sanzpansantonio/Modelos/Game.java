package com.example.proyecto_sanzpansantonio.Modelos;

import java.io.Serializable;

public class Game implements Serializable {
    //ID INT, NAME VARCHAR(40), GAME_DATE VARCHAR(40), GAME_HOUR VARCHAR(40), GAME_DURATION INT, ADDRESS VARCHAR(40), MAX_PLAYERS INT
    Integer Id;
    String Name;
    String GameDate;
    String GameHour;
    String GameDuration;
    String Address;
    Integer MaxPlayers;

    public Game(Integer id, String name, String gameDate, String gameHour, String gameDuration, String address, Integer maxPlayers) {
        this.Id = id;
        Name = name;
        GameDate = gameDate;
        GameHour = gameHour;
        GameDuration = gameDuration;
        Address = address;
        MaxPlayers = maxPlayers;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGameDate() {
        return GameDate;
    }

    public void setGameDate(String gameDate) {
        GameDate = gameDate;
    }

    public String getGameHour() {
        return GameHour;
    }

    public void setGameHour(String gameHour) {
        GameHour = gameHour;
    }

    public String getGameDuration() {
        return GameDuration;
    }

    public void setGameDuration(String gameDuration) {
        GameDuration = gameDuration;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getMaxPlayers() {
        return MaxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        MaxPlayers = maxPlayers;
    }
}
