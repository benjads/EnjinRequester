package com.horusdev.enjinrequester.object.character;

import java.util.UUID;

/**
 * A Minecraft character
 * (not related to GameCharacter)
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public class MCCharacter {

    private String name;
    private UUID uuid = null;

    public MCCharacter(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public MCCharacter(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return (uuid == null ? null : uuid);
    }
}
