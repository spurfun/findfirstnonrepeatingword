package com.pingCAP.findFirstNonRepeatingWord.util;

import java.io.*;

public class Reader {
    private String fileName;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        try {
            File file = new File(fileName);
            inputStreamReader = new InputStreamReader(new FileInputStream(file));
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void close(){
        try {
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
