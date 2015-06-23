package com.horusdev.enjinrequester.object;

import com.horusdev.enjinrequester.enums.VisibilityType;

/**
 * Represents an Enjin tag
 *
 * @author Ben (HorusDev)
 */
@SuppressWarnings("unused")
public class Tag {

    private int id, userCount = -1;
    private String name;
    private User[] users = null;
    private VisibilityType visibility;

    public Tag(int id, String name, User[] users, VisibilityType visibility) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.visibility = visibility;
    }

    public Tag(int id, String name, int userCount, VisibilityType visibility) {
        this.id = id;
        this.userCount = userCount;
        this.name = name;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserCount() {
        return (users != null ? users.length : userCount);
    }

    public User[] getUsers() {
        return (users == null ? null : users);
    }

    public VisibilityType getVisibility() {
        return visibility;
    }
}
