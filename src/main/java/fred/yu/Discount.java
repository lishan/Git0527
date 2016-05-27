package fred.yu;

import java.util.Date;

/**
 * Created by Fred on 16/5/28.
 */
public class Discount {
    private Date date;
    private double discount;

    public Discount(Date date, double discount) {
        this.date = date;
        this.discount = discount;
    }

    public boolean validDate(Date date){
        if(this.date != null && this.date.compareTo(date) == 0){
            return true;
        }
        return false;
    }

    public double afterDiscount(double costs){
        return costs * discount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "date=" + date +
                ", discount=" + discount +
                '}';
    }
}
