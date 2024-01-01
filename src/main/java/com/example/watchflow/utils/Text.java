package com.example.watchflow.utils;

public class Text {

    public static boolean isSimilarTitle(String title, String searchedTitle) {
        double probability = 0.5;
        int lengthLongestSubtitle = calcLengthLongestSubtitle(title.toLowerCase(), searchedTitle.toLowerCase());
        double similarity = (double) lengthLongestSubtitle / Math.max(title.length(), searchedTitle.length());
        return similarity >= probability;

    }
    private static int calcLengthLongestSubtitle(String title, String searchedTitle) {
        int[][] similarity = new int[title.length() + 1][searchedTitle.length() + 1];

        for (int i = 0; i <= title.length(); i++) {
            for (int j = 0; j <= searchedTitle.length(); j++) {
                if (i == 0 || j == 0) {
                    similarity[i][j] = 0;
                } else if (title.charAt(i - 1) == searchedTitle.charAt(j - 1)) {
                    similarity[i][j] = similarity[i - 1][j - 1] + 1;
                } else {
                    similarity[i][j] = Math.max(similarity[i - 1][j], similarity[i][j - 1]);
                }
            }
        }
        return similarity[title.length()][searchedTitle.length()];
    }
}
