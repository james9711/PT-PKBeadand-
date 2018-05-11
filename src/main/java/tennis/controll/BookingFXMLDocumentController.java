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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.dao.BookingEntity;
import tennis.dao.SampleDB;
import tennis.modell.SimpleClasses.BookDatas;
import tennis.modell.PdfGeneration;
import tennis.Tennis;

/**
 * A foglalási panelt kezelő osztály.
 *
 * @author adamberki97
 */
public class BookingFXMLDocumentController {

    /**
     * A logger létrehozása a BookingFXMLDocumentController osztályba.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BookingFXMLDocumentController.class);

    /**
     * DB_MANAGER deffiniálása (SampleDB), példány visszaadása.
     */
    private static final SampleDB DB_MANAGER = SampleDB.getDbPeldany();

    /**
     * A BookingEntity osztály egy példánya bookingEntity néven.
     */
    private static BookingEntity bookingEntity = new BookingEntity();

    /**
     * A BookingEntity osztály egy példánya bookingEntityChecker néven.
     */
    private static BookingEntity bookingEntityChecker = new BookingEntity();


    //<editor-fold defaultstate="collapsed" desc="FXML things and variables">

    /**
     * A foglalás lebonyolításához tartozó Pane.
     */
    @FXML
    private AnchorPane bookingPane;
    /**
     * A bookingPane-en belüli, fő/alapértelmezett panel.
     */
    @FXML
    private Pane foglalasPane;
    /**
     * A bookingPane-en belüli, a foglalasPane-en történő foglalás után felugró panel.
     */
    @FXML
    private Pane foglalasUtanPane;

    /**
     * A foglalás dátuma választható ki vele.
     */
    @FXML
    private DatePicker datePicker;

    /**
     * A foglalás időtartalma választható ki.
     */
    @FXML
    private ChoiceBox hourCB;

    /**
     * A lefoglalni kívát pálya választható ki.
     */
    @FXML
    private ChoiceBox courtCB;

    /**
     * A datePicker által kiválasztott dátum megjelenítésének helye.
     */
    @FXML
    private Label showdateLabel;
    /**
     * A foglalás idejének az órája adható itt meg.
     */
    @FXML
    private TextField hourTF;
    /**
     * A foglalás kalkulált árának megjelenítésének helye.
     */
    @FXML
    private Label foglalasAraLabel;

    /**
     * Diák létünk jelzésére szolgáló CheckBox. True esetén kedvezményes árra vagyunk jogosúltak.
     */
    @FXML
    private CheckBox diakCB;
    /**
     * Diák létünk jelzésére szolgáló CheckBox. True esetén kedvezményes árra vagyunk jogosúltak.
     */
    @FXML
    private CheckBox dolgozoCB;
    /**
     * Foglalásunk adatainak PDF-be való mentése jelezhető. A CheckBox True értékével kreál és nyitja meg.
     */
    @FXML
    private CheckBox needPdfCB;
    /**
     * A foglaló személy nevének megadásának a helye.
     */
    @FXML
    private TextField foglaloNeveTF;

    /**
     * Foglalási kísérlet utáni információk jelenítődnek meg ezen Label-ön.
     */
    @FXML
    private Label foglalasRogzitesLabel;

    /**
     * A felhasználó a sikeres foglalás után kapott kódot ide írva válthatja be.
     */
    @FXML
    private Label codeLabel;

    /**
     * A felhasználónak a foglalás első lépését megjelenító Label.
     */
    @FXML
    private Label step1Label;

    /**
     * A felhasználónak a foglalás második lépését megjelenító Label.
     */
    @FXML
    private Label step2Label;

    /**
     * A felhasználónak a foglalás harmadik lépését megjelenító Label.
     */
    @FXML
    private Label step3Label;

    /**
     * A datePicker által kiválasztott, foglalás dátumát tároló String.
     */
    private LocalDate selectedDate;

    /**
     * A PDF mentését szervező felugró ablak deklarálása.
     */
    private FileChooser fileChooser;

    /**
     * A File osztály egy példánya, a PDF generáláshoz később.
     */
    private File file;

    /**
     * A BookDatas osztály egy példánya, az adatbázis kezeléséhez.
     */
    private BookDatas bookDatas = new BookDatas();

    /**
     * A pálya foglalása ettől az időponttól lép érvényes.
     */
    private int fromInHour;

    /**
     * A pálya foglalása eddig az időpontig tart.
     */
    private int untilInHour;

    boolean isBookingSuccesfull;

//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Button actions">

    /**
     * A kiválasztott dátum showdateLabel-en való megjelenítése.
     *
     * @param event : datePicker által dátum lett kiválasztva.
     */
    public final void showDate(final ActionEvent event) {
        LOG.info("Date selected!");
        if (datePicker.getValue().isAfter(LocalDate.now())) {
            LOG.info("Selected date is valid!");
            selectedDate = datePicker.getValue();
            showdateLabel.setText(datePicker.getValue().toString());
        } else {
            LOG.info("Selected date is in the past!");
            usePopUpWindow("Please select date from the future!");
        }
    }

    /**
     * A foglalás árának kiszámítása.  Akkor, ha a kellő adatok meg lettek adva.
     * Visszajelzés a foglalasRogzitesLabel-en keresztül.
     *
     * @param event : CalculateButton-re kattintás történt.
     */
    @FXML
    private void handleCalculateButton(final ActionEvent event) {

        if (hourTF.getText() != null && hourCB.getValue() != null) {

            fromInHour = Integer.parseInt(hourTF.getText());
            untilInHour = Integer.parseInt(hourCB.getValue().toString()) + fromInHour;

            if (fromInHour >= 8 && untilInHour <= 18) {
                step1Label.setTextFill(Color.web("#08FF00"));
                calcAndWriteReservationPrice();
                LOG.info("Valid calculation made!");
            } else {
                LOG.info("Invalid calculation! Selected hour is not in the proper time!");
                usePopUpWindow("We are not open at the selected time!");
            }
        } else {
            LOG.info("Invalid calculation! Something is not set with tha resrevation hours!");
            usePopUpWindow("For calc please fill the hour and duration section!");
        }

    }


    /**
     * A kalkuláció után, a foglalás rögzítése. Akkor, ha történt foglalás és a kellő adatok meg lettek adva.
     * Visszajelzés a foglalasRogzitesLabel-en keresztül.
     *
     * @param event : foglalasButton-ra kattintás történt.
     * @throws IOException        ha a createAndopenPDF() metódus nem megfelelően hajtódik végre.
     * @throws URISyntaxException ha a createAndopenPDF()-ben a Filekezelés sikertelen.
     */
    @FXML
    private void handlefoglalasButton(final ActionEvent event) {
        if (!foglaloNeveTF.getText().isEmpty() && courtCB.getValue() != null && selectedDate != null) {

            step2Label.setTextFill(Color.web("#08FF00"));
            step3Label.setTextFill(Color.web("#08FF00"));
            setBookDatas();
            saveDatasToDataBase();
            showdateLabel.setText(selectedDate.toString());

        } else {
            LOG.info("Invalid reservation!");
            usePopUpWindow("No reservation yet!");
        }

    }

    /**
     * Váltás a Menu.fxml-re.
     *
     * @param event : foglalasBackToMenu-re kattintás történt.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlefoglalasBackToMenu(final ActionEvent event) throws IOException {
        changeFXML("/fxml/Menu.fxml", bookingPane);
        LOG.info("Menu opened!");
    }

    /**
     * Foglalás után, a visszajellzés láttamozása.
     * Visszaugrás a BookCourt.fxml
     *
     * @param event : foglalasRogzitesOK-ra kattintás történt.
     * @throws IOException ha az fxml betöltése sikertelen.
     */
    @FXML
    private void handlefoglalasRogzitesOK(final ActionEvent event) throws IOException {
        step1Label.setTextFill(Color.web("#F0F0F0"));
        step2Label.setTextFill(Color.web("#F0F0F0"));
        step3Label.setTextFill(Color.web("#F0F0F0"));
        foglalasPane.setOpacity(1);
        foglalasUtanPane.setVisible(false);
        changeFXML("/fxml/BookCourt.fxml", bookingPane);
        LOG.info("Booking pane opened!");
    }

    /**
     * A diakCB Check box-ra kattintásával a dolgozoCB kiütése.
     *
     * @param event : A diakCB-ra kattintás történt.
     */
    @FXML
    private void handlediakCB(final ActionEvent event) {
        dolgozoCB.setSelected(false);
    }

    /**
     * A dolgzoCB Check box-ra kattintva a diakCB kiütése.
     *
     * @param event : A dolgozoCB-ra kattintás történt.
     */
    @FXML
    private void handledolgozoCB(final ActionEvent event) {
        diakCB.setSelected(false);
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Functions">

    /**
     * PDF generálódik és nyitódik meg.
     */
    public final void createAndopenPDF() {
        try {
            PdfGeneration pdfCreator = new PdfGeneration();
            pdfCreator.pdfGeneration(bookDatas.getCustomersName(), bookDatas.getChoosenDate(), bookDatas.getFoglaltPalya(), bookDatas.getFoglalasAra(), file);
            Desktop.getDesktop().open(file);
            LOG.info("PDF opened!");
        } catch (NullPointerException e) {
            LOG.error("The PDF can not be opened, the file does not exist!");
        } catch (URISyntaxException e) {
            LOG.error("The PDF can not be opened, the file does not exist!");
        } catch (IOException e) {
            LOG.error("The PDF can not be opened, the file does not exist!");
        }
    }

    /**
     * Kiszámolja majd kiírja az adott Label-re a foglalás kalkulált árát az adott időtartamra.
     */
    public final void calcAndWriteReservationPrice() {
        int segedAr;
        segedAr = 2000 * Integer.parseInt(hourCB.getValue().toString());
        if (diakCB.isSelected() || dolgozoCB.isSelected()) {
            segedAr = (int) (segedAr * 0.5);
            foglalasAraLabel.setText(Integer.toString(segedAr));
            LOG.info("Reduced price...!");
        } else {
            foglalasAraLabel.setText(Integer.toString(segedAr));
        }
        bookDatas.setFoglalasAra(segedAr);
        LOG.info("Price calculated!");
    }

    /***
     *A generálandó PDF nevét és mentési helyét megadó ablak előhozása.
     * A kiválasztás után a PDF legenerálását és megnyitását végző createAndopenPDF meghívása.
     */
    public final void savePDF() {

        if (needPdfCB.isSelected()) {
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PDF files", "*.pdf")
            );
            file = fileChooser.showSaveDialog(null);
            createAndopenPDF();
        }
        LOG.info("PDF saved!");

    }

    /**
     * Az entity komponenseit beállító metódus.
     */
    public final void setEntityValues() {
        bookingEntity.setResdate(bookDatas.getChoosenDate());
        bookingEntity.setName(bookDatas.getCustomersName());
        bookingEntity.setPrice(bookDatas.getFoglalasAra());
        bookingEntity.setCourt(bookDatas.getFoglaltPalya());
        bookingEntity.setCode(bookDatas.getCodeForProg());
        LOG.info("Values for database are ready!");
    }

    /**
     * A már beállított Entity példány mentése az adatbázisba.
     */
    public final void saveDatasToDataBase() {

        setEntityValues();

        try {
            bookingEntityChecker = DB_MANAGER.checkIsAReservationPossible(bookingEntity.getResdate(), bookingEntity.getCourt());
            if (bookingEntityChecker == null) {
                usePopUpWindow("Dear " + bookDatas.getCustomersName() + ", we booked your reservation!");
                codeLabel.setText("Your code for our programm is: " + bookDatas.getCodeForProg());
                savePDF();
                DB_MANAGER.save(bookingEntity);
                LOG.info("Reservation made!");
            } else {
                usePopUpWindow("Choosen date: " + bookingEntity.getResdate() + "already booked!");
                LOG.info("Date already booked!");
            }
        } catch (IllegalArgumentException e) {
            LOG.error("IllegalArgumentException");
        } catch (Exception e) {
            LOG.error("Exception");
        }

    }

    /**
     * A sikeres foglalásokhoz tartozó kód generálását végzó metódus.
     *
     * @param length : a generálandó String hosszát megadó paraméter.
     * @return saltStr : a generált String/ kód.
     */
    private String getRndCode(final Integer length) {
        LOG.info("Code generated for the customer!");
        String posibilities = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder rndCode = new StringBuilder();
        Random rnd = new Random();
        while (rndCode.length() < length) {
            int index = (int) (rnd.nextFloat() * posibilities.length());
            rndCode.append(posibilities.charAt(index));
        }
        String saltStr = rndCode.toString();
        return saltStr;
    }

    /**
     * A foglalási adatokat tároló bookDatas objektum komponnseinek a beállítása.
     */
    private void setBookDatas() {
        bookDatas.setCustomersName(foglaloNeveTF.getText());
        bookDatas.setChoosenDate(selectedDate + " : " + fromInHour + " h - " + untilInHour + " h");
        bookDatas.setFoglaltPalya(Integer.parseInt(courtCB.getValue().toString()));
        bookDatas.setCodeForProg(getRndCode(6));
        LOG.info("Reservation details are ready!");
    }

    /**
     * Az esetenként felugró ablak szövegének beállítása és az ablak megjelenítése.
     *
     * @param msg : foglalasRogzitesLabel szövegét ezen Stringre állítja be.
     */
    public final void usePopUpWindow(final String msg) {
        foglalasRogzitesLabel.setText(msg);
        foglalasPane.setOpacity(0.6);
        foglalasUtanPane.setVisible(true);
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
        LOG.info("Pop Up window appeared!");
    }

    //</editor-fold>

}
