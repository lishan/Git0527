package fred.yu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Fred on 16/5/28.
 */
public class CategoryTest {
    private Category category;
    @Before
    public void setUp() throws Exception {
        category = new Category("test");
    }

    @Test
    public void setDiscount() throws Exception {
        Category category1 = new Category("test1");
        Category category2 = new Category("test2");
        category.addChildCategory(category1);
        category.addChildCategory(category2);
        Discount discount = new Discount(new Date(), 0.7);
        category.addDiscount(discount);
        Assert.assertTrue(category1.getDiscounts().contains(discount));
    }

    @Test
    public void addChildCategory() throws Exception {
        Category category1 = new Category("test1");
        category.addChildCategory(category1);
        Assert.assertTrue(category.getChildCategories().contains(category1));
    }

    @Test
    public void afterDiscount() throws Exception {
        category.addDiscount(new Discount(new Date(), 0.7));
        double price = category.afterDiscount(new Date(), 1000);
        Assert.assertTrue(Math.abs(price-700) < 0.0001);
    }

    @After
    public void tearDown() throws Exception{
        category = null;
    }

}