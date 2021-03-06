package net.cplibrary.generated.collections;

import net.cplibrary.generated.collections.comparator.CharComparator;
import net.cplibrary.generated.collections.function.*;
import net.cplibrary.generated.collections.iterator.CharIterator;
import net.cplibrary.generated.collections.iterator.DoubleIterator;
import net.cplibrary.generated.collections.iterator.IntIterator;
import net.cplibrary.generated.collections.iterator.LongIterator;
import net.cplibrary.generated.collections.list.CharArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface CharStream extends Iterable<Character>, Comparable<CharStream> {
    //abstract
    CharIterator charIterator();

    //base
    default Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private final CharIterator it = charIterator();

            public boolean hasNext() {
                return it.isValid();
            }

            public Character next() {
                char result = it.value();
                it.advance();
                return result;
            }
        };
    }

    default char first() {
        return charIterator().value();
    }

    default CharCollection compute() {
        return new CharArrayList(this);
    }

    default int compareTo(CharStream c) {
        CharIterator it = charIterator();
        CharIterator jt = c.charIterator();
        while (it.isValid() && jt.isValid()) {
            char i = it.value();
            char j = jt.value();
            if (i < j) {
                return -1;
            } else if (i > j) {
                return 1;
            }
            it.advance();
            jt.advance();
        }
        if (it.isValid()) {
            return 1;
        }
        if (jt.isValid()) {
            return -1;
        }
        return 0;
    }

    //algorithms
    default void forEach(CharTask task) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            task.process(it.value());
        }
    }

    default boolean contains(char value) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (it.value() == value) {
                return true;
            }
        }
        return false;
    }

    default boolean contains(CharFilter filter) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (filter.accept(it.value())) {
                return true;
            }
        }
        return false;
    }

    default int count(char value) {
        int result = 0;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (it.value() == value) {
                result++;
            }
        }
        return result;
    }

    default int count(CharFilter filter) {
        int result = 0;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (filter.accept(it.value())) {
                result++;
            }
        }
        return result;
    }

    default char min() {
        char result = Character.MAX_VALUE;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            char current = it.value();
            if (current < result) {
                result = current;
            }
        }
        return result;
    }

    default char min(CharComparator comparator) {
        char result = Character.MAX_VALUE;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            char current = it.value();
            if (result == Character.MAX_VALUE || comparator.compare(result, current) > 0) {
                result = current;
            }
        }
        return result;
    }

    default char max() {
        char result = Character.MIN_VALUE;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            char current = it.value();
            if (current > result) {
                result = current;
            }
        }
        return result;
    }

    default char max(CharComparator comparator) {
        char result = Character.MAX_VALUE;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            char current = it.value();
            if (result == Character.MAX_VALUE || comparator.compare(result, current) < 0) {
                result = current;
            }
        }
        return result;
    }

    default int sum() {
        int result = 0;
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            result += it.value();
        }
        return result;
    }

    default int[] qty(int bound) {
        int[] result = new int[bound];
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            result[it.value()]++;
        }
        return result;
    }

    default int[] qty() {
        return qty(max() + 1);
    }

    default boolean allOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (!f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default boolean anyOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return true;
            }
        }
        return false;
    }

    default boolean noneOf(CharFilter f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            if (f.accept(it.value())) {
                return false;
            }
        }
        return true;
    }

    default char reduce(CharCharToCharFunction f) {
        CharIterator it = charIterator();
        if (!it.isValid()) {
            return Character.MAX_VALUE;
        }
        char result = it.value();
        while (it.advance()) {
            result = f.value(result, it.value());
        }
        return result;
    }

    default double reduce(double initial, DoubleCharToDoubleFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default int reduce(int initial, IntCharToIntFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default long reduce(long initial, LongCharToLongFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    default char reduce(char initial, CharCharToCharFunction f) {
        for (CharIterator it = charIterator(); it.isValid(); it.advance()) {
            initial = f.value(initial, it.value());
        }
        return initial;
    }

    //views
    default CharStream union(CharStream other) {
        return () -> new CharIterator() {
            private final CharIterator first = CharStream.this.charIterator();
            private CharIterator second;

            public char value() throws NoSuchElementException {
                if (first.isValid()) {
                    return first.value();
                }
                return second.value();
            }

            public boolean advance() throws NoSuchElementException {
                if (first.isValid()) {
                    first.advance();
                    if (!first.isValid()) {
                        second = other.charIterator();
                    }
                    return second.isValid();
                } else {
                    return second.advance();
                }
            }

            public boolean isValid() {
                return first.isValid() || second.isValid();
            }

            public void remove() {
                if (first.isValid()) {
                    first.remove();
                } else {
                    second.remove();
                }
            }
        };
    }

    default CharStream filter(CharFilter f) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();

            {
                next();
            }

            private void next() {
                while (it.isValid() && !f.accept(it.value())) {
                    it.advance();
                }
            }

            public char value() {
                return it.value();
            }

            public boolean advance() {
                it.advance();
                next();
                return isValid();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    default DoubleStream map(CharToDoubleFunction function) {
        return () -> new DoubleIterator() {
            private final CharIterator it = CharStream.this.charIterator();

            public double value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    default IntStream map(CharToIntFunction function) {
        return () -> new IntIterator() {
            private final CharIterator it = CharStream.this.charIterator();

            public int value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    default LongStream map(CharToLongFunction function) {
        return () -> new LongIterator() {
            private final CharIterator it = CharStream.this.charIterator();

            public long value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    default CharStream map(CharToCharFunction function) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();

            public char value() {
                return function.value(it.value());
            }

            public boolean advance() {
                return it.advance();
            }

            public boolean isValid() {
                return it.isValid();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    default DoubleStream join(DoubleStream other, CharDoubleToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final DoubleIterator jt = other.doubleIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default IntStream join(DoubleStream other, CharDoubleToIntFunction f) {
        return () -> new IntIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final DoubleIterator jt = other.doubleIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default LongStream join(DoubleStream other, CharDoubleToLongFunction f) {
        return () -> new LongIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final DoubleIterator jt = other.doubleIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default CharStream join(DoubleStream other, CharDoubleToCharFunction f) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final DoubleIterator jt = other.doubleIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default DoubleStream join(IntStream other, CharIntToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final IntIterator jt = other.intIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default IntStream join(IntStream other, CharIntToIntFunction f) {
        return () -> new IntIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final IntIterator jt = other.intIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default LongStream join(IntStream other, CharIntToLongFunction f) {
        return () -> new LongIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final IntIterator jt = other.intIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default CharStream join(IntStream other, CharIntToCharFunction f) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final IntIterator jt = other.intIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default DoubleStream join(LongStream other, CharLongToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final LongIterator jt = other.longIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default IntStream join(LongStream other, CharLongToIntFunction f) {
        return () -> new IntIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final LongIterator jt = other.longIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default LongStream join(LongStream other, CharLongToLongFunction f) {
        return () -> new LongIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final LongIterator jt = other.longIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default CharStream join(LongStream other, CharLongToCharFunction f) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final LongIterator jt = other.longIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default DoubleStream join(CharStream other, CharCharToDoubleFunction f) {
        return () -> new DoubleIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final CharIterator jt = other.charIterator();

            public double value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default IntStream join(CharStream other, CharCharToIntFunction f) {
        return () -> new IntIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final CharIterator jt = other.charIterator();

            public int value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default LongStream join(CharStream other, CharCharToLongFunction f) {
        return () -> new LongIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final CharIterator jt = other.charIterator();

            public long value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }

    default CharStream join(CharStream other, CharCharToCharFunction f) {
        return () -> new CharIterator() {
            private final CharIterator it = CharStream.this.charIterator();
            private final CharIterator jt = other.charIterator();

            public char value() {
                return f.value(it.value(), jt.value());
            }

            public boolean advance() {
                return it.advance() && jt.advance();
            }

            public boolean isValid() {
                return it.isValid() && jt.isValid();
            }

            public void remove() {
                it.remove();
                jt.remove();
            }
        };
    }
}