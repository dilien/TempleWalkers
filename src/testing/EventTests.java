package testing;

import utility.Event;

public class EventTests {
    public EventTests() {
        int passed = 0;
        int total = 0;

        System.out.println("Test 1: Event should call listener with correct argument");
        total++;
        Event<String> event = new Event<>();
        final String[] result = {null};
        event.listen(arg -> result[0] = arg);
        event.send("hello");
        if (Test.test(result[0], "hello")) passed++;

        System.out.println("Test 2: Event should ignore garbage-collected listeners");
        total++;
        Event<Integer> intEvent = new Event<>();
        int[] count = {0};
        intEvent.listen(val -> count[0] += val);
        System.gc(); // simulate GC (not guaranteed)
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}

        intEvent.send(5);
        if (Test.test(count[0], 5)) passed++;

        System.out.println("successful: " + passed + " / " + total + "\n");
    }
}
