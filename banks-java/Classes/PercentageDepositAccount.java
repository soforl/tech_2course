package Classes;

public class PercentageDepositAccount {
    private Bank bank;
    private double percentage;
    private double lowerBorderSum;
    private double upperBorderSum;

    public PercentageDepositAccount(Bank bank, double percentage, double lowerBorderSum, double upperBorderSum) {
        this.bank = bank;
        this.percentage = percentage;
        this.upperBorderSum = upperBorderSum;
        this.lowerBorderSum = lowerBorderSum;
    }

    public Bank getBank() {
        return bank;
    }

    public double getSum2() {
        return upperBorderSum;
    }

    public double getSum1() {
        return lowerBorderSum;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setLowerBorderSum(double lowerBorderSum) {
        this.lowerBorderSum = lowerBorderSum;
    }

    public void setUpperBorderSum(double upperBorderSum) {
        this.upperBorderSum = upperBorderSum;
    }

}
