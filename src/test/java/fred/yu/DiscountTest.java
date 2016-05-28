package fred.yu;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Yulishan on 16/5/28.
 */
public class DiscountTest {
    @Test
    public void validDate() throws Exception {
        Discount discount = new Discount(new Date(), 0.7);
        Assert.assertTrue(discount.validDate(new Date()));
    }

}