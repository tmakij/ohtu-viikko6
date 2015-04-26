package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Tuote;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;

final class OstoksenLisaysKoriin implements Komento {

    private final Ostoskori ostoskori;
    private final long tuoteId;
    private final Varasto varasto;

    OstoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId, Varasto varasto) {
        this.ostoskori = ostoskori;
        this.tuoteId = tuoteId;
        this.varasto = varasto;
    }

    @Override
    public boolean suorita() {
        boolean saatiinTuote = varasto.otaVarastosta(tuoteId);

        if (!saatiinTuote) {
            return false;
        }

        Tuote tuote = varasto.etsiTuote(tuoteId);
        ostoskori.lisaaTuote(tuote);
        return true;
    }
}
