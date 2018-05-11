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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A pályák foglalásához tartozó adatokat tartalmazó tábla: RESERVATION.
 *
 * @author adamberki97
 */
@Entity
@Table(name = "RESERVATION", schema = "MY_OWN_SCHEMA")

@NamedQueries({
        @NamedQuery(name = "BookingEntity.findReservationByID", query = "SELECT e FROM BookingEntity e where e.id = :id"),
        @NamedQuery(name = "BookingEntity.checkIsAReservationPossible", query = "SELECT e from BookingEntity e where e.resdate = :resdate and e.court = :court"),
        @NamedQuery(name = "BookingEntity.checkIfValidCode", query = "SELECT e from BookingEntity e where e.code = :code"),
})

/**
 * A BookingEntity egy JPA entityt reprezentáló osztály. Táblájának szerkezete, oszlopainak megadása.
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class BookingEntity implements Serializable {

    /**
     * Az automatikusan generált ID-ket tároló oszlop létrehozása a táblában.
     */
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, updatable = false)
    private Long id;

    /**
     * A foglalás időtartalmát tároló oszlop létrehozása a táblában.
     */
    @Column(name = "RESDATE")
    private String resdate;

    /**
     * A lefoglalt pálya számát tároló oszlop létrehozása a táblában.
     */
    @Column(name = "COURT")
    private Integer court;

    /**
     * A foglaló nevét tároló oszlop létrehozása a táblában.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * A foglalás árát tároló oszlop létrehozása a táblában.
     */
    @Column(name = "PRICE")
    private Integer price;

    /**
     * A foglaláshoz tartozó belépési kódot tároló oszlop létrehozása a táblában.
     */
    @Column(name = "CODE")
    private String code;

}
