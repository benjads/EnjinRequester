package com.horusdev.enjinrequester.enums;

/**
 * The simplification of the different visibilities of tags
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public enum VisibilityType {
    HIDDEN(0), BOTH(1), IMAGE_ONLY(2);

    private int code;

    VisibilityType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static VisibilityType fromCode(int code) {
        for (VisibilityType value : values())
            if (value.getCode() == code)
                return value;

        return null;
    }
}
