package com.horusdev.enjinrequester.object.character;

/**
 * A character that is associated with an User
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public class GameCharacter {

    private int gameId;
    private String name, type;

    public GameCharacter(int gameId, String name, String type) {
        this.gameId = gameId;
        this.name = name;
        this.type = type;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
