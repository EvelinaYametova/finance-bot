public class AccountData {
    public double MyIncome;
    public double MyExpense;
    public double MyTotal;

    public AccountData(double income, double expense, double total)
    {
        MyIncome = income;
        MyExpense = expense;
        MyTotal = total;
    }
    @Override
    public String toString() {
        return String.format(" Доход: %.2f\n Расходы: %.2f\n Остаток: %.2f", MyIncome, MyExpense, MyTotal);
    }
}
