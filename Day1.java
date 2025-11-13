import java.util.Scanner;

public class Calculator {

    static double add(double a, double b) {
        return a + b;
    }

    static double subtract(double a, double b) {
        return a - b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Cannot divide by zero");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 5) break;

            System.out.print("Enter first number: ");
            double x = sc.nextDouble();
            System.out.print("Enter second number: ");
            double y = sc.nextDouble();

            switch (choice) {
                case 1 -> System.out.println("Result: " + add(x, y));
                case 2 -> System.out.println("Result: " + subtract(x, y));
                case 3 -> System.out.println("Result: " + multiply(x, y));
                case 4 -> System.out.println("Result: " + divide(x, y));
                default -> System.out.println("Invalid option");
            }
        }

        sc.close();
    }
}
