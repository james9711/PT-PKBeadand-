package tennis.modell;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.Tennis;
import tennis.modell.SimpleClasses.Booleans;
import tennis.modell.SimpleClasses.Game;
import tennis.modell.SimpleClasses.Point;
import tennis.modell.SimpleClasses.Set;

/**
 * A mérkőzés menetét irányító függvényeket tartalmazó osztály.
 *
 * @author adamberki97
 */
public class GameCalculator {

    /**
     * A logger létrehozása a GameCalculator classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GameCalculator.class);

    /**
     * A paraméterként megadott példányhoz tartozó játékos szerzett pontot,
     * a változók és labelek frissítései eszerint történnek, attól függően hogy tiebreak van e vagy sem.
     *
     * @param pontszerzoPoint   : a pontot szerzo játékoshoz tartozó Point objektum példánya
     * @param pontszerzoAll     : a pontot szerzo játékoshoz tartozó Point objektum azon példánya, melyben az összesen megszerzett pontokat tároljuk
     * @param pontszerzoGame    : a pontot szerzo játékoshoz tartozó Game objektum példánya
     * @param menetvesztesPoint : azon játékoshoz tartozó Point objektum példánya, aki ellen szerezték a pontot
     * @param homeServing       : jelzi melyik játékos szervál éppen (true ha a hazai)
     * @param tiebreak          : jelzi, ha a Set során tiebreak következne (true ha a Set tiebreakkal dől el)
     */
    public static void updatePoint(final Point pontszerzoPoint, final Point pontszerzoAll, final Game pontszerzoGame,
                                   final Point menetvesztesPoint, final Booleans homeServing, final Booleans tiebreak) {
        pontszerzoAll.increaseValue();
        if (tiebreak.getValue()) {
            GameCalculator.recalculateTiebreakPoints(pontszerzoPoint, pontszerzoGame, menetvesztesPoint, homeServing, tiebreak);
        } else {
            GameCalculator.recalculatePoints(pontszerzoPoint, menetvesztesPoint, pontszerzoGame, homeServing);
        }
    }

    /**
     * Noveli az elso paraméterként megadott példány értékét a meccs állásához mérten.
     *
     * @param pontszerzoPoint   : a pontot szerzo játékoshoz tartozó Point objektum példánya.
     * @param menetvesztesPoint : azon játékoshoz tartozó Point objektum példánya, aki ellen szerezték a pontot.
     * @param pontszerzoGame    : a pontot szerzo játékoshoz tartozó Game objektum példánya.
     * @param homeServing       : jelzi melyik játékos szervál éppen (true ha a hazai).
     */
    public static void recalculatePoints(final Point pontszerzoPoint, final Point menetvesztesPoint,
                                         final Game pontszerzoGame, final Booleans homeServing) {
        LOG.info("Point was hit. Point recalculated successfully!");
        switch (pontszerzoPoint.getValue()) {
            case 0:
                pontszerzoPoint.setValue(15);
                break;
            case 15:
                pontszerzoPoint.setValue(30);
                break;
            case 30:
                pontszerzoPoint.setValue(40);
                break;
            case 40:
                if (menetvesztesPoint.getValue() < 40) {
                    pontszerzoGame.increaseValue();
                    pontszerzoPoint.setValue(0);
                    menetvesztesPoint.setValue(0);
                    homeServing.inverseValue();
                } else if (menetvesztesPoint.getValue() == 40) {
                    pontszerzoPoint.setValue(50);
                } else if (menetvesztesPoint.getValue() == 50) {
                    pontszerzoPoint.setValue(40);
                    menetvesztesPoint.setValue(40);
                }
                break;

            case 50:
                pontszerzoGame.increaseValue();
                pontszerzoPoint.setValue(0);
                menetvesztesPoint.setValue(0);
                homeServing.inverseValue();
                break;
        }
    }

    /**
     * Az elso paraméterként megadott objektum (Point home/ awayPoint)-hoz tartozó játékos szerzett ponot a tiebreak során.
     * A már megszerzett pontok szerint növeli a pontot szerző játékos pontszámát a tiebreak szabályai szerint 1-el.
     *
     * @param pontszerzoPoint   : a pontot szerzo játékoshoz tartozó Point objektum példánya
     * @param pontszerzoGame    : a pontot szerzo játékoshoz tartozó Game objektum példánya
     * @param menetvesztesPoint : azon játékoshoz tartozó Point objektum példánya, aki ellen szerezték a pontot
     * @param homeServing       : jelzi melyik játékos szervál éppen (true ha a hazai).
     * @param tiebreak          : jelzi, ha a Set során tiebreak következne (true ha a Set tiebreakkal dől el)
     */
    public static void recalculateTiebreakPoints(final Point pontszerzoPoint, final Game pontszerzoGame,
                                           final Point menetvesztesPoint, final Booleans homeServing, final Booleans tiebreak) {
        LOG.info("Point was hit. Tiebreakpoint recalculated successfully!");
        pontszerzoPoint.increaseValue();
        if ((pontszerzoPoint.getValue() + menetvesztesPoint.getValue()) % 2 == 1) {
            homeServing.inverseValue();
        }
        if ((pontszerzoPoint.getValue() >= 7 && (pontszerzoPoint.getValue() - menetvesztesPoint.getValue()) >= 2)) {
            pontszerzoGame.increaseValue();
            pontszerzoPoint.setValue(0);
            menetvesztesPoint.setValue(0);
            homeServing.inverseValue();
            tiebreak.setValue(false);
        }
    }

    /**
     * A szettek eredményének frissítése, és vátás a következő szettre, egy counter segítségével (current_set),
     * ha valamlyik játékos megnyerte azt, a tennis szabályai szerint.
     *
     * @param homeGame   : a hazai játékoshoz tartozó Game objektum példánya
     * @param awayGame   : a vendég játékoshoz tartozó Game objektum példánya
     * @param homeSet    : a hazai játékoshoz tartozó Set objektum példánya
     * @param awaySet    : a vendég játékoshoz tartozó Set objektum példánya
     * @param currentSet : a jelenleg folyó Set sorszáma
     * @param tiebreak   : jelzi, ha a Set során tiebreak következne (true ha a Set tiebreakkal dől el)
     */
    public static void updateSets(final Game homeGame, final Game awayGame, final Set homeSet,
                                  final Set awaySet, final Set currentSet, final Booleans tiebreak) {

        if (Math.max(homeGame.getValue(), awayGame.getValue()) == 7
                || (Math.max(homeGame.getValue(), awayGame.getValue()) == 6 && Math.min(homeGame.getValue(), awayGame.getValue()) < 5)) {

            if (homeGame.getValue() > awayGame.getValue()) {
                homeSet.increaseValue();
            } else {
                awaySet.increaseValue();
            }

            LOG.info("Set ended!");

            homeGame.setValue(0);
            awayGame.setValue(0);
            currentSet.increaseValue();

        } else if (awayGame.getValue() == 6 && homeGame.getValue() == 6) {
            LOG.info("Tiebreak started!");
            tiebreak.setValue(true);
        }
    }

}
