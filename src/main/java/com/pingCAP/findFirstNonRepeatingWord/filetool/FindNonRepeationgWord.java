package com.pingCAP.findFirstNonRepeatingWord.filetool;

import com.pingCAP.findFirstNonRepeatingWord.common.Word;
import com.pingCAP.findFirstNonRepeatingWord.util.Reader;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class FindNonRepeationgWord {
    /**
     * find non-repeating word in {fileName}
     * use map and doubly linked list find non-repeating word
     * @param fileName input file
     * @return Word-class doubly linked list
     */
    public LinkedList<Word> findNonRepeationgWords (String fileName) {
        Reader reader = new Reader(fileName);
        try {
            reader.init();
            BufferedReader bufferedReader = reader.getBufferedReader();

            HashMap<String, Word> stringNodeHashMap = new HashMap<String, Word>();
            LinkedList<Word> words = new LinkedList<Word>();

            String str = "";
            str = bufferedReader.readLine();
            while (str != null) {
                String[] s = str.split(" ");
                String string = s[0];
                int position = Integer.valueOf(s[1]);
                Word word = new Word(position, string);

                if (!stringNodeHashMap.containsKey(string)) {
                    // if word not in stringNodeHashMap,insert into put stringNodeHashMap k = string v = word and add word at the tail of doubly linked list
                    stringNodeHashMap.put(string, word);
                    words.addLast(word);
                } else {
                    // if word in stringNodeHashMap , means this is repeating,remove the word from doubly linked list,and set value for k=string in stringNodeHashMap to be null
                    words.remove(stringNodeHashMap.get(string));
                    stringNodeHashMap.put(string, null);
                }
                str = bufferedReader.readLine();
            }

            //at last ,all the word in doubly linked list is non-repeating
            return words;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return null;
    }

    public Word findFirstNonRepeationgWord(String fileName){
        /**
         * the fiist word in doubly linked list is firstNonRepeationgWord in {fileName}
         */
        LinkedList<Word> words = findNonRepeationgWords(fileName);
        if (words.size() == 0)
            return new Word(-1,"");
        return words.getFirst();
    }
}