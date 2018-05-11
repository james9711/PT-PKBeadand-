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
import org.junit.Before;
import org.junit.Test;
import tennis.modell.*;
import tennis.modell.SimpleClasses.Booleans;
import tennis.modell.SimpleClasses.Game;
import tennis.modell.SimpleClasses.Point;
import tennis.modell.SimpleClasses.Set;

import static org.junit.Assert.assertEquals;

public class GameCalculatiorTest {

    Point pontszerzoPointTester;
    Point nemPontszerzoPointTester;
    Point pontszerzoAllPointTester;
    Game pontszerzoGameTester;
    Game nemPontszerzoGameTester;
    Set pontszerzoSetTester;
    Set nemPontszerzoSetTester;
    Set currSetTester;
    Booleans isTiebreakTester;
    Booleans isHomeServing;

    @Before
    public void createObjects() {
        pontszerzoPointTester = new Point();
        nemPontszerzoPointTester = new Point();
        pontszerzoAllPointTester = new Point();

        pontszerzoGameTester = new Game();
        nemPontszerzoGameTester = new Game();

        pontszerzoSetTester = new Set();
        nemPontszerzoSetTester = new Set();
        currSetTester = new Set();

        isTiebreakTester = new Booleans();
        isHomeServing = new Booleans();
    }


    @Test
    public void updatePoint() {
        pontszerzoPointTester.setValue(0);
        pontszerzoAllPointTester.setValue(0);
        pontszerzoGameTester.setValue(0);
        nemPontszerzoPointTester.setValue(0);
        isHomeServing.setValue(false);
        isTiebreakTester.setValue(false);
        GameCalculator.updatePoint(pontszerzoPointTester, pontszerzoAllPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updatePoint does not work properly!", 15, pontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 1, pontszerzoAllPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(0);
        pontszerzoAllPointTester.setValue(0);
        nemPontszerzoPointTester.setValue(0);
        isHomeServing.setValue(false);
        isTiebreakTester.setValue(true);
        GameCalculator.updatePoint(pontszerzoPointTester, pontszerzoAllPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updatePoint does not work properly!", 1, pontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 1, pontszerzoAllPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(40);
        pontszerzoAllPointTester.setValue(10);
        pontszerzoGameTester.setValue(2);
        nemPontszerzoPointTester.setValue(30);
        isHomeServing.setValue(false);
        isTiebreakTester.setValue(false);
        GameCalculator.updatePoint(pontszerzoPointTester, pontszerzoAllPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updatePoint does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 11, pontszerzoAllPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 3, pontszerzoGameTester.getValue());
        assertEquals("updatePoint does not work properly!", true, isHomeServing.getValue());

        pontszerzoPointTester.setValue(6);
        pontszerzoAllPointTester.setValue(5);
        nemPontszerzoPointTester.setValue(4);
        isHomeServing.setValue(false);
        isTiebreakTester.setValue(true);
        GameCalculator.updatePoint(pontszerzoPointTester, pontszerzoAllPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updatePoint does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 6, pontszerzoAllPointTester.getValue());
        assertEquals("updatePoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updatePoint does not work properly!", false, isTiebreakTester.getValue());

    }

    @Test
    public void recalculatePointsTester() {
        pontszerzoPointTester.setValue(0);
        nemPontszerzoPointTester.setValue(0);
        pontszerzoGameTester.setValue(0);
        isHomeServing.setValue(true);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 15, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 0, nemPontszerzoPointTester.getValue());

        pontszerzoPointTester.setValue(15);
        nemPontszerzoPointTester.setValue(15);
        pontszerzoGameTester.setValue(1);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 30, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 15, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 1, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(30);
        nemPontszerzoPointTester.setValue(40);
        pontszerzoGameTester.setValue(2);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 40, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 40, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 2, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(40);
        nemPontszerzoPointTester.setValue(15);
        pontszerzoGameTester.setValue(0);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 1, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(40);
        nemPontszerzoPointTester.setValue(40);
        pontszerzoGameTester.setValue(2);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 50, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 40, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 2, pontszerzoGameTester.getValue());


        pontszerzoPointTester.setValue(40);
        nemPontszerzoPointTester.setValue(50);
        pontszerzoGameTester.setValue(2);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 40, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 40, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 2, pontszerzoGameTester.getValue());

        pontszerzoPointTester.setValue(50);
        nemPontszerzoPointTester.setValue(40);
        pontszerzoGameTester.setValue(0);
        GameCalculator.recalculatePoints(pontszerzoPointTester, nemPontszerzoPointTester, pontszerzoGameTester, isHomeServing);
        assertEquals("recalculatePoints does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("recalculatePoints does not work properly!", 1, pontszerzoGameTester.getValue());

    }

    @Test
    public void recalculateTiebreakPointsTester() {

        pontszerzoPointTester.setValue(1);
        pontszerzoGameTester.setValue(0);
        nemPontszerzoPointTester.setValue(1);
        isHomeServing.setValue(true);
        isTiebreakTester.setValue(true);
        GameCalculator.recalculateTiebreakPoints(pontszerzoPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 2, pontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, nemPontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isHomeServing.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", true, isTiebreakTester.getValue());

        pontszerzoPointTester.setValue(6);
        pontszerzoGameTester.setValue(1);
        nemPontszerzoPointTester.setValue(6);
        isHomeServing.setValue(true);
        isTiebreakTester.setValue(true);
        GameCalculator.recalculateTiebreakPoints(pontszerzoPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 7, pontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 6, nemPontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, pontszerzoGameTester.getValue());

        //to 7-6
        GameCalculator.recalculateTiebreakPoints(pontszerzoPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isTiebreakTester.getValue());

        pontszerzoPointTester.setValue(6);
        pontszerzoGameTester.setValue(2);
        nemPontszerzoPointTester.setValue(2);
        isHomeServing.setValue(true);
        isTiebreakTester.setValue(true);
        GameCalculator.recalculateTiebreakPoints(pontszerzoPointTester, pontszerzoGameTester, nemPontszerzoPointTester, isHomeServing, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoPointTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 3, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isTiebreakTester.getValue());

    }

    @Test
    public void updateSetsTester() {

        pontszerzoGameTester.setValue(0);
        nemPontszerzoGameTester.setValue(0);
        pontszerzoSetTester.setValue(0);
        nemPontszerzoSetTester.setValue(0);
        currSetTester.setValue(0);
        isTiebreakTester.setValue(false);
        GameCalculator.updateSets(pontszerzoGameTester, nemPontszerzoGameTester, pontszerzoSetTester, nemPontszerzoSetTester, currSetTester, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, currSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isTiebreakTester.getValue());

        pontszerzoGameTester.setValue(6);
        nemPontszerzoGameTester.setValue(2);
        pontszerzoSetTester.setValue(1);
        nemPontszerzoSetTester.setValue(0);
        currSetTester.setValue(1);
        isTiebreakTester.setValue(false);
        GameCalculator.updateSets(pontszerzoGameTester, nemPontszerzoGameTester, pontszerzoSetTester, nemPontszerzoSetTester, currSetTester, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, pontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, currSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isTiebreakTester.getValue());

        pontszerzoGameTester.setValue(6);
        nemPontszerzoGameTester.setValue(7);
        pontszerzoSetTester.setValue(1);
        nemPontszerzoSetTester.setValue(0);
        currSetTester.setValue(1);
        isTiebreakTester.setValue(false);
        GameCalculator.updateSets(pontszerzoGameTester, nemPontszerzoGameTester, pontszerzoSetTester, nemPontszerzoSetTester, currSetTester, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 0, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 0, nemPontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, pontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, nemPontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, currSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", false, isTiebreakTester.getValue());

        pontszerzoGameTester.setValue(6);
        nemPontszerzoGameTester.setValue(6);
        pontszerzoSetTester.setValue(1);
        nemPontszerzoSetTester.setValue(1);
        currSetTester.setValue(2);
        isTiebreakTester.setValue(false);
        GameCalculator.updateSets(pontszerzoGameTester, nemPontszerzoGameTester, pontszerzoSetTester, nemPontszerzoSetTester, currSetTester, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 6, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 6, nemPontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, pontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, nemPontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, currSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", true, isTiebreakTester.getValue());

        pontszerzoGameTester.setValue(6);
        nemPontszerzoGameTester.setValue(6);
        pontszerzoSetTester.setValue(1);
        nemPontszerzoSetTester.setValue(1);
        currSetTester.setValue(2);
        isTiebreakTester.setValue(true);
        GameCalculator.updateSets(pontszerzoGameTester, nemPontszerzoGameTester, pontszerzoSetTester, nemPontszerzoSetTester, currSetTester, isTiebreakTester);
        assertEquals("updateTiebreakPoint does not work properly!", 6, pontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 6, nemPontszerzoGameTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, pontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 1, nemPontszerzoSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", 2, currSetTester.getValue());
        assertEquals("updateTiebreakPoint does not work properly!", true, isTiebreakTester.getValue());

    }

}
