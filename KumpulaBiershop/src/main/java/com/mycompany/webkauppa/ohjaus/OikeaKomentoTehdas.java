package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;

final class OikeaKomentoTehdas extends KomentoTehdas {

    private final Varasto varasto;
    private final PankkiFasaadi pankki;
    private final ToimitusjarjestelmaFasaadi toimitus;

    OikeaKomentoTehdas(Varasto varasto, PankkiFasaadi pankki, ToimitusjarjestelmaFasaadi toimitus) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.toimitus = toimitus;
    }

    @Override
    public Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenLisaysKoriin(ostoskori, tuoteId, varasto);
    }

    @Override
    public Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        return new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori, pankki, toimitus);
    }

    @Override
    public Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId) {
        return new OstoksenPoistoKorista(ostoskori, tuoteId, varasto);
    }
}
