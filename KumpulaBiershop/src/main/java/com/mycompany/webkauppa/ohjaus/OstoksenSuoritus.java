package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;

final class OstoksenSuoritus implements Komento {

    private PankkiFasaadi pankki;
    private final ToimitusjarjestelmaFasaadi toimitusjarjestelma;
    private final String asiakkaanNimi;
    private final String postitusosoite;
    private final String luottokortti;
    private final Ostoskori ostoskori;

    OstoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori,
            PankkiFasaadi pankki, ToimitusjarjestelmaFasaadi toimitus) {
        this.pankki = pankki;
        this.toimitusjarjestelma = toimitus;
        this.asiakkaanNimi = nimi;
        this.postitusosoite = osoite;
        this.luottokortti = luottokorttinumero;
        this.ostoskori = kori;
    }

    @Override
    public boolean suorita() {
        if (asiakkaanNimi.length() == 0 || postitusosoite.length() == 0 || ostoskori.tuotteitaKorissa() == 0) {
            return false;
        }

        if (!pankki.maksa(asiakkaanNimi, luottokortti, ostoskori.hinta())) {
            return false;
        }

        toimitusjarjestelma.kirjaatoimitus(asiakkaanNimi, postitusosoite, ostoskori.ostokset());
        ostoskori.tyhjenna();

        return true;
    }

    public void setPankki(PankkiFasaadi pankki) {
        this.pankki = pankki;
    }
}
