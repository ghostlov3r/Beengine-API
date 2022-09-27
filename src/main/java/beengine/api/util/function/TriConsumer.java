package beengine.api.util.function;

@FunctionalInterface
public interface TriConsumer<F, S, T> {
	void accept (F first, S second, T third);
}
