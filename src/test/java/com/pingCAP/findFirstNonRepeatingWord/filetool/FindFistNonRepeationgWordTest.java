package com.pingCAP.findFirstNonRepeatingWord.filetool;


import com.pingCAP.findFirstNonRepeatingWord.common.Word;
import org.junit.Test;

import java.util.LinkedList;

public class FindFistNonRepeationgWordTest {
    @Test
   public  void test1(){
        FindNonRepeationgWord firstFistNonRepeationgWord = new FindNonRepeationgWord();
        Word word = firstFistNonRepeationgWord.findFirstNonRepeationgWord("D:\\workspace\\test\\1.txt");
        System.out.println(word.getWord()+ " "+word.getPosition());
    }

    @Test
    public void test2(){
        FindNonRepeationgWord firstFistNonRepeationgWord = new FindNonRepeationgWord();
        LinkedList<Word> words = firstFistNonRepeationgWord.findNonRepeationgWords("D:\\workspace\\test\\1.txt");
        for (Word word : words){
            System.out.println(word.getWord()+" "+word.getPosition());
        }
    }
}
