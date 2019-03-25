import org.testng.annotations.*;

public class TestNGTest {
    @BeforeGroups
    public void BeforeGroups() {
        System.out.println("@BeforeGroups");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("@BeforeClass");
    }
    @Test
    public void test2() {
        System.out.println("test2");
    }
//    groups = {"My group"}
    @Test(dependsOnMethods = { "test3" ,"test2"})
    public void test1() {
        System.out.println("test1");
    }

    @Test()
    public void test3() {
        System.out.println("Inside testSalutationMessage()");
        System.out.println("test3");
    }
    @AfterClass
    public void AfterClass() {
        System.out.println("@AfterClass");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("@AfterMethod");
    }
    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("@BeforeMethod");
    }
}
