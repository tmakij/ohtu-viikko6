package ohtu.intjoukkosovellus;

import java.util.Arrays;

public final class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private final int kasvatuskoko;
    private int[] lukuJono;
    private int alkioidenLukumaara = 0;

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasitteetti on negatiivinen");
        }
        if (kasvatuskoko <= 0) {
            throw new IllegalArgumentException("Kasvatuskoko on epäpositiivinen");
        }
        lukuJono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    private void lisaa(IntJoukko joukko) {
        for (int i = 0; i < joukko.mahtavuus(); i++) {
            lisaa(joukko.lukuJono[i]);
        }
    }

    public boolean lisaa(int luku) {
        if (kuuluuJoukkoon(luku)) {
            return false;
        }
        varmistaTilaLukuJonossa();
        lukuJono[alkioidenLukumaara] = luku;
        alkioidenLukumaara++;
        return true;
    }

    private void varmistaTilaLukuJonossa() {
        if (lukuJono.length == alkioidenLukumaara) {
            lukuJono = Arrays.copyOf(lukuJono, lukuJono.length + kasvatuskoko);
        }
    }

    public boolean kuuluuJoukkoon(int luku) {
        return haeLuvunPaikka(luku) >= 0;
    }

    private int haeLuvunPaikka(int luku) {
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukuJono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int luvunKohta = haeLuvunPaikka(luku);
        if (luvunKohta < 0) {
            return false;
        }
        poistaAlkioLukuJonosta(luvunKohta);
        return true;
    }

    private void poistaAlkioLukuJonosta(int alkionPaikka) {
        alkioidenLukumaara--;
        boolean alkioOnViimeinen = alkionPaikka == alkioidenLukumaara;
        if (alkioOnViimeinen) {
            return;
        }
        int[] uusiJono = new int[lukuJono.length];
        System.arraycopy(lukuJono, 0, uusiJono, 0, alkionPaikka);
        System.arraycopy(lukuJono, alkionPaikka + 1, uusiJono, alkionPaikka, lukuJono.length - alkionPaikka - 1);
        lukuJono = uusiJono;
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }

    @Override
    public String toString() {
        StringBuilder tulos = new StringBuilder();
        tulos.append('{');
        if (alkioidenLukumaara > 0) {
            kirjoitaAlkiot(tulos);
        }
        tulos.append('}');
        return tulos.toString();
    }

    private void kirjoitaAlkiot(StringBuilder tulos) {
        tulos.append(lukuJono[0]);
        for (int i = 1; i < alkioidenLukumaara; i++) {
            tulos.append(',').append(' ').append(lukuJono[i]);
        }
    }

    public int[] toIntArray() {
        return Arrays.copyOf(lukuJono, alkioidenLukumaara);
    }

    public static IntJoukko yhdiste(IntJoukko ensimmainen, IntJoukko toinen) {
        IntJoukko yhdiste = new IntJoukko(ensimmainen.mahtavuus() + toinen.mahtavuus());
        yhdiste.lisaa(ensimmainen);
        yhdiste.lisaa(toinen);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko ensimmainen, IntJoukko toinen) {
        return lisaaNeJotkaKuuluvatEhdolla(ensimmainen, toinen, true);
    }

    public static IntJoukko erotus(IntJoukko lahto, IntJoukko erotettava) {
        return lisaaNeJotkaKuuluvatEhdolla(lahto, erotettava, false);
    }

    private static IntJoukko lisaaNeJotkaKuuluvatEhdolla(IntJoukko ensimmainen, IntJoukko toinen, boolean kuuluuJoukkoon) {
        IntJoukko kuuluvat = new IntJoukko();
        for (int i = 0; i < ensimmainen.mahtavuus(); i++) {
            int arvo = ensimmainen.lukuJono[i];
            if (toinen.kuuluuJoukkoon(arvo) == kuuluuJoukkoon) {
                kuuluvat.lisaa(arvo);
            }
        }
        return kuuluvat;
    }
}
