package com.pingCAP.findFirstNonRepeatingWord.filetool;


import org.junit.Test;

public class fileHashTest {
    @Test
    public void test(){
//        String fileName = "D:\\workspace\\test\\test.txt";
        FileHash fileHash = new FileHash();
        int hashNum= 10;
        int fileHashNum = fileHash.getHashNum("1x1",hashNum);
        System.out.println(fileHashNum);
//        fileHash.readSourceAndHash(fileName);
//
    }

}
