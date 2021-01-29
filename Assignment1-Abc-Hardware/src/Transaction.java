class Transaction {
    int customerNumber;
    int transactionNum;
    String itemName;
    int quantity;
    char transactionType;
    double itemPrice;
    double payment;

    public Transaction(){

    }

    public Transaction(int transactionNum, String itemName, char transactionType, int quantity, double itemPrice) {
        this.transactionNum = transactionNum;
        this.itemName = itemName;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(int transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(char transactionType) {
        this.transactionType = transactionType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerNumber=" + customerNumber +
                ", transactionNum=" + transactionNum +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", transactionType=" + transactionType +
                ", itemPrice=" + itemPrice +
                ", payment=" + payment +
                '}';
    }
}

