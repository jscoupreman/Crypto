/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paradoxeanniversaire;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author u99jsc
 */
public class ParadoxeAnniversaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(double i=0; i<56; i++){
            double t = ((57*i)-47)/23;
            System.out.println(String.format("i %f %.2f",i,  t));
        }
        
        System.exit(0);
        
        List<Integer> liste = new ArrayList<>();
        int result=0;
        int moyenne = -1;
        for(int i=0; i<1000; i++){
            boolean notFound = true;
            while(notFound){
                int randInt = (int) (Math.random() * (Math.pow(2, 16)-1));
                if(liste.contains(randInt)){
                    result = liste.size();
                    System.out.println("result : " + result);
                    notFound = false;
                }else{
                    liste.add(randInt);
                }
            }
            liste = new ArrayList<>();
            if(moyenne == -1){
                moyenne = result;
            }else{
                moyenne = (result + moyenne)/2;
            }
            System.out.println("moyenne : " + moyenne);
        }
        System.out.println(moyenne);
    }
    
}
