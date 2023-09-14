package com.example.gamerating.enums;

import lombok.Getter;

@Getter
public enum GenreType {

    ACTION("Action"),
    ACTION_ADVENTURE("Action/Adventure"),
    ADVENTURE("Adventure"),
    BOARD_CARD_GAME("Board/Card Game"),
    PUZZLE("Puzzle"),
    ROLE_PLAYING("Role Playing"),
    SANDBOX("Sandbox"),
    SIMULATION("Simulation"),
    STRATEGY("Strategy"),
    SPORTS("Sports"),
    MMO("Massively Multiplayer Online");

    private final String label;

    GenreType(String label) {
        this.label = label;
    }

}
