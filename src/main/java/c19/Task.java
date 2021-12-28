package c19;

@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN input);
}
