import junit.framework.TestCase;

public class UserListApplicationTest extends TestCase {

    public static void main(String[] args) {
        var test = new UserListApplicationTest();
        test.deleteTest1();
    }
    private void deleteTest1(){
        var testProgramme = new UserListApplication();
        testProgramme.login();
        testProgramme.deleteUser();
        assertEquals(testProgramme.getUserList().size());
    }

}