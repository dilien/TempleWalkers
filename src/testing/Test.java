package testing;

public class Test {
    public static <T> boolean test(T result, T expected){
        boolean pass = result == expected;
        testLog(pass);
        return pass;
    }
    public static void testLog(boolean pass){
        if(pass){
            System.out.println("Test passed");
        }else{
            System.out.println("\t\\u001B[31mTest failed!");
        }
    }
}
