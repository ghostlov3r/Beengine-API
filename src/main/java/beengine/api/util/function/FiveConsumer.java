package beengine.api.util.function;

public interface FiveConsumer<F, S, T, Q, FV> {
	
	void accept (F f, S s, T t, Q q, FV fv);
	
}
