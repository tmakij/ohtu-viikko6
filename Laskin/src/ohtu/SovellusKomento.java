package ohtu;

import javax.swing.JTextField;

public abstract class SovellusKomento implements Komento {

    protected final Sovelluslogiikka sovellus;
    protected final JTextField tuloskentta, syotekentta;
    private int arvoEnnenMuutoksia;

    protected SovellusKomento(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public final void suorita() {
        arvoEnnenMuutoksia = sovellus.tulos();
        suoritaEnnenPaivitysta();
        paivitaRuutu();
    }

    private void paivitaRuutu() {
        int laskunTulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }

    protected abstract void suoritaEnnenPaivitysta();

    @Override
    public final void peru() {
        sovellus.nollaa();
        sovellus.plus(arvoEnnenMuutoksia);
        paivitaRuutu();
    }
}
