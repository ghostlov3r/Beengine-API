package beengine.api.util.function;

@FunctionalInterface
public interface IntTriPredicate {
	
	boolean accept (int first, int second, int third);
}
