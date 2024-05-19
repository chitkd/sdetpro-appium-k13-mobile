package tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGAssertion {
    @Test
    public void hardAssertion(){
        String expectedString = "Something";
        String actualString = "Something Else";

        Assert.assertEquals(actualString, expectedString, "[ERR] The msg is wrong!!");

        // Do something else -> never reach to this line because the above assertion was HARD failed assertion!!!
        Assert.fail("I want it that way");
    }
}
