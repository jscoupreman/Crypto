/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g32666.cry.esi.heb.be;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author u99jsc
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("files/Ciphertext.hex")); // to read a single line from the file
        int read;
        String src= new String();       // to store the string obtained from buffered reader
        PrintWriter writer= new PrintWriter("fileOutput");
        src=br.readLine();              // read an input line from the file

        while(src!=null){
            src=src.replace(" ", "");   // Trim out the spaces
            for(int i=0;i<src.length();i+=2){
                
                System.out.println((char)Integer.parseInt(xorHex(src.substring(i,i+2), "28"), 16));
                
                System.exit(0);
                read=Integer.parseInt(src.substring(i,i+2), 16);    // convert the String to hex integer 
                System.out.println(Integer.toHexString((int)(Integer.getInteger("28") ^ read)));
                System.exit(0);
                writer.print((char)read);                           // convert hex to char and write into file
            }
            src=br.readLine();
        }
        writer.flush();
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
