package fred.yu;

import java.util.Date;

/**
 * Created by Fred on 16/5/27.
 */
public class Policy {
    private Date validDate;
    private double necessaryCost;
    private double discount;

    public Policy(Date validDate, double discount, double necessaryCost) {
        this.validDate = validDate;
        this.discount = discount;
        this.necessaryCost = necessaryCost;
    }

    /**
     * If shopping date is before preferential policy limit date and money is higher than limit,
     * we'll do some fee reduce
     * @param buyDate Shopping date
     * @param costs Shopping costs
     * @return After fee reduce
     * @throws ShoppingException
     */
    public double costCut(Date buyDate, double costs) throws ShoppingException{
        if(buyDate == null || validDate == null){
            throw new ShoppingException("Input date is null", new NullPointerException());
        }
        if(buyDate.compareTo(validDate) <= 0 && costs >= necessaryCost){
            double result = costs-discount;
            if(result < 0){
                throw new ShoppingException("After discount, costs below zero");
            }
            return result;
        }
        return costs;
    }
}
