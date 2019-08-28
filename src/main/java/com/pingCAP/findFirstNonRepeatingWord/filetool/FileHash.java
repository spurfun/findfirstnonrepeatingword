package com.pingCAP.findFirstNonRepeatingWord.filetool;

import com.pingCAP.findFirstNonRepeatingWord.common.Word;
import com.pingCAP.findFirstNonRepeatingWord.util.Writer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class FileHash {
    /**
     * send non-repeating word to hash file
     * @param words Word-class doubly linked list
     * @param numWriter （hashindex,Writer-class） map
     * @param hashNum hash file num
     */
    public void sendWordToHashFile(LinkedList<Word> words, HashMap<Integer, Writer> numWriter, int hashNum) {
        try {
            for (Word word : words) {
                int fileHashNum = getHashNum(word.getWord(),hashNum);
                numWriter.get(fileHashNum).getFileWriter().write(word.getWord() + " " + word.getPosition() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * transform {input} to hashindex
     * @param input word in files
     * @param hashNum hash file num
     * @return hashindex
     */
    public int getHashNum(String input,int hashNum) {
        if (input.equals(""))
            return 0;
        return (input.charAt(0) + input.charAt(input.length() - 1)) % hashNum;
    }
}
