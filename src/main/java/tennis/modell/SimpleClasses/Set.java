package tennis.modell.SimpleClasses;

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

/**
 * A megnyert szetteket reprezentáló osztály.
 *
 * @author adamberki97
 */
public class Set {

    /**
     * A logger létrehozása a Set classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Set.class);

    /**
     * A Set osztálybéli objektumok értékét reprezentáló változó.
     */
    private int value;

    /**
     * Létrehoz egy Set objektumot. Konstruktor a példányosításhoz.
     */
    public Set() {
        //LOG.info("Set object created!");
    }

    /**
     * Az adott Set példány értékét adja vissza.
     *
     * @return : Az adott Set példány értékével tér vissza.
     */
    public final int getValue() {
        return this.value;
    }

    /**
     * Az adott Set példány értékét állítja be.
     *
     * @param newPoint : ezen értékre állítja be a Set objektum értékét
     */
    public final void setValue(final int newPoint) {
        this.value = newPoint;
    }

    /**
     * Az adott Set példány értékét növeli 1-el.
     */
    public final void increaseValue() {
        this.value += 1;
    }

}
