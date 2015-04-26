package com.mycompany.ohmawebkauppa.sovelluslogiikka.ohjaus;

import com.mycompany.webkauppa.ohjaus.KomentoTehdas;
import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class OstoksenSuoritusTest {

    PankkiFasaadi pankki = PankkiFasaadi.getInstance();
    PankkiFasaadi hylkaavaPankki = teeHylkaavaPankki();
    ToimitusjarjestelmaFasaadi toimitusJarjestelma = ToimitusjarjestelmaFasaadi.getInstance();
    Varasto varasto = Varasto.getInstance();

    long tuoteId1;
    long tuoteId2;
    Tuote tuote1;
    Tuote tuote2;
    Ostoskori kori;
    String nimi;
    String osoite;
    String luottokortti;

    KomentoTehdas komennot = KomentoTehdas.testKomennot(varasto, pankki, toimitusJarjestelma);
    KomentoTehdas komennotHylkaavaPankki = KomentoTehdas.testKomennot(varasto, hylkaavaPankki, toimitusJarjestelma);

    @Before
    public void setUp() {
        nimi = "Arto Vihavainen";
        osoite = "Herttoniemenranta 10 Helsinki";
        luottokortti = "12345";

        tuoteId1 = 1;
        tuote1 = varasto.etsiTuote(tuoteId1);
        tuoteId2 = 2;
        tuote2 = varasto.etsiTuote(tuoteId2);

        kori = new Ostoskori();
        kori.lisaaTuote(tuote1);
        kori.lisaaTuote(tuote2);
    }

    @Test
    public void josMaksuOnnistuuKoriTyhjenee() {
        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori).suorita();

        assertEquals(0, kori.ostokset().size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tuotteitaKorissa());
    }

    @Test
    public void josMaksuOnnistuuPankinRajapintaaKaytetty() {
        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori).suorita();
    }

    @Test
    public void josMaksuOnnistuuToiRajmituksenapintaaKaytetty() {
        komennot.ostoksenSuoritus(nimi, osoite, luottokortti, kori).suorita();
    }

    // - tyhjä kori, nimi tai osoite -> ei kutsuta pankkia, ei toimitusta
    @Test
    public void josPankkiEiHyvaksyMaksuaPalautetaanFalseToimitustaEiTehda() {
        boolean suoritusOnnistui = komennotHylkaavaPankki.ostoksenSuoritus(nimi, osoite, luottokortti, kori).suorita();
        assertFalse(suoritusOnnistui);

        // assertSomething
    }

    // epäonnistuessa kori säilyy ennallaan
    private PankkiFasaadi teeHylkaavaPankki() {
        return new PankkiFasaadi() {

            @Override
            public boolean maksa(String nimi, String luottokortti, int hinta) {
                return false;
            }

        };
    }
}
