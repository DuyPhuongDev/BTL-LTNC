package com.ltnc.be.util;

import java.util.Arrays;
import java.util.List;

public class HashtagUtil {
    
    public static String getHashTagString (List<String> hashtags) {
        hashtags = hashtags.stream().map(hashtag -> hashtag.trim()).filter(hashtag -> !hashtag.isBlank()).toList();
        return String.join(",", hashtags);
    }

    public static List<String> getHashTagList(String hashtags) {
        return Arrays.asList(hashtags.split(","));
    }
}
