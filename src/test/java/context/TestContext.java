package context;

// Class declaration that groups the related example logic in one place.
public class TestContext {

    private static volatile String registeredEmail;
    private static volatile String registeredPassword;

    // Method definition that performs a specific part of the program workflow.
    public static synchronized void setCredentials(String email, String password) {
        registeredEmail = email;
        registeredPassword = password;
    }

    // Method definition that performs a specific part of the program workflow.
    public static String getEmail()    { return registeredEmail; }
    // Method definition that performs a specific part of the program workflow.
    public static String getPassword() { return registeredPassword; }
}
