package com.softwareag.spl.auction.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import com.softwareag.spl.auction.manager.api.SplAuctionManager;
import com.softwareag.spl.auction.model.CsvPlayer;
import com.softwareag.spl.auction.model.Player;
import com.softwareag.spl.auction.model.PlayerAssignment;
import com.softwareag.spl.auction.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
public class SplAuctionController {

    @Autowired
    private SplAuctionManager auctionManager;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return auctionManager.getAllTeams();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/player_assignments")
    public List<PlayerAssignment> getAllPlayerAssignments() {
        return auctionManager.getAllPlayerAssignments();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/player_assignments/{team_name}")
    public List<PlayerAssignment> getPlayerAssignmentByTeam(@PathVariable("team_name") String teamName) {
        return auctionManager.getPlayerAssignmentByTeam(teamName);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/random_player")
    public Player getRandomPlayerForAuction() {
        return auctionManager.getRandomPlayer();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/player_assignments")
    public ResponseEntity<String> submitPlayerAssignment(@RequestBody PlayerAssignment playerAssignment) {
        auctionManager.createPlayerAssignment(playerAssignment);
        return ResponseEntity.accepted().header("Content-Type", "text/plain").body("Player assignment to team successful.");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/players")
    public ResponseEntity<String> createPlayerInformation(@RequestParam("csvFile") MultipartFile csvFile) {
        try {
            List<CsvPlayer> csvPlayers = new CsvToBeanBuilder<CsvPlayer>(new InputStreamReader(csvFile.getInputStream())).withType(CsvPlayer.class).build().parse();
            auctionManager.createPlayers(csvPlayers);
            return ResponseEntity.accepted().header("Content-Type", "text/plain").body("Players created. Total: " + csvPlayers.size());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while parsing the CSV file: " + e.getMessage(), e);
        }
    }

}
