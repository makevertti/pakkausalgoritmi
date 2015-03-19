
package pakkausalgoritmi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        int[] merkit;
        
        Merkkilaskuri merkkilaskuri = new Merkkilaskuri();
        merkit = merkkilaskuri.laskeMerkkienMaarat(new File("src/main/resources/pakkausalgoritmi/testi.txt"));
        
        for (int i = 0; i < merkit.length; i++) {
            if (merkit[i] != 0) {
                System.out.println((char)i + ": " + merkit[i]);
            }
        }
    }
}