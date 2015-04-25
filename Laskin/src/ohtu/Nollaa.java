package ohtu;

import javax.swing.JTextField;

public final class Nollaa extends SovellusKomento {

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus, tuloskentta, syotekentta);
    }

    @Override
    protected void suoritaEnnenPaivitysta() {
        sovellus.nollaa();
    }
}
