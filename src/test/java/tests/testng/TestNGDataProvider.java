package tests.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProvider {

    @Test(dataProvider = "chi")
    public void test(String value){
        System.out.println(value);
    }

    @DataProvider(name = "chi")
    public String[] testData(){
        return new String[]{"value1", "value2"};
    }
}
