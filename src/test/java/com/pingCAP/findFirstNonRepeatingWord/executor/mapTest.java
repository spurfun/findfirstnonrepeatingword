package com.pingCAP.findFirstNonRepeatingWord.executor;


import org.junit.Test;

public class mapTest {
    @Test
    public void test(){
        Map map = new Map();
//        String hashFileNamePrefix,int hashNum,String blockFilePrefix,int blockNum
        String hashFileNamePrefix = "D:\\workspace\\test\\hash";
        int hashNum= 10;
        String blockFilePrefix = "D:\\workspace\\test\\";
        int blockNum=10;
        map.readBlockFileAndFilterHash(hashFileNamePrefix,hashNum,blockFilePrefix,blockNum);
    }
}
