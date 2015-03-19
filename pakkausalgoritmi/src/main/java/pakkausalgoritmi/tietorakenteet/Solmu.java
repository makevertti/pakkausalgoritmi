
package pakkausalgoritmi.tietorakenteet;

public class Solmu implements Comparable<Solmu>{
    private char merkki;
    private int maara;
    private Solmu oikea;
    private Solmu vasen;
    
    public Solmu(char merkki, int maara, Solmu oikea, Solmu vasen) {
        this.merkki = merkki;
        this.maara = maara;
        this.oikea = oikea;
        this.vasen = vasen;
    }

    @Override
    public int compareTo(Solmu toinen) {
        return this.maara - toinen.maara;
    }
}
