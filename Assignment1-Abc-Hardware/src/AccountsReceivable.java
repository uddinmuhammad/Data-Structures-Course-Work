import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

class AccountsReceivable {
    String masterFile = "src/Master.txt";
    String transactionFile = "src/Transaction.txt";

    // Integer holds CustomerNumber and Invoice holds the Customer and a list of Transactions
    private TreeMap<Integer, Invoice> invoices;
    List<Transaction> transactions;
    List<Customer> customers;

    AccountsReceivable() {
        invoices = new TreeMap<>();
    }

    public void readTransactions() {

        try {
            Scanner scanner = new Scanner(new File(transactionFile));
            transactions = new ArrayList<>();
            boolean firstCustomer = true;
            Transaction transaction;
            int previousCustomer = 0;
            int newCustomerNumber;
            scanner.nextLine();

            while (scanner.hasNext()){

                transaction = new Transaction();
                transaction.setTransactionType(scanner.next().charAt(0));

                newCustomerNumber = scanner.nextInt();

                if (firstCustomer) {
                    previousCustomer = newCustomerNumber;
                    firstCustomer = false;
                }


                transaction.setCustomerNumber(newCustomerNumber);
                transaction.setTransactionNum(scanner.nextInt());

                if (transaction.getTransactionType() == 'P'){
                    transaction.setPayment(scanner.nextDouble());
                } else {
                    transaction.setItemName(scanner.next());
                    transaction.setQuantity(scanner.nextInt());
                    transaction.setItemPrice(scanner.nextDouble());
                }

                if (previousCustomer != newCustomerNumber){
                    transactions = new ArrayList<>();
                    previousCustomer = newCustomerNumber;
                }


                transactions.add(transaction);
                addTransactionsToInvoice(transactions);
            }

            //System.out.println("Trastion Map" +transactionsMap);

        } catch (FileNotFoundException e) {
            System.out.println(transactionFile + " file not found");
            e.printStackTrace();
        }

    }

    public void addTransactionsToInvoice(List<Transaction> transactions) {
        this.invoices.get(transactions.get(0).getCustomerNumber()).setTransactions(transactions);
    }

    public void readMasterFile() {
        try {
            Scanner scanner = new Scanner(new File(masterFile));
            customers = new ArrayList<>();
            Customer customer;
            scanner.nextLine();
            while (scanner.hasNext()){
                customer = new Customer();
                customer.setCustomerNumber(scanner.nextInt());
                customer.setName(scanner.next());
                customer.setPreviousBalanceDue(scanner.nextDouble());

                addCustomerToInvoice(customer);
                customers.add(customer);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Master file not found");
            e.printStackTrace();
        }
    }

    public void addCustomerToInvoice(Customer customer) {
        Invoice invoice = new Invoice(customer);
        this.invoices.put(customer.getCustomerNumber(), invoice);
    }

    public void printInvoices(){

        for(int i = 0; i < invoices.size(); i++)
        {
            int customerNumber = customers.get(i).getCustomerNumber();
            String cName = invoices.get(customerNumber).getCustomer().getName();
            double cPreviousBalanceDue = invoices.get(customerNumber).getCustomer().getPreviousBalanceDue();
            double newBalanceDue = invoices.get(customerNumber).totalBalanceDue();

            System.out.println(cName +"\t\t" + customerNumber +"\n\t\t\tPervious Balance:\t" + cPreviousBalanceDue);

            for(int j = 0; j < invoices.get(customerNumber).getTransactions().size(); j++)
            {
                //System.out.println(invoices.get(customerNumber).getTransactions().size());
                int transactionNumber = invoices.get(customerNumber).getTransactions().get(j).getTransactionNum();
                String itemName = invoices.get(customerNumber).getTransactions().get(j).getItemName();
                int quantity = invoices.get(customerNumber).getTransactions().get(j).getQuantity();
                double orderTotal = invoices.get(customerNumber).orderAmount(invoices.get(customerNumber).getTransactions().get(j));
                char transactionType = invoices.get(customerNumber).getTransactions().get(j).getTransactionType();
                double paymentAmount = invoices.get(customerNumber).getTransactions().get(j).getPayment();

                switch (transactionType) {
                    case 'O':
                        System.out.println(transactionNumber + "\t" + itemName + "\t" + quantity + "\t" +orderTotal);
                        break;
                    case 'P':
                        System.out.println(transactionNumber + "\tPayment\t\t\t\t\t\t" + paymentAmount);

                }

            }

            System.out.println("\t\t\tBalance Due:\t"+newBalanceDue);

        }
    }
    // Read the master file

    public void start()  {
        // read master file
        readMasterFile();

        // process transactions
        readTransactions();

        // Print the invoices
        printInvoices();

    }

}