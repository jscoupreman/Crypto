/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.g32666.cry.esi.heb.be;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author u99jsc
 */
public class HexaData {
    
    private List<List<String>> data;
    
    public HexaData(){
        this.data = new ArrayList();
        for(int i=0; i<5; i++){
            this.data.add(new ArrayList());
        }
    }
    
    private HashMap<String, AtomicInteger> getStats(List<String> list){
        HashMap<String, AtomicInteger> map = new HashMap();
        for(String s : list){
            if(map.containsKey(s)){
                map.replace(s, new AtomicInteger(map.get(s).incrementAndGet()));
            }else{
                map.put(s, new AtomicInteger(1));
            }
        }
        return map;
    }
    
    public String getKey(List<String> list){
        String key = "";
        for(Entry<String, AtomicInteger> entry : getStats(list).entrySet()){
            System.out.println("Key : " + entry.getKey() +
                    " - Value : " + entry.getValue());
        }
        return key;
    }
    
    public List<List<String>> getData(){
        return this.data;
    }
    
    @Override
    public String toString(){
        String buffer = "";
        for(List<String> i : data){
            buffer = i.stream().map((j) -> j + " ").reduce(buffer, String::concat);
            buffer += "\n";
        }
        return buffer;
    }
    
    private void processLine(String line){
        String[] array = line.split(" ");
        for(int i=0; i<array.length; i++){
            this.data.get(i).add(array[i]);
        }
    }
    
    public void readFile(String path){
        try{
            //FileReader reader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            try{
                /*
                StringBuffer buffer = new StringBuffer();
                char[] cbuf = new char[2048];
                int len;
                while((len = reader.read(cbuf)) > 0){
                    buffer.append(cbuf, 0, len);
                }
                processLine(buffer.toString());
                */
                String line;
                while((line = buffer.readLine()) != null){
                    processLine(line);
                }
            }finally{
                //reader.close();
                buffer.close();
            }
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
