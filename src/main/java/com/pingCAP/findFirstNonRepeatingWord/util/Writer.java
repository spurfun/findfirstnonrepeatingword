package com.pingCAP.findFirstNonRepeatingWord.util;

import java.io.*;

public class Writer {
    private String fileName;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public Writer(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter=new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
