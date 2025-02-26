package api;

/**
 * @author Vasiliki Zerdali,Alexander Damousis
 * This class is proccesing String data ,espesially prices and quantities
 * It is used when we want to show some results or when we want to do calculations with the prices and the quantity.
 */
public class CleanString
{
    private String newstring;

    /**
     * The constructor initializes the string which is going to be processed
     * @param newstring
     */
    public CleanString(String newstring)
    {
        this.newstring=newstring;
    }

    /**
     * Converts the string by replacing commas with dots, removing the euro and makes
     * the returning string is truncated after to decimals.
     * @return the "cleaned" string
     */
    public String convertString()
    {
        String price1,price2;
        price1=newstring.replace(",",".");
        price2=price1.replace("€","").trim();

        String finalLabelPrice="";
        for (int k = 0; k < price2.length(); k++)
        {
            char ch = price2.charAt(k);
            finalLabelPrice += ch;
            if (k >= 2) {
                if (price2.charAt(k - 2) == '.')
                    break;
            }
        }
        return finalLabelPrice;
    }

    /**
     * Calculates the final price based on the given quantity
     * @param quantity the quantity that multiplies with the price
     * @return the final price as string
     */
    public String showFinalPrice(String quantity)
    {
        double newPrice = Double.parseDouble(quantity)* Double.parseDouble(this.convertString());
        String semiFinalPrice = Double.toString(newPrice);
        CleanString finalPrice = new CleanString(semiFinalPrice);
        return finalPrice.convertString();

    }

    /**
     * Removes from the quantity the measures(kg or τεμάχια)
     * @return the string without the measures
     */
    public String removeMeasure()
    {
        String finalQuan="";
        if(newstring.contains("kg"))
        {
            finalQuan=newstring.replace("kg","").trim();
        }
        else
        {
            finalQuan=newstring.replace("τεμάχια","").trim();
        }
        return finalQuan;
    }


}