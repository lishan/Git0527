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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount1 = (Discount) o;

        if (Double.compare(discount1.discount, discount) != 0) return false;
        return date != null ? date.equals(discount1.date) : discount1.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Compare whether same day with discount
     * @param date Shopping date
     * @return Same or different
     */
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
