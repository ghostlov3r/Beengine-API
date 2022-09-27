package beengine.api.util.function;

public interface QuadConsumer<F, S, T, Q> {
	
	void accept (F f, S s, T t, Q q);
	
}
