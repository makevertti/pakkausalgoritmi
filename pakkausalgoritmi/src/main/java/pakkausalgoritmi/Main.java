
package pakkausalgoritmi;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pakkaaja pakkaaja = new Pakkaaja(new File("src/main/resources/pakkausalgoritmi/testi2.txt"), new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"));
        pakkaaja.pakkaa();

        Purkaja purkaja = new Purkaja(new File("src/main/resources/pakkausalgoritmi/pakattuTiedosto"), new File("src/main/resources/pakkausalgoritmi/purettu.txt"));
        purkaja.pura();

        /*
        Scanner lukija = new Scanner(System.in);

        while (true) {
            System.out.print("1: Pakkaa \n2: Pura \n3: Lopeta \n>");
            String syote = lukija.nextLine();

            System.out.println();

            if (syote.equals("1")) {
                System.out.print("Pakattava tiedosto: \n>");
                String pakattavaTiedosto = lukija.nextLine();

                System.out.print("Kirjoitettava tiedosto: \n>");
                String kirjoitettavaTiedosto = lukija.nextLine();

                Pakkaaja pakkaaja = new Pakkaaja(new File(pakattavaTiedosto), new File(kirjoitettavaTiedosto));
                pakkaaja.pakkaa();
                System.out.println("-Tiedosto pakattu-");
            } else if (syote.equals("2")) {
                System.out.print("Purettava tiedosto: \n>");
                String purettavaTIedosto = lukija.nextLine();
                System.out.println();

                System.out.print("Kirjoitettava tiedosto: \n>");
                String kirjoitettavaTiedosto = lukija.nextLine();
                System.out.println();

                Purkaja purkaja = new Purkaja(new File(purettavaTIedosto), new File(kirjoitettavaTiedosto));
                purkaja.pura();
                System.out.println("-Tiedosto purettu-");
            } else if (syote.equals("3")){
                break;
            } else {
                System.out.println("Virheellinen syöte");
            }
            System.out.println();
        }
        */
    }
}