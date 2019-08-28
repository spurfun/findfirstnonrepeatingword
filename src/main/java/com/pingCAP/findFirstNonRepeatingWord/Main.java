package com.pingCAP.findFirstNonRepeatingWord;

import com.pingCAP.findFirstNonRepeatingWord.executor.Map;
import com.pingCAP.findFirstNonRepeatingWord.executor.Reduce;
import com.pingCAP.findFirstNonRepeatingWord.filetool.FileSplit;
import com.pingCAP.findFirstNonRepeatingWord.util.ConfigUtils;

public class Main {
    public static void main(String[] args) {
        //100G file name
        String fileName = ConfigUtils.getFileName();
        //hash file prerfix name
        String hashFileNamePrefix = ConfigUtils.getHashFileNamePrefix();
        //hash file num
        int hashNum = ConfigUtils.getHashNum();
        //block file prefix name
        String blockFilePrefix = ConfigUtils.getBlockFilePrefix();
        // block file num
        int blockNum = ConfigUtils.getBlockNum();
        //{eachNum} words send to block file,then send {eachNum} words send to next block file
        int eachNum = ConfigUtils.getEachNum();

        /**
         * split 100g file to {blockNum} blocks
         */
        FileSplit fileSplit = new FileSplit();
        fileSplit.fileSplit(fileName, blockNum, eachNum ,blockFilePrefix);

        /**
         * in map,read every block ,hash and send non-repeating word to {hashNum} files, make sure same word send to same hashFile
         */
        Map map = new Map();
        map.readBlockFileAndFilterHash(hashFileNamePrefix, hashNum, blockFilePrefix, blockNum);

        /**
         * in reduce,read every hashFile, get first non-repeating word in hashFIles,  then select fist word in this words and return
         */
        Reduce reduce = new Reduce();
        String result = reduce.getResult(hashFileNamePrefix, hashNum);

        System.out.println(result);
    }
}
