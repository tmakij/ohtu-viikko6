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

public class OstoksenLisaysKoriinTest {

    Varasto varasto = Varasto.getInstance();
    Ostoskori kori;
    long tuoteid = 1;
    Tuote tuote;

    KomentoTehdas komennot = KomentoTehdas.testKomennot(varasto, PankkiFasaadi.getInstance(), ToimitusjarjestelmaFasaadi.getInstance());

    @Before
    public void setUp() {
        tuote = varasto.etsiTuote(tuoteid);
        if (tuote.getSaldo() == 0) {
            tuote.setSaldo(1);
        }
        kori = new Ostoskori();
    }

    @Test
    public void koriSisaltaaLisatynTuotteen() {
        komennot.ostoksenLisaysKoriin(kori, tuoteid).suorita();

        assertEquals(1, kori.tuotteitaKorissa());
        assertEquals(tuote.getHinta(), kori.hinta());
        assertEquals(tuote.getNimi(), kori.ostokset().get(0).tuotteenNimi());
    }

    @Test
    public void tuotteenMaaraVahentyy() {
        int varastossaAluksi = varasto.etsiTuote(tuoteid).getSaldo();

        komennot.ostoksenLisaysKoriin(kori, tuoteid).suorita();

        assertEquals(varastossaAluksi - 1, varasto.etsiTuote(tuoteid).getSaldo());
    }

    @Test
    public void josTuotteenVarastosaldoNollaEiTuotettaLaitetaOstoskoriin() {
        varasto.etsiTuote(tuoteid).setSaldo(0);

        komennot.ostoksenLisaysKoriin(kori, tuoteid).suorita();

        assertEquals(0, kori.tuotteitaKorissa());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.ostokset().size());
    }
}
