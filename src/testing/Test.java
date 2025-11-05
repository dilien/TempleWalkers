package testing;

//boilerplate code for writing tests
public class Test {
    //check if two things are equal and print the result
    public static <T> boolean test(T result, T expected){
        boolean pass = result == expected;
        testLog(pass);
        return pass;
    }
    //print pass/fail
    public static void testLog(boolean pass){
        if(pass){
            System.out.println("Test passed");
        }else{
            System.out.println("\t\\u001B[31mTest failed!");
        }
    }
}
