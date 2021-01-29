import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    RecordsList recordsList = new RecordsList();
    ArrayList<Sale> salesList = new ArrayList<>();

    public void readData(){
        File data=new File("src/Data.txt");
        try {
            int promoSaleNumber = 1;
            int SaleNum = 1;
            Scanner scanner = new Scanner(data);
            scanner.nextLine();
            String type;
            int quantity;
            double price;
            int promotion = 0;
            boolean promotionOccured = false;
            while(scanner.hasNext()) {
                RecieptRecord recieptRecord;
                type = scanner.next();
                if(type.charAt(0) == 'R') {
                    quantity = scanner.nextInt();
                    price = convertPriceToDouble(scanner.next());
                    System.out.println("An Order of "+quantity+" Widgets recived at $" +price);
                    recieptRecord = new RecieptRecord(quantity, price);
                    recordsList.add(recieptRecord);
                }
                else if(type.charAt(0) == 'S')
                {
                   System.out.println("***********SALE #"+SaleNum+"**************");
                    makeSale(scanner.nextInt(), promotion);
                    if(promotionOccured && promoSaleNumber >= 2) {
                        promotion = 0;
                        promotionOccured = false;
                        promoSaleNumber = 1;
                    }

                    else if(promotionOccured && promoSaleNumber <= 2){
                        promoSaleNumber++;
                    }
                    SaleNum++;
                }
                else if(type.charAt(0) == 'P'){
                    promotion = convertPromotionToInt(scanner.next());
                    promotionOccured = true;
                    System.out.println("Next 2 costumers will recive a Discount of " +promotion+ "%");
                }

            }

            System.out.println("\nWidgets Left in Stock \nquantity\tprice");
            System.out.println(recordsList.toString());
        }
        catch (FileNotFoundException fE)
        {
            fE.getMessage();
        }
    }

    public double convertPriceToDouble(String price){
        double priceInDouble;
        price = price.substring(1);
        priceInDouble = Double.parseDouble(price);

        return priceInDouble;
    }

    public int convertPromotionToInt(String promotion)
    {
        String[] split = promotion.split("%");
        return Integer.parseInt(split[0]);
    }

    public void makeSale(int quantity, int promotion){

        Sale sale;
        int count;
        ArrayList<Double> pricePerSale = new ArrayList<>();
        ArrayList<Integer> quantityPerSale = new ArrayList<>();
        int quantityAskedFor = quantity;
        int quantitySold = 0;
        double buyingPrice;
        double salePriceWithOutPromo;
        int saleMade;

        int i = 0;
        while(quantity != 0) {
            if(recordsList.first.recieptRecord.getQuantity() == quantity){
                buyingPrice = recordsList.first.recieptRecord.getPrice();
                salePriceWithOutPromo = buyingPrice + ((30 * buyingPrice) / 100);
                pricePerSale.add(salePriceWithOutPromo - ((promotion * salePriceWithOutPromo)/100));
                quantityPerSale.add(quantity);
                quantitySold += quantity;

                quantity = 0;
                recordsList.first.recieptRecord.setQuantity(0);
                recordsList.remove(0);

            }

            else if (recordsList.first.recieptRecord.getQuantity() > quantity) {
                buyingPrice = (recordsList.first.recieptRecord.getPrice());
                //pricePerSale.add(buyingPrice + ((30 * buyingPrice) / 100));
                salePriceWithOutPromo = buyingPrice + ((30 * buyingPrice) / 100);
                pricePerSale.add(salePriceWithOutPromo - ((promotion * salePriceWithOutPromo)/100));
                quantityPerSale.add(quantity);
                quantitySold += quantity;

                recordsList.first.recieptRecord.setQuantity(recordsList.first.recieptRecord.getQuantity() - quantity);
                quantity = 0;
            }

            else if(recordsList.first.recieptRecord.getQuantity() < quantity && recordsList.first.next == null)
            {
                buyingPrice = (recordsList.first.recieptRecord.getPrice());
                //pricePerSale.add(buyingPrice + ((30 * buyingPrice) / 100));
                salePriceWithOutPromo = buyingPrice + ((30 * buyingPrice) / 100);
                pricePerSale.add(salePriceWithOutPromo - ((promotion * salePriceWithOutPromo)/100));
                quantityPerSale.add(recordsList.first.recieptRecord.getQuantity());
                quantitySold += recordsList.first.recieptRecord.getQuantity();

                recordsList.remove(0);
                quantity = 0;
            }
            else if (recordsList.first.recieptRecord.getQuantity() < quantity) {
                buyingPrice = (recordsList.first.recieptRecord.getPrice());
                //pricePerSale.add(buyingPrice + ((30 * buyingPrice) / 100));
                salePriceWithOutPromo = buyingPrice + ((30 * buyingPrice) / 100);
                pricePerSale.add(salePriceWithOutPromo - ((promotion * salePriceWithOutPromo)/100));
                quantityPerSale.add(recordsList.first.recieptRecord.getQuantity());
                quantitySold += recordsList.first.recieptRecord.getQuantity();
                quantity -= quantityPerSale.get(i);
                recordsList.remove(0);
            } else
                System.out.println("No price Given");

            i++;
        }

        sale = new Sale(quantityAskedFor, quantitySold, pricePerSale, quantityPerSale);
        System.out.println(sale);
        salesList.add(sale);
    }



    public void start(){
        readData();
    }

}
