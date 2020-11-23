import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        System.out.println("Enter a: ");
        BigInteger a;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextBigInteger();

        System.out.println("Enter b: ");
        BigInteger b;
        b = scanner.nextBigInteger();

        ModuloInversion moduloInversion1 = new ModuloInversion(a, b);
        System.out.println("Result (s): " + moduloInversion1.invMod(a, b));
    }
}
