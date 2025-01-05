
package Kaar.test;

public class TestContext {
    private static ThreadLocal<String> browser = new ThreadLocal<>();

    public static String getBrowser() {
        return browser.get();
    }

    public static void setBrowser(String browserValue) {
        browser.set(browserValue);
    }

    public static void clearBrowser() {
        browser.remove(); // VERY IMPORTANT: Prevents memory leaks
    }
}
