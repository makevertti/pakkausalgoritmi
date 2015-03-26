
package pakkausalgoritmi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Pakkaaja pakkaaja = new Pakkaaja();
        pakkaaja.pakkaa(new File("src/main/resources/pakkausalgoritmi/testi2.txt"));  
    }
}