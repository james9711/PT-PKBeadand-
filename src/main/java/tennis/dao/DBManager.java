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
import javax.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Az JPA adatbázis kapcsolat ellenörzéséért, kezeléséért felelős osztály.
 */
public class DBManager {


    /**
     * A logger létrehozása a DBManager osztályba.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DBManager.class);

    /**
     * DB_PELDANY létrehozása, DBManager példányosítása.
     */
    private static final DBManager DB_PELDANY = new DBManager();

    /**
     * Egy EntityManager deffiniálása.
     */
    public static EntityManager entityManager;

    /**
     * A DBManager osztályhoz tartozó konstruktor.
     */
    private DBManager() {
    }

    /**
     * A DBManager adott példányát adja vissza.
     *
     * @return DB_PELDANY: a pédány.
     */
    public static DBManager getDbPeldany() {
        return DB_PELDANY;
    }

    /**
     * Az adatbázishoz való csatlakozást végző metódus.
     */
    public void connectDB() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("tDB");
        entityManager = emFactory.createEntityManager();
        LOG.info("Datebase: connection OK!");
    }

    /**
     * Az adatbázisról való lecsatlakozást végző metódus.
     */
    public void disconnectDB() {
        if (connected()) {
            entityManager.close();
            LOG.info("Datebase: disconnection OK!");
        }
        entityManager = null;
    }

    /**
     * Az adatbázishoz való csatlakozást vizsgáló metódus.
     *
     * @return igaz: ha az entity nem üres és nyitott.
     */
    public boolean connected() {
        return entityManager != null && entityManager.isOpen();
    }

}
