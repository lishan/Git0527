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

    public double costCut(Date buyDate, double costs) throws ShoppingException{
        if(buyDate == null || validDate == null){
            throw new ShoppingException("Input date is null", new NullPointerException());
        }
        if(buyDate.before(validDate) && costs >= necessaryCost){
            double result = costs-discount;
            if(result < 0){
                throw new ShoppingException("After discount, costs below zero");
            }
            return result;
        }
        return costs;
    }
}
