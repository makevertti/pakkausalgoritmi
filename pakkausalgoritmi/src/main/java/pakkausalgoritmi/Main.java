
package pakkausalgoritmi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Pakkaaja pakkaaja = new Pakkaaja(new File("src/main/resources/pakkausalgoritmi/testi2.txt"), new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"));
        pakkaaja.pakkaa();
        
        Purkaja purkaja = new Purkaja(new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"), new File("src/main/resources/pakkausalgoritmi/purettu.txt"));
        purkaja.pura();
    }
}