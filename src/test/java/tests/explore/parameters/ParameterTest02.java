package tests.explore.parameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class ParameterTest02 {
    @Test
    @Parameters({"uuid", "systemPort"})
    public void getParams(String teo, String ti){
        try{
            Thread.sleep(2000);
        } catch (Exception ignored){

        }
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("systemPort: %s | udid: %s", teo, ti);
    }
}
