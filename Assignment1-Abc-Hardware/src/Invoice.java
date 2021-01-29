import java.util.List;

class Invoice {

    private Customer customer;
    private List<Transaction> transactions;

    public Invoice() {
    }

    public Invoice(Customer customer) {
        this.customer= customer;
    }

    public Invoice(Customer customer, List<Transaction> transactions) {
        this.customer = customer;
        this.transactions = transactions;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                " " + customer + "Due:" +
                ", " + transactions +
                '}';
    }


    public void print()
    {
        System.out.println(customer.getName()+"\t\t"+customer.getCustomerNumber());
    }


    public double orderAmount(Transaction transaction)
    {
        return transaction.getQuantity() *transaction.itemPrice;
    }

    public double totalBalanceDue()
    {
        double newBalanaceDue = customer.getPreviousBalanceDue();

        for(int i = 0; i < transactions.size(); i++)
        {
            if(transactions.get(i).getTransactionType() == 'P')
            {
                newBalanaceDue -= transactions.get(i).getPayment();
            }

            else
                newBalanaceDue += (transactions.get(i).getItemPrice() * transactions.get(i).getQuantity());
        }

        return newBalanaceDue;
    }
}