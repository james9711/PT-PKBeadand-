package SimpleClassTests;/*-
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
import org.junit.Test;
import tennis.modell.SimpleClasses.BookDatas;

import static org.junit.Assert.assertEquals;

public class BookDatasTest {

    BookDatas tester = new BookDatas();

    @Test
    public void getCustomersNameTester() {
        tester.setCustomersName("Example Jack");
        assertEquals("Get CustomerName Value does not work!", "Example Jack", tester.getCustomersName());
    }

    @Test
    public void setCustomersNameTester() {
        tester.setCustomersName("Example Jacky");
        assertEquals("Set CustomerName Value does not work!", "Example Jacky", tester.getCustomersName());
    }

    @Test
    public void getChoosenDateTester() {
        tester.setChoosenDate("2018.04.26");
        assertEquals("Get ChoosenDate Value does not work!", "2018.04.26", tester.getChoosenDate());
    }

    @Test
    public void setChoosenDateTester() {
        tester.setChoosenDate("2018.apr.26");
        assertEquals("Set getChoosenDate Value does not work!", "2018.apr.26", tester.getChoosenDate());
    }

    @Test
    public void getFoglalasAraTester() {
        tester.setFoglalasAra(500);
        assertEquals("Get FoglalasAra Value does not work!", 500, tester.getFoglalasAra());
    }

    @Test
    public void setFoglalasAraTester() {
        tester.setFoglalasAra(500);
        assertEquals("Set FoglalasAra Value does not work!", 500, tester.getFoglalasAra());
    }

    @Test
    public void getFoglaltPalya() {
        tester.setFoglaltPalya(2);
        assertEquals("Get FoglaltPalya Value does not work!", 2, tester.getFoglaltPalya());
    }

    @Test
    public void setFoglaltPalya() {
        tester.setFoglaltPalya(3);
        assertEquals("Set FoglaltPalya Value does not work!", 3, tester.getFoglaltPalya());
    }

    @Test
    public void getCodeForProg() {
        tester.setCodeForProg("AAA222");
        assertEquals("Get CodeForProg Value does not work!", "AAA222", tester.getCodeForProg());
    }

    @Test
    public void setCodeForProg() {
        tester.setCodeForProg("AAA000");
        assertEquals("Set CodeForProg Value does not work!", "AAA000", tester.getCodeForProg());
    }

}
