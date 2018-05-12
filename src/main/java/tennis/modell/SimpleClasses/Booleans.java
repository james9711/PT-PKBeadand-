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
 * A szükséges boolean értékeket reprezentáló osztály.
 *
 * @author adamberki97
 */
public class Booleans {

    /**
     * A logger létrehozása a Booleans classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Booleans.class);

    /**
     * A Booleans osztálybéli objektumok értékét reprezentáló változó.
     */
    private boolean value;

    /**
     * Létrehoz egy Booleans objektumot. Konstruktor a példányosításhoz.
     */
    public Booleans() {
        //LOG.info("Booleans object created!");
    }

    /**
     * Az adott Booleans példány értékét adja vissza.
     *
     * @return : Az adott Game példány értékével tér vissza.
     */
    public final boolean getValue() {
        return this.value;
    }

    /**
     * Az adott Booleans példány értékét állítja be.
     *
     * @param newValue : ezen értékre állítja be a Booleans objektum értékét
     */
    public final void setValue(final boolean newValue) {
        this.value = newValue;
    }

    /**
     * Az adott Booleans példány értékét növeli 1-el.
     */
    public final void inverseValue() {
        this.value = !this.value;
    }

}
