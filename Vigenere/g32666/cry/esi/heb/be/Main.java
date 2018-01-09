/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g32666.cry.esi.heb.be;

import core.g32666.cry.esi.heb.be.HexaData;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author u99jsc
 */
public class Main {
    public static void main(String[] Args){
        HexaData fm = new HexaData();
        fm.readFile("files/Ciphertext.hex");
        //System.out.println(fm);
        //for(List<String> it : fm.getData()){
            //System.out.println(fm.getKey(it));
            //System.out.println(it);
        //}
        String key = null;
        List<List<String>> hex = fm.getData();
        for(int i=0; i<hex.get(0).size(); i++){
            for(int j=0; j<5; j++){
                switch(j){
                    case 0: key = "28";
                        break;
                    case 1: key = "57";
                        break;
                    case 2: key = "eb";
                        break;
                    case 3: key = "ba";
                        break;
                    case 4: key = "0d";
                        break;
                }
                System.out.print((char)Integer.parseInt(xorHex(hex.get(j).get(i), key), 16));
            }
        }
        for(int i=0; i<5; i++){
            
        }
        for(String s : fm.getData().get(0)){
            //System.out.println(s);
            System.out.print((char)Integer.parseInt(xorHex(s, "28"), 16));
        }
        
        for(String s : fm.getData().get(1)){
            //System.out.println(s);
            System.out.print((char)Integer.parseInt(xorHex(s, "57"), 16));
        }
    }
    
    public static String xorHex(String a, String b) {
        // TODO: Validation
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
        }
        return new String(chars);
    }

    private static int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        throw new IllegalArgumentException();
    }

    private static char toHex(int nybble) {
        if (nybble < 0 || nybble > 15) {
            throw new IllegalArgumentException();
        }
        return "0123456789ABCDEF".charAt(nybble);
    }
}
