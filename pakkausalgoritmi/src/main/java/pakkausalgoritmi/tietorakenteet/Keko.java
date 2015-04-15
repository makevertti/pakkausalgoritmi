
package pakkausalgoritmi.tietorakenteet;

/**
 * Minimikeon toiminnallisuutta toteuttava luokka
 */
public class Keko {
    private Solmu[] keko;
    private int koko;

    /**
     * Luo keon annetulla maksimikoolla
     *
     * @param maksimikoko Keon maksimikoko
     */
    public Keko(int maksimikoko) {
        this.koko = 0;
        this.keko = new Solmu[maksimikoko];
    }

    /**
     * Lisää kekoon parametrina annetun solmun
     * ja varmistaa että kekoehto säilyy
     *
     * @param lisattava Lisättävä solmu
     */
    public void lisaa(Solmu lisattava) {
        int indeksi = koko;
        keko[indeksi] = lisattava;

        while (keko[indeksi].getMaara() < keko[indeksi / 2].getMaara()) {
            vaihda(indeksi, indeksi / 2);
            indeksi = indeksi / 2;
        }
        koko++;
    }

    private void vaihda(int a, int b) {
        Solmu apu = keko[a];
        keko[a] = keko[b];
        keko[b] = apu;
    }

    /**
     * Poistaa keosta pienimmän solmun
     * ja korjaa keon rakenteen
     *
     * @return  Keon pienin solmu
     */
    public Solmu haePienin() {
        if (koko == 0) {
            return null;
        }

        Solmu pienin = keko[0];
        keko[0] = keko[koko - 1];
        keko[koko - 1] = null;
        koko--;
        korjaaKeko(0);
        return pienin;
    }

    private void korjaaKeko(int indeksi) {
        if (!onLehti(indeksi)) {
            if (keko[indeksi].getMaara() > keko[indeksi * 2].getMaara() || keko[indeksi].getMaara() > keko[indeksi * 2 + 1].getMaara()) {
                if (keko[indeksi * 2].getMaara() < keko[indeksi * 2 + 1].getMaara()) {
                    vaihda(indeksi, indeksi * 2);
                    korjaaKeko(indeksi * 2);
                } else {
                    vaihda(indeksi, indeksi * 2 + 1);
                    korjaaKeko(indeksi * 2 + 1);
                }
            }
        }
    }

    private boolean onLehti(int indeksi) {
        return indeksi >= (koko / 2) && indeksi <= koko;
    }

    /**
     * Hakee keon hetkellisen koon
     *
     * @return Keon koko
     */
    public int getKoko() {
        return koko;
    }
}
