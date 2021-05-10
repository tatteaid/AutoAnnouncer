package me.tatteaid.utils;

// TODO: complete this
public enum SoundUtil {
    AMBIENCE_CAVE("AMBIENCE_CAVE", "AMBIENT_CAVE"),
    AMBIENCE_RAIN("AMBIENCE_RAIN", "WEATHER_RAIN");

    private String sound8;
    private String sound912;
    private String sound13;
    private String sound16;

    private String sound913;
    private String sound816;

    // sounds that differ from 1.8, 1.9-1.12, 1.13, and 1.16
    private SoundUtil(String sound8, String sound912, String sound13, String sound16) {
        this.sound8 = sound8;
        this.sound912 = sound912;
        this.sound13 = sound13;
        this.sound16 = sound16;
    }

    /*
    Sounds that differ from 1.8, 1.9-1.12, 1.13, and 1.16
    Sounds that differ from 1.8, 1.9-1.12, and the same from 1.13-1.16
    Sounds that differ from 1.8, but the same from 1.9-1.16
    Sounds that have remained the same from 1.8-1.16
     */
}