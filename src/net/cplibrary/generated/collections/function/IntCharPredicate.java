package net.cplibrary.generated.collections.function;

/**
 * @author Abhishek Patel (abhishekpatelmrj@gmail.com)
 * Linkedin : ( https://www.linkedin.com/in/abhishek-p-6733b3195/ )
 */
public interface IntCharPredicate {
    boolean value(int first, char second);

    default IntFilter setSecond(char second) {
        return v -> value(v, second);
    }

    default CharFilter setFirst(int first) {
        return v -> value(first, v);
    }
}
