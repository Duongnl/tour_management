package com.tour.tour_management.utils;

import com.github.slugify.Slugify;

import java.util.Map;

public class StringUtils {

    private static final Slugify slugify = Slugify.builder() // provided as a map
            .customReplacements(Map.of("đ", "d", "Đ", "D"))
            // provided as single key-value
            .customReplacement("đ", "d")
            .customReplacement("Đ", "D")
            .build();

    public static String createSlug (String name) {
        return slugify.slugify(name);
    }

    public static Integer getIdFromUrl (String url) {
        try {
            String [] parts = url.split("-");
            Integer id  = Integer.parseInt(parts[parts.length-1]);
            return id;
        } catch (NumberFormatException e)  {
            return -1;
        }
    }

    public static String getUUIDFromUrl (String url) {
        try {
            String [] parts = url.split("-");
            String uuid  =parts[parts.length-5]+ '-' + parts[parts.length-4] + '-' +
                    parts[parts.length-3] + '-' + parts[parts.length-2] + '-' + parts[parts.length-1];

            return uuid;
        } catch (NumberFormatException e)  {
            return "error";
        }
    }

}
