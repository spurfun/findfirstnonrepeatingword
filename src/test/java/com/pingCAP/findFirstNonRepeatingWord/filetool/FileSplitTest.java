package com.pingCAP.findFirstNonRepeatingWord.filetool;


import org.junit.Test;

public class FileSplitTest {
    @Test
    public void test(){
        String fileName = "D:\\workspace\\test\\test.txt";
        FileSplit fileSplit = new FileSplit();
        int blockNum=10;
        int eachNum = 100;
        String blockFilePrefix = "D:/workspace/test/";
                fileSplit.fileSplit(fileName,blockNum,eachNum,blockFilePrefix);
    }
}