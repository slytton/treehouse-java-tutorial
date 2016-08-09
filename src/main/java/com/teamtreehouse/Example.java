package com.teamtreehouse;

import java.util.*;

/**
 * Created by gschool on 8/6/16.
 */
public class Example {
    public static void main(String[] args) {
        Treet[] treets = Treets.loadAndSaveRemote();
        System.out.printf("There are %d treets. %n", treets.length);

        Set<String> allHashTags = new TreeSet<String>();
        Set<String> allMentions = new TreeSet<String>();

        for (Treet treet : treets) {
            allHashTags.addAll(treet.getHashTags());
            allMentions.addAll(treet.getMentions());
        }

        System.out.printf("Hash tags: %s %n", allHashTags);
        System.out.printf("Mentions: %s %n", allMentions);

        Map<String, Integer> hashTagCounts = new HashMap<String, Integer>();

        for (Treet treet : treets) {
            for (String hashTag : treet.getHashTags()){
                Integer count = hashTagCounts.get(hashTag);
                if(count == null) {
                    count = 0;
                }
                count++;
                hashTagCounts.put(hashTag, count);
            }
        }

        System.out.printf("Hash tag counts: %s %n", hashTagCounts);

        Map<String, List<Treet>> treetsByAuthor = new HashMap<String, List<Treet>>();

        for (Treet treet : treets) {
            List<Treet> authoredTreets = treetsByAuthor.get(treet.getAuthor());

            if (authoredTreets == null){
                authoredTreets = new ArrayList<>();
                treetsByAuthor.put(treet.getAuthor(), authoredTreets);
            }

            authoredTreets.add(treet);
        }

        System.out.printf("Treets by author: %s %n", treetsByAuthor);
        System.out.printf("Treets by Khi_bet: %s %n", treetsByAuthor.get("Khi_bet"));
    }
}
