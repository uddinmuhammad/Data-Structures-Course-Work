import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sale {
    private int quantityAskedFor;
    private int quantitySold;
    private ArrayList<Double> pricePerSale;
    private ArrayList<Integer> quantityPerSale;
    private ArrayList<Double> totalPerSale;
    private double totalSale;

    public Sale() {
    }

    public Sale(int quantityAskedFor, int quantitySold, ArrayList<Double> pricePerSale, ArrayList<Integer> quantityPerSale) {
        this.quantityAskedFor = quantityAskedFor;
        this.quantitySold = quantitySold;
        this.pricePerSale = pricePerSale;
        this.quantityPerSale = quantityPerSale;

        caculateTotalPerSale();
    }

    public int getQuantityAskedFor() {
        return quantityAskedFor;
    }

    public void setQuantityAskedFor(int quantityAskedFor) {
        this.quantityAskedFor = quantityAskedFor;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold += quantitySold;
    }

    public ArrayList<Double> getPricePerSale() {
        return pricePerSale;
    }

    public void setPricePerSale(ArrayList<Double> pricePerSale) {
        this.pricePerSale = pricePerSale;
    }

    public ArrayList<Integer> getQuantityPerSale() {
        return quantityPerSale;
    }

    public void setQuantityPerSale(ArrayList<Integer> quantityPerSale) {
        this.quantityPerSale = quantityPerSale;
    }

    public void caculateTotalPerSale()
    {
        double total;
        totalPerSale = new ArrayList<>();
        for(int i = 0; i < pricePerSale.size(); i++)
        {
         totalPerSale.add(pricePerSale.get(i) * quantityPerSale.get(i));
        }

        for(int i = 0; i < totalPerSale.size(); i++)
        {
            totalSale += totalPerSale.get(i);
        }
    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#####.##");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(quantitySold);
        stringBuilder.append(" Widgets sold\n");

        for(int i = 0; i < quantityPerSale.size(); i++)
        {
            stringBuilder.append(quantityPerSale.get(i));
            stringBuilder.append(" at ");
            stringBuilder.append(df.format(pricePerSale.get(i)));
            stringBuilder.append(" each\t\tSales: $ ");
            stringBuilder.append(df.format(totalPerSale.get(i)));
            stringBuilder.append("\n");
        }

        stringBuilder.append("\t\t\t\t\tTotal Sale:");
        stringBuilder.append(df.format(totalSale));
        stringBuilder.append("\n");

        if(quantityAskedFor > quantitySold)
        {
            stringBuilder.append("Remainder of ");
            stringBuilder.append(quantityAskedFor-quantitySold);
            stringBuilder.append(" Widgets not available\n");
        }


        return stringBuilder.toString();
    }
}
