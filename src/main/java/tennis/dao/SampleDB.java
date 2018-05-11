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
import tennis.Tennis;

/**
 * Az adatbázissal való kapcsolattartást, műveleteket deffiniáló osztály.
 *
 * @author adamberki97
 */
public final class SampleDB {

    /**
     * A logger létrehozása a SampleDB osztályba.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SampleDB.class);

    /**
     * DB_PELDANY létrehozása, SampleDB példányosítása.
     */
    private static final SampleDB DB_PELDANY = new SampleDB();

    /**
     * Egy EntityManager deffiniálása.
     */
    private EntityManager em;

    /**
     * A SampleDB osztályhoz tartozó konstruktor.
     */
    private SampleDB() {
    }

    /**
     * A SampleDB adott példányát adja vissza.
     *
     * @return DB_PELDANY: a pédány.
     */
    public static SampleDB getDbPeldany() {
        return DB_PELDANY;
    }

    /**
     * Az adatbázishoz való csatlakozást végző metódus.
     */
    public void connectDB() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tDB");
        em = emFactory.createEntityManager();
        LOG.info("Datebase: connection OK!");
    }

    /**
     * Az adatbázisról való lecsatlakozást végző metódus.
     */
    public void disconnectDB() {
        if (connected()) {
            em.close();
            LOG.info("Datebase: disconnection OK!");
        }
        em = null;
    }

    /**
     * Az adatbázishoz való csatlakozást vizsgáló metódus.
     *
     * @return igaz: ha az entity nem üres és nyitott.
     */
    public boolean connected() {
        return em != null && em.isOpen();
    }

    /**
     * A táblába való beszúrást és frissítést végző metódus.
     *
     * @param entity : a lementendő entitás.
     * @return entity : a lementendő entitás.
     * @throws Exception ha kivétel váltódik ki.
     */
    public BookingEntity save(final BookingEntity entity) throws Exception {

        if (!connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (entity == null) {
            LOG.info("Saved entity is null!");
            throw new IllegalArgumentException("Saved entity is null!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                LOG.info("Inserting into database...");
                em.persist(entity);
            } else {
                LOG.info("Updating database...");
                em.merge(entity);
            }

            em.getTransaction().commit();

            return entity;

        } catch (PersistenceException e) {
            LOG.error("JPA queries error!");
            throw new Exception("JPA error!", e);
        }
    }

    /**
     * A táblából való törlést végző metódus.
     *
     * @param entity : a torlendő entitás.
     * @throws Exception ha kivétel váltódik ki.
     */
    public void delete(final BookingEntity entity) throws Exception {
        if (!connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (entity == null || entity.getId() == 0) {
            LOG.info("The entity you want to delete is null or has no ID");
            throw new IllegalArgumentException("The entity you want to delete is null or has no ID");
        }

        try {
            BookingEntity delEntity = em.find(BookingEntity.class, entity.getId());

            if (delEntity.getId() == null) {
                LOG.error("No reservation with the ID!");
                throw new IllegalArgumentException("No reservation with the ID!");
            }

            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            LOG.error("JPA queries error!");
            throw new Exception("JPA error", e);
        }
    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param id : a keresés alapját adó Long típusú érték.
     * @return entity : a megtalált entitás.
     * @throws Exception ha kivétel váltódik ki.
     */
    public BookingEntity findReservationByID(final Long id) throws Exception {
        if (!connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (id == null) {
            return null;
        }

        try {

            Query query = em.createNamedQuery("BookingEntity.findReservationByID");
            query.setParameter("id", id);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.error("No reservation with the ID!");
            return null;
        } catch (PersistenceException e) {
            LOG.error("JPA queries error!");
            throw new Exception("JPA error!", e);
        }
    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param resdate : a keresés alapját adó String.
     * @param court   : a keresés alapját adó Integer.
     * @return entity : a megtalált entitás.
     * @throws Exception ha kivétel váltódik ki.
     */
    public BookingEntity checkIsAReservationPossible(final String resdate, final Integer court) throws Exception {
        if (!connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (resdate == null) {
            return null;
        }

        try {

            Query query = em.createNamedQuery("BookingEntity.checkIsAReservationPossible");
            query.setParameter("resdate", resdate);
            query.setParameter("court", court);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.info("Court is available at that time, resrevation is possible!");
            return null;
        } catch (PersistenceException e) {
            LOG.error("JPA queries error!");
            throw new Exception("JPA error!", e);
        }
    }

    /**
     * Az egyes sorokkal visszatéró metódus, azonosítás/ keresés az egyedi ID alapján.
     *
     * @param code : a keresés alapját adó String.
     * @return entity : a megtalált entitás.
     * @throws Exception ha kivétel váltódik ki.
     */
    public BookingEntity checkIfValidCode(final String code) throws Exception {
        if (!connected()) {
            LOG.info("No database connection!");
            throw new IllegalStateException("No database connection!");
        }

        if (code == null) {
            return null;
        }

        try {

            Query query = em.createNamedQuery("BookingEntity.checkIfValidCode");
            query.setParameter("code", code);
            BookingEntity entity = (BookingEntity) query.getSingleResult();
            LOG.info("Queries made!");
            return entity;

        } catch (NoResultException e) {
            LOG.info("Invalid code!");
            return null;
        } catch (PersistenceException e) {
            LOG.error("JPA queries error!");
            throw new Exception("JPA error!", e);
        }
    }

}