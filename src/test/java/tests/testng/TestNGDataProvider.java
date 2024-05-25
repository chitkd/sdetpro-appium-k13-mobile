package tests.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProvider {

    @Test(dataProvider = "t")
    public void test(){

    }

    @DataProvider(name="t")
    public String[] testData(){
        return new String[]{"value1", "value2"};
    }
}
