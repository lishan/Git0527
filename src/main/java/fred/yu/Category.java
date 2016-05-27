package fred.yu;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Fred on 16/5/27.
 */
public class Category {
    private String name;
    private List<Category> childCategories;
    private Discount discount;

    public Category(String name) {
        this.name = name;
        this.childCategories = new LinkedList<>();
        this.discount = null;
    }

    public String getName() {
        return name;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Category addChildCategory(Category category) throws ShoppingException{
        if(category != null && this.childCategories != null) {
            this.childCategories.add(category);
            return this;
        }
        throw new ShoppingException("Add child category null", new NullPointerException());
    }

    public double afterDiscount(Date date, double cost){
        if(this.discount.validDate(date)){
            return this.discount.afterDiscount(cost);
        }
        return cost;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", childCategories=" + childCategories +
                ", discount=" + discount +
                '}';
    }
}
