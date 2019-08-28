package com.pingCAP.findFirstNonRepeatingWord.filetool;

import com.pingCAP.findFirstNonRepeatingWord.util.Reader;
import com.pingCAP.findFirstNonRepeatingWord.util.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class FileSplit {
    /**
     * split 100g file to {blockNum} blocks , send (wrod position) to block file
     * {eachNum} words send to block file,then send {eachNum} words send to next block file
     */
    public void fileSplit(String fileName,int blockNum,int eachNum,String blockFilePrefix){
        long position = 0;
        Reader fileReader = null;
        HashMap<Integer, Writer> numWriter = null;
        try {
            fileReader = new Reader(fileName);
            fileReader.init();
            BufferedReader bufferedReader = fileReader.getBufferedReader();

            numWriter = new HashMap<Integer, Writer>();
            for (int i = 0; i < blockNum; i++) {
                Writer fileWriter = new Writer(blockFilePrefix + i + ".txt");
                fileWriter.init();
                numWriter.put(i, fileWriter);
            }

            String str = "";
            StringBuilder stringBuilder = new StringBuilder();
            str = bufferedReader.readLine();
            while (str != null) {
                String[] s = str.split(" ");
                for (int i =0;i<s.length;i++){
                    stringBuilder.append(s[i]+" "+position+"\n");
                    position++;
                    if (position%eachNum==0){
                        int num = (int) ((position/eachNum)%blockNum);
                        numWriter.get(num).getFileWriter().write(stringBuilder.toString());
                        stringBuilder = new StringBuilder();
                    }
                }
                str = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
            for (Integer num : numWriter.keySet()){
                numWriter.get(num).close();
            }
        }
    }
}
