package net.cplibrary.generated.collections.function;

/**
 * @author Abhishek Patel (abhishekpatelmrj@gmail.com)
 * Linkedin : ( https://www.linkedin.com/in/abhishek-p-6733b3195/ )
 */
public interface IntIntToDoubleFunction {
    double value(int first, int second);

    default IntToDoubleFunction setSecond(int second) {
        return v -> value(v, second);
    }

    default IntToDoubleFunction setFirst(int first) {
        return v -> value(first, v);
    }
}
