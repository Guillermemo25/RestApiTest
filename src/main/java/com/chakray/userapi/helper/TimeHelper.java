package com.chakray.userapi.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeHelper {
    /**
     * Returns the current time in Madagascar
     * @return String with the current time in Madagascar
     */
    public static String getTimeFromMadagascar() {
        return LocalDateTime.now(ZoneId.of("Indian/Antananarivo"))
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
