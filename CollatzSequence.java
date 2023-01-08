import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

public class CollatzSequence {

    private Map<BigInteger, BigInteger> table = new LinkedHashMap<>();
    private BigInteger maxNumber = new BigInteger("0");
    private BigInteger maxChain = new BigInteger("0");

    CollatzSequence() {
        table.put(BigInteger.ONE, BigInteger.ONE);
    }

    /**
     * Finds the number less than 10000000 which produces the longest chain
     * 
     * @return {n, chain length of number n}
     */
    public BigInteger[] solve() {
        for (int i = 2; i <= 1000000; i++) {
            this.findChainLength(new BigInteger(i + ""));
        }

        System.out.println(
                "The number " + this.maxNumber + " produces the longest chain with length: " + this.maxChain);

        return new BigInteger[] { maxNumber, maxChain };
    }

    /**
     * Finds the chain length that n produces and the chain length of every element
     * that appears on the chain of n.
     * 
     * @param n
     * @return the chain length that number n produces
     */
    public BigInteger findChainLength(BigInteger n) {
        BigInteger chainLength = BigInteger.ZERO;
        BigInteger result = new BigInteger(n.toString());
        chainLength = chainLength.add(BigInteger.ONE);

        result = compute(n);
        BigInteger lengthOnMap = table.get(result);

        if (lengthOnMap != null) {
            chainLength = chainLength.add(lengthOnMap);
        } else {
            chainLength = chainLength.add(findChainLength(result));
        }

        table.put(n, chainLength);

        if (lessThan(n, new BigInteger("1000000"))) {
            if (lessThan(maxChain, chainLength)) {
                maxChain = new BigInteger(chainLength.toString());
                maxNumber = new BigInteger(n.toString());
            }

        }
        return chainLength;
    }

    /**
     * @param n
     * @return n/2 if n is even, 3n+1 if n is odd
     */
    public BigInteger compute(BigInteger n) {
        BigInteger two = new BigInteger("2");
        BigInteger three = new BigInteger("3");
        if (isEven(n)) {
            return n.divide(two);
        } else {
            return (n.multiply(three)).add(BigInteger.ONE);
        }
    }

    /**
     * @param n
     * @return true if n is even
     */
    private boolean isEven(BigInteger n) {
        return (n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0);
    }

    /**
     * 
     * @param n
     * @param x
     * @return true if n<x
     */
    private boolean lessThan(BigInteger n, BigInteger x) {
        return n.compareTo(x) == -1;
    }
}
