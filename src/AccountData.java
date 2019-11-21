public class AccountData {
    public double MyIncome;
    public double MyExpense;

    public double getMyTotal(){
        return MyIncome - MyExpense;
    }

   public AccountData(double income, double expense)
    {
        MyIncome = income;
        MyExpense = expense;
    }
    @Override
    public String toString() {
        return String.format(" Доход: %.2f\n Расходы: %.2f\n Остаток: %.2f", MyIncome, MyExpense, this.getMyTotal());
    }
}
