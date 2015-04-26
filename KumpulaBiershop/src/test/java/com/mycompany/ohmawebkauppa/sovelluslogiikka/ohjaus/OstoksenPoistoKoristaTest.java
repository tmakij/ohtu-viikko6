package com.mycompany.ohmawebkauppa.sovelluslogiikka.ohjaus;

import com.mycompany.webkauppa.ohjaus.KomentoTehdas;
import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class OstoksenPoistoKoristaTest {

    Varasto varasto = Varasto.getInstance();
    Ostoskori kori;
    long tuoteid = 1;
    KomentoTehdas komennot = KomentoTehdas.testKomennot(varasto, PankkiFasaadi.getInstance(), ToimitusjarjestelmaFasaadi.getInstance());

    @Before
    public void setUp() {
        Tuote tuote = varasto.etsiTuote(tuoteid);
        kori = new Ostoskori();
        kori.lisaaTuote(tuote);
    }

    @Test
    public void poistettuTuoteEiEnaaKorissa() {
        komennot.ostoksenPoistoKorista(kori, tuoteid).suorita();

        assertEquals(0, kori.tuotteitaKorissa());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.ostokset().size());
    }

    @Test
    public void tuotteenMaaraKasvaa() {
        int varastossaAluksi = varasto.etsiTuote(tuoteid).getSaldo();

        komennot.ostoksenPoistoKorista(kori, tuoteid).suorita();

        assertEquals(varastossaAluksi + 1, varasto.etsiTuote(tuoteid).getSaldo());
    }
}
