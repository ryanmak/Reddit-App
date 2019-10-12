package com.mak.ryan.reddit.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class TimestampUtility {
    public static String format(int epoch) {
        Long epochLong = (long) epoch;
        LocalDate postedDateTime = new DateTime(epochLong * 1000L).toLocalDate();
        LocalDate currentDateTime = new DateTime().toLocalDate();

        int years = Years.yearsBetween(postedDateTime, currentDateTime).getYears();
        if (years > 0) {
            if (years == 1) {
                return years + " year ago";
            }
            return years + " years ago";
        }

        int months = Months.monthsBetween(postedDateTime, currentDateTime).getMonths();
        if (months > 0) {
            if (months == 1) {
                return months + " month ago";
            }
            return months + " months ago";
        }

        int weeks = Weeks.weeksBetween(postedDateTime, currentDateTime).getWeeks();
        if (weeks > 0) {
            if (weeks == 1) {
                return weeks + " week ago";
            }
            return weeks + " weeks ago";
        }

        int days = Days.daysBetween(postedDateTime, currentDateTime).getDays();
        if (days > 0) {
            if (days == 1) {
                return days + " day ago";
            }
            return days + " days ago";
        }

        int hours = Hours.hoursBetween(postedDateTime, currentDateTime).getHours();
        if (hours > 0) {
            if (hours == 1) {
                return hours + " hour ago";
            }
            return hours + " hours ago";
        }

        int minutes = Minutes.minutesBetween(postedDateTime, currentDateTime).getMinutes();
        if (minutes > 0) {
            if (minutes == 1) {
                return minutes + " minute ago";
            }
            return minutes + " minutes ago";
        }

        int seconds = Seconds.secondsBetween(postedDateTime, currentDateTime).getSeconds();
        if (seconds > 0) {
            if (seconds == 1) {
                return seconds + " second ago";
            }
            return seconds + " seconds ago";
        }

        return "Just now";
    }
}
