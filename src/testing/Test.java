package testing;

//boilerplate code for writing tests
public class Test {
    /**
     * Tests if two things are equal
     * @param result - A
     * @param expected - B
     * @param <T> - The type of A and B that are going to be checked.
     */
    public static <T> void test(T result, T expected){
        boolean pass = result.equals(expected);
        if(pass){
            System.out.println("Test passed");
        }else{
            System.out.println("\u001B[31m" + "Test failed!" + "\u001B[0m");
        }
    }
}
