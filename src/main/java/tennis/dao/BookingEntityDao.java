package tennis.dao;

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

/**
 * Az adatbázis műveleteket deffiniáló interface.
 *
 * @author adamberki97
 */
public interface BookingEntityDao {


    /**
     * A táblába való beszúrást és frissítést deffiniáló metódus lesz.
     *
     * @param entity : a lementendő entitás.
     * @return entity : a lementett entitás.
     */
    BookingEntity save(BookingEntity entity);

    /**
     * A táblából való törlést deffiniáló metódus lesz.
     *
     * @param entity : a torlendő entitás.
     */
    void delete(BookingEntity entity);

    /**
     * Az adatbázisban ID alapján való keresést deffiniáló metódus lesz.
     *
     * @param id : a keresés alapját adó Long típusú érték.
     * @return entity : a megtalált entitás.
     */
    BookingEntity findReservationByID(Long id);

    /**
     * Az adatbázisban resdate illetve court értéke alapján való keresést deffiniáló metódus lesz.
     *
     * @param resdate : a keresés alapját adó String típusú érték.
     * @param court : a keresés alapját adó Integer típusú érték.
     * @return entity : a megtalált entitás.
     */
    BookingEntity checkIsAReservationPossible(String resdate, Integer court);

    /**
     * Az adatbázisban code alapján való keresést deffiniáló metódus lesz.
     *
     * @param code : a keresés alapját adó String típusú érték.
     * @return entity : a megtalált entitás.
     */
    BookingEntity checkIfValidCode(String code);

}
