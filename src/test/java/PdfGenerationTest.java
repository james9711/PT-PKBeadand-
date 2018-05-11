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
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import tennis.modell.PdfGeneration;

import static junit.framework.TestCase.assertTrue;

public class PdfGenerationTest {

    private PdfGeneration testCreator = new PdfGeneration();

    String testName;
    String testDate;
    int testAr;
    int testPalyaszam;
    File file;

    @Before
    public void setUsedParas(){
        testName = "Example Jack";
        testDate = "2018.apr.26";
        testAr = 500;
        testPalyaszam = 1;
        file = null;
    }

    @Test
    public void pdfGenerationTester() {
        try {
            file = File.createTempFile("some-prefix", "some-ext");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            testCreator.pdfGeneration(testName, testDate, testPalyaszam, testAr, file);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert file != null;
        assertTrue("PdfGeneration can not write on files!",(file.length() != 0));
        file.deleteOnExit();
    }

}
