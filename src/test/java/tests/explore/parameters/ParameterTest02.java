package tests.explore.parameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class ParameterTest02 {
    @Test
    @Parameters({"systemPort", "uuid"})
    public void getParams(String systemPort, String uuid){
        try{
            Thread.sleep(2000);
        } catch (Exception ignored){

        }
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("systemPort: %s | udid: %s", systemPort, uuid);
    }
}
