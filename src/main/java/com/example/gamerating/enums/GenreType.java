package com.example.gamerating.enums;

import lombok.Getter;

@Getter
public enum GenreType {

    ACTION("Action"),
    ACTION_ADVENTURE("Action/Adventure"),
    ADVENTURE("Adventure"),
    BOARD_CARD_GAME("Board/Card Game"),
    MMO("Massively Multiplayer Online (MMO)"),
    MOBA("Multiplayer Online Battle Arena (MOBA)"),
    PUZZLE("Puzzle"),
    ROLE_PLAYING("Role Playing"),
    SANDBOX("Sandbox"),
    SIMULATION("Simulation"),
    STRATEGY("Strategy"),
    SPORTS("Sports");

    private final String label;

    GenreType(String label) {
        this.label = label;
    }

}
