package tennis;

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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tennis.dao.BookingEntityDaoImpl;
import tennis.dao.DBManager;

/**
 * A Main osztályunkat tartalmazó class.
 *
 * @author adamberki97
 */
public class Tennis extends Application {

    /**
     * DBManager létrehozása a main, Tennis classban.
     */
    private static final DBManager DB_MANAGER = DBManager.getDbPeldany();

    /**
     * A logger létrehozása a Tennis classra.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Tennis.class);

    @Override
    public final void start(final Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));

        Scene scene = new Scene(root);
        LOG.info("Scene created!");

        int windowHeight = 500;
        int windowLength = 920;

        stage.setScene(scene);
        stage.setTitle("Tennis App 2K!");
        stage.setResizable(true);
        stage.setHeight(windowHeight);
        stage.setWidth(windowLength);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * A Main függvény. Indítja az argumentumok futását, a programot.
     *
     * @param args : indítandó argumentumok.
     */
    public static void main(final String[] args) {

        try {
            DB_MANAGER.connectDB();
            LOG.info("Database connection created!");
            launch(args);
        } catch (Exception e) {
            LOG.error("No database connection!", e);
        } finally {
            DB_MANAGER.disconnectDB();
        }

    }

}
