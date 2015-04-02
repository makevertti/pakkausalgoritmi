
package pakkausalgoritmi.tietorakenteet;

/**
 * Tätä luokkaa käytetään Huffmanin puun rakentamisessa.
 */
public class Solmu implements Comparable<Solmu> {
    private int merkki;
    private int maara;
    private Solmu vasen;
    private Solmu oikea;
    
    /**
     *
     * @param merkki    Merkki jota solmu kuvaa, -1 jos ei lehti.
     * @param maara     Merkin esiintymismäärä.
     * @param vasen     Solmun vasen lapsi
     * @param oikea     Solmun oikea lapsi
     */
    public Solmu(int merkki, int maara, Solmu vasen, Solmu oikea) {
        this.merkki = merkki;
        this.maara = maara;
        this.vasen = vasen;
        this.oikea = oikea;
    }

    @Override
    public int compareTo(Solmu toinen) {
        return this.maara - toinen.maara;
    }
    
    public int getMerkki() {
        return this.merkki;
    }
    
    public int getMaara() {
        return this.maara;
    }
    
    public Solmu getVasen() {
        return this.vasen;
    }
    
    public Solmu getOikea() {
        return this.oikea;
    }
    
    public boolean onLehti() {
        return this.vasen == null && this.oikea == null;
    }
}
