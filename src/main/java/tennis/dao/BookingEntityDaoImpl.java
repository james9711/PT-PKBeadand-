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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.print.Book;

/**
 * Az adatbázisban végzendő műveleteket deffiniáló, implementáló osztály.
 *
 * @author adamberki97
 */
public class BookingEntityDaoImpl implements BookingEntityDao {

    /**
     * A logger létrehozása a BookingEntityDaoImpl osztályba.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BookingEntityDaoImpl.class);

    /**
     * DBManager létrehozása a main, BookingEntityDaoImpl classban.
     */
    private static final DBManager DB_MANAGER = DBManager.getDbPeldany();

    /**
     * bookingEntityDaoImpl létrehozása, BookingEntityDaoImpl példányosítása.
     */
    private static final BookingEntityDaoImpl bookingEntityDaoImpl = new BookingEntityDaoImpl();

    /**
     * A BookingEntityDaoImpl osztályhoz tartozó konstruktor.
     */
    private BookingEntityDaoImpl() {
    }

    /**
     * A BookingEntityDaoImpl adott példányát adja vissza.
     *
     * @return bookingEntityDaoImpl: a pédány.
     */
    public static BookingEntityDaoImpl getBookingEntityDaoImpl() {
        return bookingEntityDaoImpl;
    }

    /**
     * A táblába való beszúrást és frissítést végző metódus.
     *
     * @param entity : a lementendő entitás.
     * @return entity : a lementett entitás.
     */
    @Override
    public BookingEntity save(final BookingEntity entity) {

        if (!DB_MANAGER.connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (entity == null) {
            LOG.info("Saved entity is null!");
            throw new IllegalArgumentException("Saved entity is null!");
        }

        DBManager.entityManager.getTransaction().begin();

        if (entity.getId() == null) {
            LOG.info("Inserting into database...");
            DBManager.entityManager.persist(entity);
        } else {
            LOG.info("Updating database...");
            DBManager.entityManager.merge(entity);
        }

        DBManager.entityManager.getTransaction().commit();

        return entity;
    }

    /**
     * A táblából való törlést végző metódus.
     *
     * @param entity : a torlendő entitás.
     */
    @Override
    public void delete(final BookingEntity entity) {
        if (!DB_MANAGER.connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (entity == null || entity.getId() == 0) {
            LOG.info("The entity you want to delete is null or has no ID");
            throw new IllegalArgumentException("The entity you want to delete is null or has no ID");
        }

            DBManager.entityManager.getTransaction().begin();
            DBManager.entityManager.remove(entity);
            DBManager.entityManager.getTransaction().commit();

    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param id : a keresés alapját adó Long típusú érték.
     * @return entity : a megtalált entitás.
     */
    @Override
    public BookingEntity findReservationByID(final Long id) {
        if (!DB_MANAGER.connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (id == null) {
            return null;
        }

        try {

            Query query = DBManager.entityManager.createNamedQuery("BookingEntity.findReservationByID");
            query.setParameter("id", id);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.error("No reservation with the ID!");
            return null;
        }
    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param resdate : a keresés alapját adó String.
     * @param court   : a keresés alapját adó Integer.
     * @return entity : a megtalált entitás.
     */
    @Override
    public BookingEntity checkIsAReservationPossible(final String resdate, final Integer court) {
        if (!DB_MANAGER.connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (resdate == null) {
            return null;
        }

        try {

            Query query = DBManager.entityManager.createNamedQuery("BookingEntity.checkIsAReservationPossible");
            query.setParameter("resdate", resdate);
            query.setParameter("court", court);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.info("Court is available at that time, resrevation is possible!");
            return null;
        }
    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param code : a keresés alapját adó String.
     * @return entity : a megtalált entitás.
     */
    @Override
    public BookingEntity checkIfValidCode(final String code) {
        if (!DB_MANAGER.connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (code == null) {
            return null;
        }

        try {

            Query query = DBManager.entityManager.createNamedQuery("BookingEntity.checkIfValidCode");
            query.setParameter("code", code);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.info("Invalid code!");
            return null;
        }
    }

}