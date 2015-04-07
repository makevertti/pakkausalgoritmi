
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
        return maara - toinen.maara;
    }
    
    public int getMerkki() {
        return merkki;
    }
    
    public int getMaara() {
        return maara;
    }
    
    public Solmu getVasen() {
        return vasen;
    }
    
    public Solmu getOikea() {
        return oikea;
    }
    
    public boolean onLehti() {
        return vasen == null && oikea == null;
    }
}
