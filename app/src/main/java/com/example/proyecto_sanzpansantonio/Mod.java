package com.example.proyecto_sanzpansantonio;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Mod {
    class createGameMod implements Serializable {
        private String Name;
        private Date GameDate;
        private Time GameHour;
        private Integer GameTime;
        private String Address;
        private Integer MaxPlayers;

        public createGameMod(String name, Date gameDate, Time gameHour, Integer gameTime, String address, Integer maxPlayers) {
            Name = name;
            GameDate = gameDate;
            GameHour = gameHour;
            GameTime = gameTime;
            Address = address;
            MaxPlayers = maxPlayers;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Date getGameDate() {
            return GameDate;
        }

        public void setGameDate(Date gameDate) {
            GameDate = gameDate;
        }

        public Time getGameHour() {
            return GameHour;
        }

        public void setGameHour(Time gameHour) {
            GameHour = gameHour;
        }

        public Integer getGameTime() {
            return GameTime;
        }

        public void setGameTime(Integer gameTime) {
            GameTime = gameTime;
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
}
