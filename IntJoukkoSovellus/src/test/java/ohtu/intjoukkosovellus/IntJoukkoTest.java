package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaa(10);
        joukko.lisaa(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaa(4);
        assertEquals(3, joukko.mahtavuus());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaa(10);
        joukko.lisaa(3);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluuJoukkoon(10));
        assertFalse(joukko.kuuluuJoukkoon(5));
        assertTrue(joukko.kuuluuJoukkoon(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.kuuluuJoukkoon(3));
        assertEquals(1, joukko.mahtavuus());
    }

    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};

        joukko.lisaa(55);
        joukko.poista(10);
        joukko.lisaa(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }

    @Test
    public void toimiiKasvatuksenJalkeen() {
        int[] lisattavat = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14};
        for (int luku : lisattavat) {
            joukko.lisaa(luku);
        }
        assertEquals(14, joukko.mahtavuus());
        assertTrue(joukko.kuuluuJoukkoon(11));
        joukko.poista(11);
        assertFalse(joukko.kuuluuJoukkoon(11));
        assertEquals(13, joukko.mahtavuus());
    }

    @Test
    public void poistoToimiiYhdella() {
        joukko = new IntJoukko();
        joukko.lisaa(1);
        joukko.poista(1);
        assertFalse(joukko.kuuluuJoukkoon(1));
    }

    @Test
    public void poistoToimiiKunEiOle() {
        joukko.poista(5);
        assertEquals(2, joukko.mahtavuus());
    }

    @Test
    public void virheEpakelvollaParametrilla2() {
        luoJoukkoParametreilla(-1, 1);
    }

    @Test
    public void virheEpakelvollaParametrilla3() {
        luoJoukkoParametreilla(1, 0);
    }

    @Test
    public void virheEpakelvollaParametrilla4() {
        luoJoukkoParametreilla(1, -1);
    }

    private void luoJoukkoParametreilla(int kapasiteetti, int kasvatuskoko) {
        try {
            joukko = new IntJoukko(kapasiteetti, kasvatuskoko);
            fail("Epäkelpo parametri ei kaatanut");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void toStringToimiiTyhjalla() {
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }

    @Test
    public void toStringToimii() {
        assertEquals("{10, 3}", joukko.toString());
    }
}
