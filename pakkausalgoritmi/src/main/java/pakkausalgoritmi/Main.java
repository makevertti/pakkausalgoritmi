
package pakkausalgoritmi;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        
//        Bittikirjoitin bittikirjoitin = new Bittikirjoitin(new File("src/main/resources/pakkausalgoritmi/testi"));
//        bittikirjoitin.kirjoitaBitti(1);
//        bittikirjoitin.kirjoitaBitti(1);
//        bittikirjoitin.kirjoitaBitti(0);
//        bittikirjoitin.kirjoitaBitti(1);
//        bittikirjoitin.kirjoitaBitti(0);
//        bittikirjoitin.kirjoitaBitti(0);
//        bittikirjoitin.kirjoitaBitti(1);
//       
//        
//        bittikirjoitin.sulje();
//        
//        Bittilukija bittilukija = new Bittilukija(new File("src/main/resources/pakkausalgoritmi/testi"));
//        for (int i = 0; i < 10; i++) {
//            System.out.println(bittilukija.lueBitti());
//        }
//        bittilukija.sulje();
        
        Pakkaaja pakkaaja = new Pakkaaja(new File("src/main/resources/pakkausalgoritmi/testi2.txt"), new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"));
        pakkaaja.pakkaa();
        
        Purkaja purkaja = new Purkaja(new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"), new File("src/main/resources/pakkausalgoritmi/purettu.txt"));
        purkaja.pura();
    }
}