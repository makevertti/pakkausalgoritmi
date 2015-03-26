
package pakkausalgoritmi.tietorakenteet;

/**
 * Tätä luokkaa käytetään Huffmanin puun rakentamisessa.
 */
public class Solmu implements Comparable<Solmu> {
    private int merkki;
    private int maara;
    private Solmu oikea;
    private Solmu vasen;
    private boolean onLehti;
    
    /**
     *
     * @param merkki    Merkki jota solmu kuvaa, -1 jos ei lehti.
     * @param maara     Merkin esiintymismäärä.
     * @param oikea     Solmun oikea lapsi
     * @param vasen     Solmun vasen lapsi
     */
    public Solmu(int merkki, int maara, Solmu oikea, Solmu vasen) {
        this.merkki = merkki;
        this.maara = maara;
        this.oikea = oikea;
        this.vasen = vasen;
        this.onLehti = (this.oikea == null && this.vasen == null);
    }

    @Override
    public int compareTo(Solmu toinen) {
        return this.maara - toinen.maara;
    }
    
    @Override
    public String toString() {
        return (char)this.merkki + ": " + this.maara;
    }
    
    public int getMerkki() {
        return this.merkki;
    }
    
    public int getMaara() {
        return this.maara;
    }
    
    public Solmu getOikea() {
        return this.oikea;
    }
    
    public Solmu getVasen() {
        return this.vasen;
    }
    
    public boolean onLehti() {
        return onLehti;
    }
}
