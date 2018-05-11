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
 * Az adatbázisba szügséges adatok tárolását lebonyolító osztály.
 *
 * @author adamberki97
 */
public class BookDatas {

    /**
     * A logger létrehozása a BookDatas classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BookDatas.class);

    /**
     * A foglaló személy nevét tároló változó.
     */
    private String customersName;
    /**
     * A foglás időpontját tároló változó.
     */
    private String choosenDate;
    /**
     * A foglás árát tároló változó.
     */
    private int foglalasAra;

    /**
     * A foglt pályát tároló változó.
     */
    private int foglaltPalya;

    /**
     * A fogláshoz tartozó kódot tároló változó.
     */
    private String codeForProg;

    /**
     * Létrehoz egy BookDatas objektumot. Konstruktor a példányosításhoz.
     */
    public BookDatas() {
        LOG.info("BookDatas object created!");
    }

    /**
     * Az adott BookDatas példányhoz tartozó nevet adja vissza.
     *
     * @return : Az adott BookDatas példányhoz tartozó névvel tér vissza.
     */
    public final String getCustomersName() {
        return customersName;
    }

    /**
     * Az adott BookDatas példányhoz tartozó név értékét állítja be.
     *
     * @param newCustomersName : ezen értékre állítja be a BookDatas objektumhoz tartozó nevet.
     */
    public final void setCustomersName(final String newCustomersName) {
        this.customersName = newCustomersName;
    }

    /**
     * Az adott BookDatas példányhoz tartozó foglalás dátumát adja vissza.
     *
     * @return : Az adott BookDatas példányhoz tartozó foglalás dátumával tér vissza.
     */
    public final String getChoosenDate() {
        return choosenDate;
    }

    /**
     * Az adott BookDatas példányhoz tartozó dátum értékét állítja be.
     *
     * @param newChoosenDate : ezen értékre állítja be a BookDatas objektumhoz tartozó dátumot.
     */
    public final void setChoosenDate(final String newChoosenDate) {
        this.choosenDate = newChoosenDate;
    }

    /**
     * Az adott BookDatas példányhoz tartozó fizetendő összeget adja vissza.
     *
     * @return : Az adott BookDatas példányhoz tartozó fizetendő összeggel tér vissza.
     */
    public final int getFoglalasAra() {
        return foglalasAra;
    }

    /**
     * Az adott BookDatas példányhoz tartozó fizetendő összeg értékét állítja be.
     *
     * @param newFoglalasAra : ezen értékre állítja be a BookDatas objektumhoz tartozó fizetendő összeget.
     */
    public final void setFoglalasAra(final int newFoglalasAra) {
        this.foglalasAra = newFoglalasAra;
    }

    /**
     * Az adott BookDatas példányhoz tartozó foglalt pálya számát adja vissza.
     *
     * @return : Az adott BookDatas példányhoz tartozó foglalandó pálya számával tér vissza.
     */
    public final int getFoglaltPalya() {
        return foglaltPalya;
    }

    /**
     * Az adott BookDatas példányhoz tartozó foglalandó pályát állítja be.
     *
     * @param newFoglaltPalya : ezen értékre állítja be a BookDatas objektumhoz tartozó foglalandó páylát.
     */
    public final void setFoglaltPalya(final int newFoglaltPalya) {
        this.foglaltPalya = newFoglaltPalya;
    }

    /**
     * Az adott BookDatas példányhoz generált kódot adja vissza.
     *
     * @return : Az adott BookDatas példányhoz tartozó kód értékével tér vissza.
     */
    public final String getCodeForProg() {
        return codeForProg;
    }

    /**
     * Az adott BookDatas példányhoz tartozó kód értékét állítja be.
     *
     * @param newCodeForProg : ezen értékre állítja be a BookDatas objektumhoz tartozó kódot.
     */
    public final void setCodeForProg(final String newCodeForProg) {
        this.codeForProg = newCodeForProg;
    }

}

