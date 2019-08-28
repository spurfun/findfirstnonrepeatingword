package com.pingCAP.findFirstNonRepeatingWord.util;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    @Test
    public void test(){
        Reader fileReader = new Reader("C:\\Users\\tsymq\\Desktop\\test.txt");
        fileReader.init();
        BufferedReader bufferRader = fileReader.getBufferedReader();
        Writer fileWriter = new Writer("C:\\Users\\tsymq\\Desktop\\test2.txt");
        fileWriter.init();
        FileWriter bufferedWriter = fileWriter.getFileWriter();
        String str = "";
        try {
            str = bufferRader.readLine();
            while (str!=null){
                System.out.println(str);
                bufferedWriter.write(str+ "\n");
                str = bufferRader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileReader.close();
            fileWriter.close();
        }
    }
}
