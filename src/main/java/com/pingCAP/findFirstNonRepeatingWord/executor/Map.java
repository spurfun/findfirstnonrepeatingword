package com.pingCAP.findFirstNonRepeatingWord.executor;

import com.pingCAP.findFirstNonRepeatingWord.common.Word;
import com.pingCAP.findFirstNonRepeatingWord.filetool.FileHash;
import com.pingCAP.findFirstNonRepeatingWord.filetool.FindNonRepeationgWord;
import com.pingCAP.findFirstNonRepeatingWord.util.Writer;

import java.util.HashMap;
import java.util.LinkedList;

public class Map {
    public void readBlockFileAndFilterHash(String hashFileNamePrefix,int hashNum,String blockFilePrefix,int blockNum){
        HashMap<Integer, Writer> numWriter = null;

        numWriter = new HashMap<Integer, Writer>();
        FindNonRepeationgWord findNonRepeationgWord = new FindNonRepeationgWord();
        FileHash fileHash = new FileHash();
        try {
            numWriter = new HashMap<Integer, Writer>();
            for (int i = 0; i < hashNum; i++) {
                Writer fileWriter = new Writer(hashFileNamePrefix + i + ".txt");
                fileWriter.init();
                numWriter.put(i, fileWriter);
            }

            for (int i = 0;i<blockNum;i++){
                String blockFileName = blockFilePrefix+i+".txt";
                LinkedList<Word> nonRepeationgWords = findNonRepeationgWord.findNonRepeationgWords(blockFileName);
                fileHash.sendWordToHashFile(nonRepeationgWords,numWriter,hashNum);
            }
        } finally {
            for (Integer num : numWriter.keySet()){
                numWriter.get(num).close();
            }
        }
    }
}
