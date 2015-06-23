package com.horusdev.enjinrequester.object;

import com.horusdev.enjinrequester.object.character.GameCharacter;

import java.util.Date;

/**
 * Represents an Enjin user
 *
 * @author Ben (HorusDev)
 */
@SuppressWarnings("unused")
public class User {

    private int id;
    private String username;
    private int forumPosts = -1, forumVotes = -1, forumUpVotes = -1, forumDownVotes = -1;
    private Date lastSeen, dateJoined;
    private GameCharacter[] characters;

    public User(int id, String username, int forumPosts, int forumVotes, int forumUpVotes, int forumDownVotes, Date lastSeen, Date dateJoined, GameCharacter[] characters) {
        this.id = id;
        this.username = username;
        this.forumPosts = forumPosts;
        this.forumVotes = forumVotes;
        this.forumUpVotes = forumUpVotes;
        this.forumDownVotes = forumDownVotes;
        this.lastSeen = lastSeen;
        this.dateJoined = dateJoined;
        this.characters = characters;
    }

    //Forum-less
    public User(int id, String username, Date lastSeen, Date dateJoined, GameCharacter[] characters) {
        this.id = id;
        this.username = username;
        this.lastSeen = lastSeen;
        this.dateJoined = dateJoined;
        this.characters = characters;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getForumPosts() {
        return forumPosts;
    }

    public int getForumVotes() {
        return forumVotes;
    }

    public int getForumUpVotes() {
        return forumUpVotes;
    }

    public int getForumDownVotes() {
        return forumDownVotes;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public GameCharacter[] getCharacters() {
        return characters;
    }
}
