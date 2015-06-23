package com.horusdev.enjinrequester.enums;

/**
 * All (used) names of JSON objects for consistency
 *
 * @author Ben S (HorusDev)
 */
public enum Param {
    USER_ID("user_id"),
    CHARACTER("character"),
    CHARACTERS("characters"),
    MC_PLAYERS("mcplayers"),

    USERNAME("username"),
    FORUM_POST_COUNT("forum_post_count"),
    FORUM_VOTES("forum_votes"),
    FORUM_UP_VOTES("forum_up_votes"),
    FORUM_DOWN_VOTES("forum_down_votes"),
    LAST_SEEN("lastseen"),
    DATE_JOINED("datejoined"),

    NAME("name"),
    TAGNAME("tagname"),

    VISIBILITY("visible"),
    USERS("users"),
    NUMBER_USERS("numusers"),

    TYPE("type"),

    POINTS("points"),

    PLAYER("player");

    private String key;

    Param(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
