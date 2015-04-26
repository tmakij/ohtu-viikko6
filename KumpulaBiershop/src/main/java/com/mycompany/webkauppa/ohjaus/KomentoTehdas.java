package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import com.mycompany.webkauppa.sovelluslogiikka.Varasto;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;

public abstract class KomentoTehdas {

    public static KomentoTehdas komennot(Varasto varasto, PankkiFasaadi pankki, ToimitusjarjestelmaFasaadi toimitus) {
        return new OikeaKomentoTehdas(varasto, pankki, toimitus);
    }

    //??? ei tarvittu
    public static KomentoTehdas testKomennot(Varasto varasto, PankkiFasaadi pankki, ToimitusjarjestelmaFasaadi toimitus) {
        return new OikeaKomentoTehdas(varasto, pankki, toimitus);
    }

    public abstract Komento ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId);

    public abstract Komento ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori);

    public abstract Komento ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId);
}
