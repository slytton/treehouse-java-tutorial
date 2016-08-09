package com.teamtreehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by gschool on 8/6/16.
 */
public class Treet implements Comparable<Treet>, Serializable {
    private static final long serialVersionUID = -1753454516422841451L;
    private String mAuthor;
    private String mDescription;
    private Date mCreationDate;
    private Boolean mBreakIt = true;

    public Treet(String author, String description, Date creationDate) {
        mAuthor = author;
        mDescription = description;
        mCreationDate = creationDate;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public List<String> getWords() {
        String[] words = mDescription.toLowerCase().split("[^\\w#@\"']+");
        return Arrays.asList(words);
    }

    public List<String> getHashTags() {
        return getWordsPrefixedWith("#");
    }

    public List<String> getMentions() {
        return getWordsPrefixedWith("@");
    }

    private List<String> getWordsPrefixedWith(String prefix) {
        List<String> results = new ArrayList<String>();
        for(String word: getWords()) {
            if(word.startsWith(prefix)){
                results.add(word);
            }
        }

        return results;
    }
    @Override
    public int compareTo(Treet other) {
        if(equals(other)) {
            return 0;
        }
        int dateCmp = mCreationDate.compareTo(other.mCreationDate);
        if (dateCmp == 0){
            return mDescription.compareTo(other.mDescription);
        }
        return dateCmp;
    }

    @Override
    public String toString() {

        return String.format("Treet: \"%s\" by %s on %s",
                mDescription, mAuthor, mCreationDate);
    }
}
