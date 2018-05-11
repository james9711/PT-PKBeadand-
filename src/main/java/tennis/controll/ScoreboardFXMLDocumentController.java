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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.Tennis;
import tennis.modell.*;
import tennis.modell.SimpleClasses.Booleans;
import tennis.modell.SimpleClasses.Game;
import tennis.modell.SimpleClasses.Point;
import tennis.modell.SimpleClasses.Set;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * A Scoreboard.fxml-t irányító osztály.
 * A GameCalculator osztályban deffiniált metdusok használatával vezeti a statisztikát a képernyőn.
 *
 * @author adamberki97
 */
public class ScoreboardFXMLDocumentController implements Initializable {

    /**
     * A logger létrehozása a ScoreboardFXMLDocumentController classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ScoreboardFXMLDocumentController.class);

    //<editor-fold defaultstate="collapsed" desc="FXML things">
    /**
     * A mérkőzés menetét reprezentáló Label-eket tartalmazó Pane.
     */
    @FXML
    private AnchorPane eredmenyjelzoPane;

    /**
     * A hazai csapat pontjait megjelenítő Label.
     */
    @FXML
    private Label labelHomePoints;
    /**
     * A vendég csapat pontjait megjelenítő Label.
     */
    @FXML
    private Label labelAwayPoints;
    /**
     * A hazai csapat pontjait megjelenítő, eredményjelzőn található Label.
     */
    @FXML
    private Label labelHomePointsInTheTable;
    /**
     * A vendég csapat pontjait megjelenítő, eredményjelzőn található Label.
     */
    @FXML
    private Label labelAwayPointsInTheTable;
    /**
     * A hazai játékos szerválását jelzi (ha nem üres).
     */
    @FXML
    private Label serveHome;
    /**
     * A vendég játékos szerválását jelzi (ha nem üres).
     */
    @FXML
    private Label serveAway;

    /**
     * A vendég játékos első szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelAwaySet1;
    /**
     * A vendég játékos második szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelAwaySet2;
    /**
     * A vendég játékos harmadik szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelAwaySet3;
    /**
     * A hazai játékos első szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelHomeSet1;
    /**
     * A hazai játékos második szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelHomeSet2;
    /**
     * A hazai játékos harmadik szettben megnyert játékait jelzi.
     */
    @FXML
    private Label labelHomeSet3;

    /**
     * A hazai játékos a mérkőzés alatt, összesen megnyert pontjait jelzi.
     */
    @FXML
    private Label labelAllHomePoints;
    /**
     * A vendég játékos a mérkőzés alatt, összesen megnyert pontjait jelzi.
     */
    @FXML
    private Label labelAllAwayPoints;

    /**
     * A hazai játékos megnyert szettjeinek számát jelzi.
     */
    @FXML
    private Label labelHomeSets;
    /**
     * A vendég játékos megnyert szettjeinek számát jelzi.
     */
    @FXML
    private Label labelAwaySets;
    /**
     * A hazai játékos a mérkőzésen ütött ászainak a számát jelzi.
     */
    @FXML
    private Label labelAllHomeAces;
    /**
     * A vendég játékos a mérkőzésen ütött ászainak a számát  jelzi.
     */
    @FXML
    private Label labelAllAwayAces;
    /**
     * A hazai játékos a mérkőzésen ütött kettős hibáinak a számát  jelzi.
     */
    @FXML
    private Label labelAllDoubleFaultsHome;
    /**
     * A vendég játékos a mérkőzésen ütött kettős hibáinak a számát  jelzi.
     */
    @FXML
    private Label labelAllDoubleFaultsAway;
    /**
     * A hazai játékos a mérkőzésen ütött kikényszerítettlen hibáinak a számát  jelzi.
     */
    @FXML
    private Label labelUnforcedHome;
    /**
     * A vendég játékos a mérkőzésen ütött kikényszerítettlen hibáinak a számát  jelzi.
     */
    @FXML
    private Label labelUnforcedAway;

//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Variables">
    /**
     * A hazai játékos pontszámát tároló Point objektum.
     */
    private Point homePoint = new Point();
    /**
     * A vendég játékos pontszámát tároló Point objektum.
     */
    private Point awayPoint = new Point();
    /**
     * A hazai játékos összesen megszerzett pontjainak számát tároló Point objektum.
     */
    private Point allHomePoint = new Point();
    /**
     * A vendég játékos összesen megszerzett pontjainak számát tároló Point objektum.
     */
    private Point allAwayPoint = new Point();

    /**
     * A hazai játékos ászainak a számát tároló Point objektum.
     */
    private Point allHomeAce = new Point();
    /**
     * A vendég játékos ászainak a számát tároló Point objektum.
     */
    private Point allAwayAce = new Point();
    /**
     * A hazai játékos kettős hibáinak a számát tároló Point objektum.
     */
    private Point allHomeDoubleFault = new Point();
    /**
     * A vendég játékos kettős hibáinak a számát tároló Point objektum.
     */
    private Point allAwayDoubleFault = new Point();
    /**
     * A hazai játékos ki nem kényszerített hibáinak a számát tároló Point objektum.
     */
    private Point allHomeUnforcedErr = new Point();
    /**
     * A vendég játékos ki nem kényszerített hibáinak a számát tároló Point objektum.
     */
    private Point allAwayUnforcedErr = new Point();

    /**
     * A hazai játékos által megnyert játszmák számát tároló Game objektum.
     */
    private Game homeGame = new Game();
    /**
     * A vendég játékos által megnyert játszmák számát tároló Game objektum.
     */
    private Game awayGame = new Game();

    /**
     * A hazai játékos által megnyert szettek számát tároló Game objektum.
     */
    private Set homeSet = new Set();
    /**
     * A vendég játékos által megnyert szettek számát tároló Game objektum.
     */
    private Set awaySet = new Set();

    /**
     * Az aktuális szett számát jelölő integer.
     */
    private Set currentSet = new Set();
    /**
     * A hazai szerválását vizsgáló boolean érték.
     * True ha a hazai játékos szervál az adott játszmában.
     */
    private Booleans homeServing = new Booleans();
    /**
     * Jelzi, hogy egy szettben tiebreak van e éppen.
     * True ha az adott szettben tiebreakra kerül a sor.
     */
    private Booleans tiebreak = new Booleans();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Button actions">

    /**
     * Mérközés újrakezdése.
     *
     * @param event : restartGameButtonra való kattintás
     */
    @FXML
    private void handlerestartGameButton(final ActionEvent event) {
        setVariablesAndLabelsDefault();
    }

    /**
     * Visszalép a főmenübe, váltás a Menu.fxml-re.
     *
     * @param event : backToMenuButtonra való kattintás
     * @throws IOException ha a Menu.fxml nem tölthető be
     */
    @FXML
    private void handlebackToMenuButton(final ActionEvent event) throws IOException {
        changeFXML("/fxml/Menu.fxml", eredmenyjelzoPane);
    }

    /**
     * Hazai pontszerzés történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : ButtonHomePointra való kattintás
     */
    @FXML
    private void handleButtonHomePoint(final ActionEvent event) {
        GameCalculator.updatePoint(homePoint, allHomePoint, homeGame, awayPoint, homeServing, tiebreak);
        updateLabelsAfterPoint();
    }

    /**
     * Vendég pontszerzés történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : ButtonAwayPointra való kattintás
     */
    @FXML
    private void handleButtonAwayPoint(final ActionEvent event) {
        GameCalculator.updatePoint(awayPoint, allAwayPoint, awayGame, homePoint, homeServing, tiebreak);
        updateLabelsAfterPoint();
    }

    /**
     * Hazai kikényszerítetlen hiba történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonUnforcedHomera való kattintás
     */
    @FXML
    private void handlebuttonUnforcedHome(final ActionEvent event) {
        allHomeUnforcedErr.increaseValue();
        GameCalculator.updatePoint(awayPoint, allAwayPoint, awayGame, homePoint, homeServing, tiebreak);
        updateLabelsAfterPoint();
        labelUnforcedHome.setText("" + allHomeUnforcedErr.getValue());
    }

    /**
     * Vendég kikényszerítetlen hiba történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonUnforcedAwayre való kattintás
     */
    @FXML
    private void handlebuttonUnforcedAway(final ActionEvent event) {
        allAwayUnforcedErr.increaseValue();
        GameCalculator.updatePoint(homePoint, allHomePoint, homeGame, awayPoint, homeServing, tiebreak);
        updateLabelsAfterPoint();
        labelUnforcedAway.setText("" + allAwayUnforcedErr.getValue());
    }

    /**
     * Hazai ász történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonAceHomera való kattintás
     */
    @FXML
    private void handlebuttonAceHome(final ActionEvent event) {
        if (homeServing.getValue()) {
            allHomeAce.increaseValue();
            GameCalculator.updatePoint(homePoint, allHomePoint, homeGame, awayPoint, homeServing, tiebreak);
            updateLabelsAfterPoint();
            labelAllHomeAces.setText("" + allHomeAce.getValue());
        } else {
            LOG.info("Only serving player can hit an ace!");
        }
    }

    /**
     * Vendég ász történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonAceHAwayra való kattintás
     */
    @FXML
    private void handlebuttonAceAway(final ActionEvent event) {
        if (!homeServing.getValue()) {
            allAwayAce.increaseValue();
            GameCalculator.updatePoint(awayPoint, allAwayPoint, awayGame, homePoint, homeServing, tiebreak);
            updateLabelsAfterPoint();
            labelAllAwayAces.setText("" + allAwayAce.getValue());
        } else {
            LOG.info("Only serving player can hit an ace!");
        }
    }

    /**
     * Hazai kettőshiba történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonDoubleFaultHomera való kattintás
     */
    @FXML
    private void handlebuttonDoubleFaultHome(final ActionEvent event) {
        if (homeServing.getValue()) {
            allHomeDoubleFault.increaseValue();
            GameCalculator.updatePoint(awayPoint, allAwayPoint, awayGame, homePoint, homeServing, tiebreak);
            updateLabelsAfterPoint();
            labelAllDoubleFaultsHome.setText("" + allHomeDoubleFault.getValue());
        } else {
            LOG.info("Only serving player can hit double fault!");
        }
    }

    /**
     * Vendég kettőshiba történt, statisztikai adatok újraszámolása, Labelek frissítése.
     *
     * @param event : buttonDoubleFaultAwayre való kattintás
     */
    @FXML
    private void handlebuttonDoubleFaultAway(final ActionEvent event) {
        if (!homeServing.getValue()) {
            allAwayDoubleFault.increaseValue();
            GameCalculator.updatePoint(homePoint, allHomePoint, homeGame, awayPoint, homeServing, tiebreak);
            updateLabelsAfterPoint();
            labelAllDoubleFaultsAway.setText("" + allAwayDoubleFault.getValue());
        } else {
            LOG.info("Only serving player can hit double fault!");
        }
    }
//</editor-fold>

    @Override
    public final void initialize(final URL url, final ResourceBundle rb) {
        setVariablesAndLabelsDefault();
    }


    /**
     * Az FXML-ek közti váltást megvalósító metódus.
     *
     * @param resource : A megjelenítendő fxml elérési útja.
     * @param oldPane  : A régi FXML, melyet leváltok a metódus során.
     *
     * @throws IOException : a betöltés problémaköre.
     */
    public final void changeFXML(final String resource, final AnchorPane oldPane) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(resource));
        oldPane.getChildren().setAll(pane);
    }

    //<editor-fold defaultstate="collapsed" desc="Functions in connection with Labels">

    /**
     * Az aktuáls, konvertált eredmény (arg1) kíírása, a megadott (arg2,arg3) Labelek-re. konvertálás: lehetővé teszi az "A":advantage kiírását, melyet az 50-es érték reprezentált
     *
     * @param point      : A kiiratandó játékos pontszámát tároló Point objektum példánya
     * @param label      : Azon Label, mely ezen átalakított értéket veszi fel
     * @param labelTable : Azon Label, mely ezen átalakított értéket veszi fel
     */
    public final void scoreConvertAndWrite(final Point point, final Label label, final Label labelTable) {
        if (point.getValue() == 50) {
            label.setText("A");
            labelTable.setText("A");
        } else {
            label.setText("" + point.getValue());
            labelTable.setText("" + point.getValue());
        }
    }

    /**
     * A currentSet counter értékétől függően frissítí az adott szetthez tartozó Labelek-et.
     */
    public final void printSetLabels() {
        switch (currentSet.getValue()) {
            case 1:
                labelHomeSet1.setText("" + homeGame.getValue());
                labelAwaySet1.setText("" + awayGame.getValue());
                break;
            case 2:
                labelHomeSet2.setText("" + homeGame.getValue());
                labelAwaySet2.setText("" + awayGame.getValue());
                break;
            case 3:
                labelHomeSet3.setText("" + homeGame.getValue());
                labelAwaySet3.setText("" + awayGame.getValue());
                break;
            default:
                break;
        }
    }

    /**
     * Az aktuálisan szerváló játékost jelzi, játékonként cseréli a String helyét!
     */
    public final void whoIsServing() {
        if (homeServing.getValue()) {
            serveHome.setText(">");
            serveAway.setText("");
        } else {
            serveHome.setText("");
            serveAway.setText(">");
        }
    }

    /**
     * Ha valamelyik játékos elérte e már a 2 nyert szettet a WhoWinPopUp.fxml lép életbe!
     */
    public final void whoWin() {
        if (homeSet.getValue() == 2 || awaySet.getValue() == 2) {
            LOG.info("Match ended!");
            try {
                changeFXML("/fxml/WhoWinPopUp.fxml", eredmenyjelzoPane);
            } catch (IOException ex) {
                System.out.println("A meccs utáni panel nem nyitható meg!");
            }
        }
    }

    /**
     * Az eredményjelzőnk (label-ek) alapállapozba állítása és a változóink lenullázásával.
     */
    public final void setVariablesAndLabelsDefault() {

        LOG.info("Variables set to dafault!");

        homePoint.setValue(0);
        awayPoint.setValue(0);
        allHomePoint.setValue(0);
        allAwayPoint.setValue(0);
        allHomeAce.setValue(0);
        allAwayAce.setValue(0);
        allHomeDoubleFault.setValue(0);
        allAwayDoubleFault.setValue(0);
        allHomeUnforcedErr.setValue(0);
        allAwayUnforcedErr.setValue(0);
        homeSet.setValue(0);
        awaySet.setValue(0);
        homeGame.setValue(0);
        awayGame.setValue(0);

        currentSet.setValue(1);
        homeServing.setValue(true);

        printPointsLabel();
        printAllPoints();
        printAllDoubleFaults();
        printAllUnforcedErrors();
        printAllAces();

        printSet1();
        printSet2();
        printSet3();
        printSets();

        whoIsServing();
    }

    /**
     * Pontszerzés utáni állapotok beállítása. Pontok újraszámolása, és ezen új értékek frissítése az adott Label-eken.
     */
    public final void updateLabelsAfterPoint() {
        printPointsLabel();
        printAllPoints();
        printSetLabels();
        GameCalculator.updateSets(homeGame, awayGame, homeSet, awaySet, currentSet, tiebreak);
        printSets();
        whoIsServing();
        whoWin();
    }

    /**
     * A homePoint, awayPoint Point objektumokhoz tartozó Label-ek frissítése.
     */
    public final void printPointsLabel() {
        scoreConvertAndWrite(awayPoint, labelAwayPoints, labelAwayPointsInTheTable);
        scoreConvertAndWrite(homePoint, labelHomePoints, labelHomePointsInTheTable);
    }

    /**
     * A allHomePoint, allAwayPoint Point objektumokhoz tartozó Label-ek frissítése.
     */
    public final void printAllPoints() {
        DecimalFormat formatter = new DecimalFormat("#0.0");
        Double hazaiNyertPontSzazalek;
        Double vendegNyertPontSzazalek;
        if (allHomePoint.getValue() + allAwayPoint.getValue() == 0) {
            hazaiNyertPontSzazalek = 0.0;
            vendegNyertPontSzazalek = 0.0;
        } else {
            hazaiNyertPontSzazalek = ((double) allHomePoint.getValue() / (allHomePoint.getValue() + allAwayPoint.getValue())) * 100;
            vendegNyertPontSzazalek = 100 - hazaiNyertPontSzazalek;
        }

        labelAllHomePoints.setText("" + allHomePoint.getValue() + " (" + formatter.format(hazaiNyertPontSzazalek) + ")");
        labelAllAwayPoints.setText("" + allAwayPoint.getValue() + " (" + formatter.format(vendegNyertPontSzazalek) + ")");
    }

    /**
     * A allHomeDoubleFault, allAwayDoubleFault Point objektumokhoz tartozó Label-ek frissítése.
     */
    public final void printAllDoubleFaults() {
        labelAllDoubleFaultsHome.setText("" + allHomeDoubleFault.getValue());
        labelAllDoubleFaultsAway.setText("" + allAwayDoubleFault.getValue());
    }

    /**
     * A allHomeUnforcedErr, allAwayUnforcedErr Point objektumokhoz tartozó Label-ek frissítése.
     */
    public final void printAllUnforcedErrors() {
        labelUnforcedHome.setText("" + allHomeUnforcedErr.getValue());
        labelUnforcedAway.setText("" + allAwayUnforcedErr.getValue());
    }

    /**
     * A allHomeAce, allAwayAce Point objektumokhoz tartozó Label-ek frissítése.
     */
    public final void printAllAces() {
        labelAllHomeAces.setText("" + allHomeAce.getValue());
        labelAllAwayAces.setText("" + allAwayAce.getValue());
    }

    /**
     * Az első Set Label-einek frissítése az adott homeGame, AwayGame Game objektumok aktuális értékeivel.
     */
    public final void printSet1() {
        labelHomeSet1.setText("" + homeGame.getValue());
        labelAwaySet1.setText("" + awayGame.getValue());
    }

    /**
     * Az második Set Label-einek frissítése az adott homeGame, AwayGame Game objektumok aktuális értékeivel.
     */
    public final void printSet2() {
        labelHomeSet2.setText("" + homeGame.getValue());
        labelAwaySet2.setText("" + awayGame.getValue());
    }

    /**
     * Az harmadik Set Label-einek frissítése az adott homeGame, AwayGame Game objektumok aktuális értékeivel.
     */
    public final void printSet3() {
        labelHomeSet3.setText("" + homeGame.getValue());
        labelAwaySet3.setText("" + awayGame.getValue());
    }

    /**
     * A megnyert Set-ekhez (homeSet, awaySet) tartozó Label-ek frissítése.
     */
    public final void printSets() {
        labelHomeSets.setText("" + homeSet.getValue());
        labelAwaySets.setText("" + awaySet.getValue());
    }

//</editor-fold>

}

