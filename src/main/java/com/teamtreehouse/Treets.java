package com.teamtreehouse;

import twitter4j.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gschool on 8/7/16.
 */
public class Treets {
    public static void save(Treet[] treets) {
        try(
                FileOutputStream fos = new FileOutputStream("treets.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(treets);
        } catch(IOException ioe) {
            System.out.println("Problem saving Treets");
            ioe.printStackTrace();
        }
    }

    public static Treet[] load() {
        Treet[] treets = new Treet[0];
        try (
                FileInputStream fis = new FileInputStream("treets.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            treets = (Treet[]) ois.readObject();
        } catch(IOException ioe) {
            System.out.println("Error reading file");
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.out.println("Error loading treets");
            cnfe.printStackTrace();
        }

        return treets;
    }

    public static Treet[] loadAndSaveRemote() {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query();
        query.setQuery("java ");
        query.setCount(100);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            System.out.println("Getting tweet failed");
            e.printStackTrace();
        }
        List<Status> tweets = result.getTweets();
        List<Treet> list = new ArrayList<>();
        System.out.printf("%d tweets were retrieved %n", tweets.size());
        for (Status status : tweets) {
            list.add( new Treet(status.getUser().getScreenName(),
                                      status.getText(),
                                      status.getCreatedAt()));
        }
        Treet[] returning = list.toArray(new Treet[list.size()]);
        Treets.save(returning);
        return returning;
    }
}
