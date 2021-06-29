package com.jobseeker.adminportal.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DateDiffUtility {
    public String getDiffHour(LocalDateTime localDateTime) {
        if(localDateTime == null) return "";
        String diffs;
        LocalDateTime now = localDateTime.now();
        long days = localDateTime.until(now, ChronoUnit.DAYS);
        localDateTime = localDateTime.plusDays(days);

        long hours = localDateTime.until(now, ChronoUnit.HOURS);

        if (days != 0) {
            diffs = days + " day(s) and " + hours + " hour(s) ago";
        } else {
            diffs = hours + " hour(s) ago";
        }

        return diffs;
    }
}
