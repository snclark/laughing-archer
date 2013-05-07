/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.io.File;
import mobireader.Header;
import mobireader.Mobi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author att
 */
public class HeaderJUnitTest {
    
    public HeaderJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCaluculationOfHeaderSize() {
        Mobi mobi = new Mobi("test");
        String header_format = "32shhIIIIII4s4sIIH";
        int correct_size = 78;
        int calculated_size = mobi.calcsize(header_format);
        assertEquals("Calculated size is incorrect", correct_size,
                    calculated_size);
    }
    
    @Test
    public void testCreatingHeaderByPreon() {
        Mobi mobi = new Mobi("test");
        String path = "/home/att/studia/semestr4/Java/resources/test.mobi";
        File file = new File(path);
        Header header = mobi.createHeaderBasedOn(file) ;
        System.out.println(header.name);
        assertTrue(header.name.length() != 0);
        
    }
}