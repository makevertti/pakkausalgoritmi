
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
     * Luo uuden solmun
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

    /**
     * Vertaa kahta solmua niiden merkkimäärän perusteella
     *
     * @param toinen    Solmu johon tätä solmua verrataan
     * @return          Palauttaa positiiviesen luvun jos
     *                  verrattava solmu on pienempi ja
     *                  muuten negatiivisen luvun
     */
    @Override
    public int compareTo(Solmu toinen) {
        return maara - toinen.maara;
    }

    /**
     * Hakee merkin jota solmu vastaa
     *
     * @return Merkki jota solmu kuvaa
     */
    public int getMerkki() {
        return merkki;
    }

    /**
     * Hakee solmun kuvaaman merkin esiintymismäärän
     *
     * @return Merkin esiintymismäärä
     */
    public int getMaara() {
        return maara;
    }

    /**
     * Hakee solmun vasemman lapsen
     *
     * @return Vasen lapsi
     */
    public Solmu getVasen() {
        return vasen;
    }

    /**
     * Hakee solmun oikean lapsen
     *
     * @return Oikea lapsi
     */
    public Solmu getOikea() {
        return oikea;
    }

    /**
     * Kertoo onko solmu lehti
     *
     * @return Onko solmu lehti
     */
    public boolean onLehti() {
        return vasen == null && oikea == null;
    }
}
