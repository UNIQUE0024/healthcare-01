package com.healthcare;

import com.healthcare.servlet.PatientServlet;
import org.junit.Test;
import static org.junit.Assert.*;

public class PatientServletTest {
    
    @Test
    public void testServletNotNull() {
        PatientServlet servlet = new PatientServlet();
        assertNotNull("Servlet should not be null", servlet);
    }
    
    @Test
    public void testServletInstance() {
        PatientServlet servlet = new PatientServlet();
        assertTrue("Should be instance of HttpServlet", 
                   servlet instanceof javax.servlet.http.HttpServlet);
    }
    
    @Test
    public void testServletInitialization() {
        PatientServlet servlet = new PatientServlet();
        assertNotNull("Servlet object should be created", servlet);
    }
}
