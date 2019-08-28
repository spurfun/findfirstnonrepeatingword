package com.pingCAP.findFirstNonRepeatingWord.executor;


import org.junit.Test;

public class ReduceTest {
    @Test
    public void test(){
        Reduce reduce = new Reduce();
//        String hashFileNamePrefix,int hashNum
        String hashFileNamePrefix = "D:\\workspace\\test\\hash";
        int hashNum= 10;
        String result = reduce.getResult(hashFileNamePrefix, hashNum);
        System.out.println(result);
    }
}
