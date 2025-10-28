package base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Event<T> {
    //Each consumer represents a function that can be called, with T as the argument.
    //It is like using Function<T, void>, except that doesn't work.
    //this is a weak reference, because if the object is destroyed (i.e. a item with no parent) then we want the garbage collector to clean it up still.
    private final ArrayList<WeakReference<Consumer<T>>> listeners = new ArrayList<>();

    public void listen(Consumer<T> callback) {
        listeners.add(new WeakReference<>(callback));
    }

    public void send(T args) {
        listeners.removeIf(ref -> ref.get() == null);
        listeners.forEach(listener -> listener.get().accept(args));
    }
}