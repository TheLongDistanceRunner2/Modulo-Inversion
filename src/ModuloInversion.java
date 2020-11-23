import java.math.BigInteger;
import java.util.Vector;

public class ModuloInversion {
    private BigInteger a;
    private BigInteger b;
    private Vector<Inversion> table;

    public ModuloInversion(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
        this.table = new Vector<>();
    }

    public int invMod(BigInteger a, BigInteger b) {
        Inversion inversion = new Inversion();
        BigInteger index = new BigInteger("2"); // 3 !!!

        Inversion tmp1 = new Inversion();
        Inversion tmp2 = new Inversion();

        // a > b:
        if (a.compareTo(b) == 1) {
            tmp1.setR(a);
            tmp1.setS(BigInteger.valueOf(1));
            tmp2.setR(b);
            tmp2.setS(BigInteger.valueOf(0));

            table.add(tmp1);
            table.add(tmp2);
            System.out.println("q: " + table.get(0).getQ() + " r: " + table.get(0).getR() + " s: " + table.get(0).getS());
            System.out.println("q: " + table.get(1).getQ() + " r: " + table.get(1).getR() + " s: " + table.get(1).getS());
        }
        // a < b:
        else if (a.compareTo(b) == -1) {
            tmp1.setR(b);
            tmp1.setS(BigInteger.valueOf(0));
            tmp2.setR(a);
            tmp2.setS(BigInteger.valueOf(1));

            table.add(tmp1);
            table.add(tmp2);
            System.out.println("q: " + table.get(0).getQ() + " r: " + table.get(0).getR() + " s: " + table.get(0).getS());
            System.out.println("q: " + table.get(1).getQ() + " r: " + table.get(1).getR() + " s: " + table.get(1).getS());
        }

        boolean flag = true;
        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");

        while (flag) {
            // calculate q:
            BigInteger indexRminus2 = index.subtract(two);
            String stringIndexRminus2 = indexRminus2.toString();
            int intIndexRminus2 = Integer.parseInt(stringIndexRminus2);
            BigInteger valueRminus2 = table.get(intIndexRminus2).getR();

            BigInteger indexRminus1 = index.subtract(one);
            String stringIndexRminus1 = indexRminus1.toString();
            int intIndexRminus1 = Integer.parseInt(stringIndexRminus1);
            BigInteger valueRminus1 = table.get(intIndexRminus1).getR();

            BigInteger q = valueRminus2.divide(valueRminus1);
            System.out.print("q: " + q);

            // calculate r:
            BigInteger r = valueRminus2.mod(valueRminus1);
            System.out.print(" r: " + r);

            // calculate s:
            BigInteger indexSminus2 = index.subtract(two);
            String stringIndexSminus2 = indexSminus2.toString();
            int intIndexSminus2 = Integer.parseInt(stringIndexSminus2);
            BigInteger valueSminus2 = table.get(intIndexSminus2).getS();

            BigInteger indexSminus1 = index.subtract(one);
            String stringIndexSminus1 = indexSminus1.toString();
            int intIndexSminus1 = Integer.parseInt(stringIndexSminus1);
            BigInteger valueSminus1 = table.get(intIndexSminus1).getS();

            BigInteger tmpS = q.multiply(valueSminus1);
            BigInteger s = valueSminus2.subtract(tmpS);
            System.out.print(" s: " + s + "\n");

            Inversion tmpInversion = new Inversion();
            tmpInversion.setQ(q);
            tmpInversion.setR(r);
            tmpInversion.setS(s);

            // add to table of:
            table.add(tmpInversion);

            // if r == 0:
            if (r.equals(BigInteger.valueOf(0))) {
                flag = false;

                String stringIndex = index.toString();
                int intIndex = Integer.parseInt(stringIndex);
                // decrement index to check the value at previous itereation:
                intIndex--;

                //    check the value of r at index - 1:
                // if it doesn't equal to 1, return -1:
                if (!table.get(intIndex).getR().equals(BigInteger.valueOf(1))) {
                    return -1;
                }
                // if it's all right:
                else {
                    //    check the value of s:
                    // if s is positive:
                    if (table.get(intIndex).getS().compareTo(BigInteger.valueOf(0)) == 1) {
                        String sString = table.get(intIndex).getS().toString();
                        int intS = Integer.parseInt(sString);
                        // return s:
                        return intS;
                    }
                    // if s is negative:
                    else if (table.get(intIndex).getS().compareTo(BigInteger.valueOf(0)) == -1) {
                        // modify the value of s:
                        String tmpA = a.toString();
                        int intA = Integer.parseInt(tmpA);

                        String tmpB = b.toString();
                        int intB = Integer.parseInt(tmpB);

                        String sString = table.get(intIndex).getS().toString();
                        int intS = Integer.parseInt(sString);

                        //int newS = intA * (intS + intB) % intB;

                        if (intS > 0) {
                            return intS;
                        }
                        else {
                            while (intS < 0) {
                                intS += intB;
                            }

                            return intS;
                        }
                    }
                }
            }

            // increment index:
            index = index.add(one);
        }

        return -111;
    }

    // helper class:
    private class Inversion {
        private BigInteger q;
        private BigInteger r;
        private BigInteger s;

        public Inversion() {
            this.q = BigInteger.valueOf(0);
            this.r = BigInteger.valueOf(0);
            this.s = BigInteger.valueOf(0);
        }

        public BigInteger getQ() {
            return q;
        }

        public BigInteger getR() {
            return r;
        }

        public BigInteger getS() {
            return s;
        }

        public void setQ(BigInteger q) {
            this.q = q;
        }

        public void setR(BigInteger r) {
            this.r = r;
        }

        public void setS(BigInteger s) {
            this.s = s;
        }
    }
}
