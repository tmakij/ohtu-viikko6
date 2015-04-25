package ohtu;

import javax.swing.JTextField;

public final class Laskutoimittaja extends SovellusKomento {

    private final Laskutoimitus toimitus;

    public Laskutoimittaja(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta, Laskutoimitus toimitus) {
        super(sovellus, tuloskentta, syotekentta);
        this.toimitus = toimitus;
    }

    @Override
    protected void suoritaEnnenPaivitysta() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        toimitus.laske(arvo);
    }
}
