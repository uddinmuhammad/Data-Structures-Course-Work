class Customer {

    private String name;
    private int customerNumber;
    private double previousBalanceDue;

    public Customer() {

    }
    public Customer(int customerNumber, String name, double previousBalanceDue) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.previousBalanceDue = previousBalanceDue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public double getPreviousBalanceDue() {
        return previousBalanceDue;
    }

    public void setPreviousBalanceDue(double previousBalanceDue) {
        this.previousBalanceDue = previousBalanceDue;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", customerNumber=" + customerNumber +
                ", amountDue=" + previousBalanceDue +
                '}';
    }
}