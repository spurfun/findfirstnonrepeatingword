package com.pingCAP.findFirstNonRepeatingWord.executor;

import com.pingCAP.findFirstNonRepeatingWord.common.Word;
import com.pingCAP.findFirstNonRepeatingWord.filetool.FindNonRepeationgWord;

import java.util.ArrayList;

public class Reduce {
    public String getResult(String hashFileNamePrefix,int hashNum){
        ArrayList<Word> nonRepeationWords = getNonRepeationWords(hashFileNamePrefix, hashNum);
        int resultIndex = 0;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0;i<nonRepeationWords.size();i++){
            int position = nonRepeationWords.get(i).getPosition();
            if (position>=0 && minPosition>position){
                minPosition = nonRepeationWords.get(i).getPosition();
                resultIndex = i;
            }
        }
        return nonRepeationWords.get(resultIndex).getWord();
    }

    public ArrayList<Word> getNonRepeationWords(String hashFileNamePrefix, int hashNum){
        ArrayList<Word> words = new ArrayList<Word>();
        FindNonRepeationgWord findNonRepeationgWord = new FindNonRepeationgWord();
        for (int i =0;i<hashNum;i++){
            Word nonRepeationgWord = findNonRepeationgWord.findFirstNonRepeationgWord(hashFileNamePrefix + i + ".txt");
            words.add(nonRepeationgWord);
        }
        return words;
    }
}