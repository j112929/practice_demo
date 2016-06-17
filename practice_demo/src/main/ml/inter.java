package main.ml;
import java.util.Objects;
import java.util.function.Consumer;

public interface inter<E> {
	boolean hasNext();
	E next();
	static void deo(){
	
    }
	default int de(){
		
		return 0;
	}
	default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
	public static void main(String[] args) {
		
	}
}
