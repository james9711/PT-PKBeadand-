package tennis.controll;

/*-
 * #%L
 * TennisGyak
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.Tennis;
import tennis.dao.BookingEntity;
import tennis.dao.SampleDB;

/**
 * Főbb FXML-ek között való váltást, a ststisztika vezetőhöz való jogosultság vizsgálatát megvalósító osztály.
 *
 * @author adamberki97
 */
public class FXMLDocumentController {

    /**
     * A logger létrehozása az FXMLDocumentController classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FXMLDocumentController.class);

    /**
     * DB_MANAGER deffiniálása (SampleDB), példány visszaadása.
     */
    private static final SampleDB DB_MANAGER = SampleDB.getDbPeldany();

    /**
     * A BookingEntity osztály egy példánya bookingEntityChecker néven.
     */
    private static BookingEntity bookingEntityChecker = new BookingEntity();

//<editor-fold defaultstate="collapsed" desc="FXML things">

    /**
     * A mérkőzés után felugró ablakhoz tartozó Pane.
     */
    @FXML
    private AnchorPane meccsUtanPane;

    /**
     * A főmenühöz tartozó Pane.
     */
    @FXML
    private AnchorPane menuPane;

    /**
     * A foglalást ellenőrző panel, a generált kód bekérésével.
     */
    @FXML
    private Pane loginPane;

    /**
     * A foglalás után a statisztika vezető panelhez való belépéshez a kód megadására szolgáló TextField.
     */
    @FXML
    private TextField codeTF;

    /**
     * A felhasználónak nyújt visszajelzést a megadott kód érvényességéről.
     */
    @FXML
    private Label isCodeValidLabel;

    /**
     * A kód érvényességét tároló boolean változó. True, ha a megadott kód érvényes volt, ellenkező esetben false.
     */
    boolean codeAccepted = false;

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Button actions">

    /**
     * Mérközés kezdődik.
     * Váltás a Scoreboard.fxml-re.
     *
     * @param event :  menuStartGameButton lenyomása.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlemenuStartGameButton(final ActionEvent event) throws IOException {
        loginPane.setVisible(true);
        codeAccepted = false;
    }

    /**
     * Pályát szeretnéne foglalni.
     * Váltás a BookCourt.fxml-re.
     *
     * @param event :  menuBookACourt lenyomása.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlemenuBookACourt(final ActionEvent event) throws IOException {
        changeFXML("/fxml/BookCourt.fxml", menuPane);
        LOG.info("Booking pane opened!");
    }

    /**
     * Mérkőzés vége, visszalépés a főmenűbe.
     * Váltás a Menu.fxml-re.
     *
     * @param event :  meccsUtanButton lenyomása.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlemeccsUtanButton(final ActionEvent event) throws IOException {
        changeFXML("/fxml/Menu.fxml", meccsUtanPane);
        LOG.info("Menu opened!");
    }

    /**
     * Újabb mérkőzés kezdődik.
     * Váltás vissza, a Scoreboard.fxml-re.
     *
     * @param event :  newGameButton lenyomása.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlenewGameButton(final ActionEvent event) throws IOException {
        changeFXML("/fxml/Scoreboard.fxml", meccsUtanPane);
        LOG.info("Match restarted!");
    }

    /**
     * A kódbeváltás kísérletét managelő billentyűműködés.
     *
     * @param event :  redeemCode lenyomása.
     * @throws Exception ha a kód lekérése sikertelen.s
     */
    @FXML
    private void handleredeemCode(final ActionEvent event) throws Exception {
        bookingEntityChecker = DB_MANAGER.checkIfValidCode(codeTF.getText());
        isThisCodeValid();
    }

    /**
     * Sikeres kód beváltása esetén a statisztikavezető indítása, egyébként visszagomb a menube.
     *
     * @param event :  a Start lenyomása.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handleStart(final ActionEvent event) throws IOException {
        if (codeAccepted) {
            changeFXML("/fxml/Scoreboard.fxml", menuPane);
            LOG.info("Match can be started!");
        } else {
            LOG.info("Match can not start until a valid code was given!");
        }
        loginPane.setVisible(false);
        LOG.info("Back to menu!");
    }

    /**
     * Sikeres kódbeváltás esetén az adatbázisban az adott kód frissítése used-ra, a többszöri használhatóság megelőzése végett.
     */
    public final void setAcceptedCodeUsed() {
        bookingEntityChecker.setCode("used");
        try {
            DB_MANAGER.save(bookingEntityChecker);
            LOG.error("Used Code updated!");
        } catch (Exception e1) {
            LOG.error("Can not update Code value!");
        }
    }

    /**
     * A felhasználó által megadott kódot ellenőrzi az adatbázis segítségével.
     */
    public final void isThisCodeValid() {

        if (bookingEntityChecker != null && codeTF.getText() != "used") {
            setAcceptedCodeUsed();
            codeAccepted = true;
            isCodeValidLabel.setTextFill(Color.web("#5cf54e"));
            isCodeValidLabel.setText("Code was valid! Have fun! :)");
            LOG.info("Code was valid!");
        } else {
            codeAccepted = false;
            isCodeValidLabel.setTextFill(Color.web("#cd0b0b"));
            isCodeValidLabel.setText("Code was invalid! >:c");
            LOG.info("Code was invalid!");
        }

    }

    /**
     * Az FXML-ek közti váltást megvalósító metódus.
     *
     * @param resource : A megjelenítendő fxml elérési útja.
     * @param oldPane  : A régi FXML, melyet leváltok a metódus során.
     * @throws IOException : a betöltés problémaköre.
     */
    public final void changeFXML(final String resource, final AnchorPane oldPane) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(resource));
        oldPane.getChildren().setAll(pane);
    }

//</editor-fold>

}
