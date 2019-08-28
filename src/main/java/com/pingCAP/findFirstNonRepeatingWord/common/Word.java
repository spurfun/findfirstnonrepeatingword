package com.pingCAP.findFirstNonRepeatingWord.common;

public class Word {
    private int position;
    private String word;

    public Word(int position, String word) {
        this.position = position;
        this.word = word;
    }

    public int getPosition() {
        return position;
    }

    public String getWord() {
        return word;
    }
}
