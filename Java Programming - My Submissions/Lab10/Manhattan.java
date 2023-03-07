import java.math.BigDecimal;

public class Manhattan {
    public static void main(String[] args) {
        // Create variables for initial investment, interest rate, and multiply value for final interest rate
        final double initialInvestment = 24;
        double totalInterestRate = 1.08;
        // For years specified, multiply the initial interest rate by 1.08
        for (int x = 0; x < 395; x++) {
            totalInterestRate = 1.08 * totalInterestRate;
        }
        // Print out the final interest rate times the initial investment of $24
        System.out.println("$" + new BigDecimal(totalInterestRate * initialInvestment).toPlainString());
    }
}
