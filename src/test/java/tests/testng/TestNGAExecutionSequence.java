package tests.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGAExecutionSequence {
    @Test(dependsOnMethods = "testA")
    public void testB(){
        System.out.println("TestB");
    }

    public void testA(){
        System.out.println("TestA");
        Assert.fail("I want it that way!");
    }

}
