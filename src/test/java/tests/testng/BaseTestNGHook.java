package tests.testng;

import org.testng.annotations.*;

public class BaseTestNGHook {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("<!--BeforeSuite Hook-->");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("<!--AfterSuite Hook-->");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("<!--BeforeTest Hook-->");
    }

    @AfterTest
    public void afterSTest(){
        System.out.println("<!--AfterTest Hook-->");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("<!--BeforeClass Hook-->");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("<!--AfterClass Hook-->");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("<!--BeforeMethod Hook-->");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("<!--AfterMethod Hook-->");
    }
}
