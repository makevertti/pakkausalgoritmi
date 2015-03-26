
package pakkausalgoritmi;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MerkkilaskuriTest {
    private Merkkilaskuri merkkiLaskuri;
    
    @Before
    public void setUp() {
        merkkiLaskuri = new Merkkilaskuri();
    }
    
    @Test
    public void merkkilaskuriLaskeeOikein1() {
        int[] merkit = merkkiLaskuri.laskeMerkkienMaarat(new File("src/main/resources/pakkausalgoritmi/testi1.txt"));
        assertEquals(0, merkit[35]);
        assertEquals(2, merkit[76]);
        assertEquals(88, merkit[108]);
    }
    
    @Test
    public void merkkilaskuriLaskeeOikein2() {
        int[] merkit = merkkiLaskuri.laskeMerkkienMaarat(new File("src/main/resources/pakkausalgoritmi/testi2.txt"));
        assertEquals(50, merkit[47]);
        assertEquals(768, merkit[76]);
        assertEquals(0, merkit[92]);
    }
}
