package com.example.gamerating.util;

public class QueryUtils {

    private QueryUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static final String GAME_AVG_RATING_FORMULA_QUERY = "(select avg(r.value) from rating_tb r " +
            "left join game_tb g on g.game_id = r.game_id " +
            "Where g.game_id = game_id)";

}
