package net.cplibrary.misc;

import java.util.Random;

/**
 * @author Ashutosh Patel (ashutoshpatelnoida@gmail.com)
 * Linkedin : ( https://www.linkedin.com/in/ashutosh-patel-7954651ab/ )
 */
public class ExtendedRandom extends Random {
    public ExtendedRandom(long seed) {
        super(seed);
    }

    public int nextInt(int from, int to) {
        return nextInt(to - from) + from;
    }

    public int[] nextIntArray(int size, int from, int to) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = nextInt(from, to);
        }
        return result;
    }

    public char[] nextCharArray(int size, char... letters) {
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = letters[nextInt(letters.length)];
        }
        return result;
    }
}
