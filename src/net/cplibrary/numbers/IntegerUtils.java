package net.cplibrary.numbers;

import net.cplibrary.generated.collections.list.IntArrayList;
import net.cplibrary.generated.collections.list.IntList;
import net.cplibrary.generated.collections.list.LongArrayList;
import net.cplibrary.generated.collections.list.LongList;
import net.cplibrary.generated.collections.pair.LongIntPair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ashutosh Patel (ashutoshpatelnoida@gmail.com)
 * Linkedin : ( https://www.linkedin.com/in/ashutosh-patel-7954651ab/ )
 */
public class IntegerUtils {
    private static long _x;
    private static long _y;

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

//    public ArrayList<Integer> generatePrimes(){
//
//    }

    public static int[] generatePrimes(int upTo) {
        int[] isPrime = generateBitPrimalityTable(upTo);
        IntList primes = new IntArrayList();
        for (int i = 0; i < upTo; i++) {
            if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1) {
                primes.add(i);
            }
        }
        return primes.toArray();
    }

    public static boolean[] generatePrimalityTable(int upTo) {
        boolean[] isPrime = new boolean[upTo];
        if (upTo < 2) {
            return isPrime;
        }
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < upTo; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < upTo; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static int[] generateBitPrimalityTable(int upTo) {
        int[] isPrime = new int[(upTo + 31) >> 5];
        if (upTo < 2) {
            return isPrime;
        }
        Arrays.fill(isPrime, -1);
        isPrime[0] &= -4;
        for (int i = 2; i * i < upTo; i++) {
            if ((isPrime[i >> 5] >>> (i & 31) & 1) == 1) {
                for (int j = i * i; j < upTo; j += i) {
                    isPrime[j >> 5] &= -1 - (1 << (j & 31));
                }
            }
        }
        return isPrime;
    }

    public static int[] generateDivisorTable(int upTo) {
        int[] divisor = new int[upTo];
        for (int i = 1; i < upTo; i++) {
            divisor[i] = i;
        }
        for (int i = 2; i * i < upTo; i++) {
            if (divisor[i] == i) {
                for (int j = i * i; j < upTo; j += i) {
                    divisor[j] = i;
                }
            }
        }
        return divisor;
    }

    public static long powerInFactorial(long n, long p) {
        long result = 0;
        while (n != 0) {
            result += n /= p;
        }
        return result;
    }

    public static int sumDigits(CharSequence number) {
        int result = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            result += digitValue(number.charAt(i));
        }
        return result;
    }

    public static int digitValue(char digit) {
        if (Character.isDigit(digit)) {
            return digit - '0';
        }
        if (Character.isUpperCase(digit)) {
            return digit + 10 - 'A';
        }
        return digit + 10 - 'a';
    }

    public static int longCompare(long a, long b) {
        if (a < b) {
            return -1;
        }
        if (a > b) {
            return 1;
        }
        return 0;
    }

    public static long[][] generateBinomialCoefficients(int n) {
        long[][] result = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            result[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
            }
        }
        return result;
    }

    public static long[][] generateBinomialCoefficients(int n, long module) {
        long[][] result = new long[n + 1][n + 1];
        if (module == 1) {
            return result;
        }
        for (int i = 0; i <= n; i++) {
            result[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
                if (result[i][j] >= module) {
                    result[i][j] -= module;
                }
            }
        }
        return result;
    }

    public static long[] generateBinomialRow(int n, long module) {
        long[] result = generateReverse(n + 1, module);
        result[0] = 1;
        for (int i = 1; i <= n; i++) {
            result[i] = result[i - 1] * (n - i + 1) % module * result[i] % module;
        }
        return result;
    }

    public static int[] representationInBase(long number, int base) {
        long basePower = base;
        int exponent = 1;
        while (number >= basePower) {
            basePower *= base;
            exponent++;
        }
        int[] representation = new int[exponent];
        for (int i = 0; i < exponent; i++) {
            basePower /= base;
            representation[i] = (int) (number / basePower);
            number %= basePower;
        }
        return representation;
    }

    public static int trueDivide(int a, int b) {
        return (a - trueMod(a, b)) / b;
    }

    public static long trueDivide(long a, long b) {
        return (a - trueMod(a, b)) / b;
    }

    public static int trueMod(int a, int b) {
        a %= b;
        a += b;
        a %= b;
        return a;
    }

    public static long trueMod(long a, long b) {
        a %= b;
        a += b;
        a %= b;
        return a;
    }

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static long factorial(int n, long mod) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i % mod;
        }
        return result % mod;
    }

    public static List<LongIntPair> factorize(long number) {
        List<LongIntPair> result = new ArrayList<>();
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                int power = 0;
                do {
                    power++;
                    number /= i;
                } while (number % i == 0);
                result.add(LongIntPair.makePair(i, power));
            }
        }
        if (number != 1) {
            result.add(LongIntPair.makePair(number, 1));
        }
        return result;
    }

    public static LongList getDivisors(long number) {
        List<LongIntPair> primeDivisors = factorize(number);
        return getDivisorsImpl(primeDivisors, 0, 1, new LongArrayList());
    }

    private static LongList getDivisorsImpl(List<LongIntPair> primeDivisors, int index, long current, LongList result) {
        if (index == primeDivisors.size()) {
            result.add(current);
            return result;
        }
        long p = primeDivisors.get(index).first;
        int power = primeDivisors.get(index).second;
        for (int i = 0; i <= power; i++) {
            getDivisorsImpl(primeDivisors, index + 1, current, result);
            current *= p;
        }
        return result;
    }

    public static long power(long base, long exponent) {
        if (exponent == 0) {
            return 1;
        }
        long result = power(base, exponent >> 1);
        result = result * result;
        if ((exponent & 1) != 0) {
            result = result * base;
        }
        return result;
    }

    public static long power(long base, long exponent, long mod) {
        if (base >= mod) {
            base %= mod;
        }
        if (exponent == 0) {
            return 1 % mod;
        }
        long result = power(base, exponent >> 1, mod);
        result = result * result % mod;
        if ((exponent & 1) != 0) {
            result = result * base % mod;
        }
        return result;
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static long[] generateFibonacci(long upTo) {
        int count = 0;
        long last = 0;
        long current = 1;
        while (current <= upTo) {
            long next = last + current;
            last = current;
            current = next;
            count++;
        }
        return generateFibonacci(count, -1);
    }

    public static long[] generateFibonacci(int count, long module) {
        long[] result = new long[count];
        if (module == -1) {
            if (count != 0) {
                result[0] = 1;
            }
            if (count > 1) {
                result[1] = 1;
            }
            for (int i = 2; i < count; i++) {
                result[i] = result[i - 1] + result[i - 2];
            }
        } else {
            if (count != 0) {
                result[0] = 1 % module;
            }
            if (count > 1) {
                result[1] = 1 % module;
            }
            for (int i = 2; i < count; i++) {
                result[i] = (result[i - 1] + result[i - 2]) % module;
            }
        }
        return result;
    }

    public static long[] generateHappy(int digits) {
        long[] happy = new long[(1 << (digits + 1)) - 2];
        happy[0] = 4;
        happy[1] = 7;
        int first = 0;
        int last = 2;
        for (int i = 2; i <= digits; i++) {
            for (int j = 0; j < last - first; j++) {
                happy[last + 2 * j] = 10 * happy[first + j] + 4;
                happy[last + 2 * j + 1] = 10 * happy[first + j] + 7;
            }
            int next = last + 2 * (last - first);
            first = last;
            last = next;
        }
        return happy;
    }

    public static long[] generateFactorial(int count, long module) {
        long[] result = new long[count];
        if (module == -1) {
            if (count != 0) {
                result[0] = 1;
            }
            for (int i = 1; i < count; i++) {
                result[i] = result[i - 1] * i;
            }
        } else {
            if (count != 0) {
                result[0] = 1 % module;
            }
            for (int i = 1; i < count; i++) {
                result[i] = (result[i - 1] * i) % module;
            }
        }
        return result;
    }

    public static long reverse(long number, long modulo) {
        extGcd(number, modulo);
        return trueMod(_x, modulo);
    }

    private static long extGcd(long a, long b) {
        if (a == 0) {
            _x = 0;
            _y = 1;
            return b;
        }
        long d = extGcd(b % a, a);
        long nx = _y - (b / a) * _x;
        _y = _x;
        _x = nx;
        return d;
    }

    public static boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long[] generateReverse(int upTo, long module) {
        long[] result = new long[upTo];
        if (upTo > 1) {
            result[1] = 1;
        }
        for (int i = 2; i < upTo; i++) {
            result[i] = (module - module / i * result[((int) (module % i))] % module) % module;
        }
        return result;
    }

    public static long[] generateReverseFactorials(int upTo, long module) {
        long[] result = generateReverse(upTo, module);
        if (upTo > 0) {
            result[0] = 1;
        }
        for (int i = 1; i < upTo; i++) {
            result[i] = result[i] * result[i - 1] % module;
        }
        return result;
    }

    public static long[] generatePowers(long base, int count, long mod) {
        long[] result = new long[count];
        if (count != 0) {
            result[0] = 1 % mod;
        }
        for (int i = 1; i < count; i++) {
            result[i] = result[i - 1] * base % mod;
        }
        return result;
    }

    public static long nextPrime(long from) {
        if (from <= 2) {
            return 2;
        }
        from += 1 - (from & 1);
        while (!isPrime(from)) {
            from += 2;
        }
        return from;
    }

    public static long binomialCoefficient(int n, int m, long mod) {
        if (m < 0 || m > n) {
            return 0;
        }
        if (2 * m > n) {
            m = n - m;
        }
        long result = 1;
        for (int i = n - m + 1; i <= n; i++) {
            result = result * i % mod;
        }
        return result * BigInteger.valueOf(factorial(m, mod)).modInverse(BigInteger.valueOf(mod)).longValue() % mod;
    }

    public static boolean isSquare(long number) {
        long sqrt = Math.round(Math.sqrt(number));
        return sqrt * sqrt == number;
    }

    public static long findCommon(long aRemainder, long aMod, long bRemainder, long bMod) {
        long modGCD = gcd(aMod, bMod);
        long gcdRemainder = aRemainder % modGCD;
        if (gcdRemainder != bRemainder % modGCD) {
            return -1;
        }
        aMod /= modGCD;
        aRemainder /= modGCD;
        bMod /= modGCD;
        bRemainder /= modGCD;
        BigInteger aReverse = BigInteger.valueOf(aMod).modInverse(BigInteger.valueOf(bMod));
        BigInteger bReverse = BigInteger.valueOf(bMod).modInverse(BigInteger.valueOf(aMod));
        BigInteger mod = BigInteger.valueOf(aMod * bMod);
        return bReverse.multiply(BigInteger.valueOf(aRemainder)).mod(mod).multiply(BigInteger.valueOf(bMod)).add(
                aReverse.multiply(BigInteger.valueOf(bRemainder)).mod(mod).multiply(BigInteger.valueOf(aMod))
        ).mod(mod).longValue() * modGCD + gcdRemainder;
    }

    public static long findCommonFast(long aRemainder, long aMod, long bRemainder, long bMod) {
        long aReverse = reverse(aMod, bMod);
        long bReverse = reverse(bMod, aMod);
        long mod = aMod * bMod;
        return (bReverse * aRemainder % mod * bMod + aReverse * bRemainder % mod * aMod) % mod;
    }

    public static long[] generatePowers(long base, long maxValue) {
        if (maxValue <= 0) {
            return new long[0];
        }
        int size = 1;
        long current = 1;
        while (maxValue / base >= current) {
            current *= base;
            size++;
        }
        return generatePowers(base, size, Long.MAX_VALUE);
    }

    static int divisorCount(int n, boolean[] primalityTable) {
        int total = 1;
        for (int p = 2; p <= n; p++) {
            if (primalityTable[p]) {
                int count = 0;
                if (n % p == 0) {
                    while (n % p == 0) {
                        n = n / p;
                        count++;
                    }
                    total = total * (count + 1);
                }
            }
        }
        return total;
    }

    public static boolean isRangeOverlap(long x1, long x2, long y1, long y2) {
        return x1 <= y2 && y1 <= x2;
    }

    public static boolean isRangeOverlap(int x1, int x2, int y1, int y2) {
        return x1 <= y2 && y1 <= x2;
    }

    public static long rangeFactorial(int start, int end) {
        long result = 1;
        for (int i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }

    public static int ncr(int n, int r) {
        return (int) (rangeFactorial(r + 1, n) / factorial(n - r));
    }
}
