package tests.explore.parameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class ParameterTest01 {
    @Test
    @Parameters({"systemPort", "uuid"})
    public void getParams(String systemPort, String uuid){
        System.out.println(new GregorianCalendar().getTime());
        System.out.printf("systemPort: %s | udid: %s\n", systemPort, uuid);
        try{
            Thread.sleep(1000);
        } catch (Exception ignored){

        }

    }
}
