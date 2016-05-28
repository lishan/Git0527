package fred.yu;

import java.util.Date;

/**
 * Created by Fred on 16/5/28.
 */
public class InputPolicy {
    private Date endDate;
    private double costs;
    private double discount;

    public InputPolicy(Date endDate, double costs, double discount) {
        this.endDate = endDate;
        this.costs = costs;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "InputPolicy{" +
                "endDate=" + endDate +
                ", costs=" + costs +
                ", discount=" + discount +
                '}';
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
