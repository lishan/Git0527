package fred.yu;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.ExpectedException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Fred on 16/5/28.
 */
public class PolicyTest {
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void costCut() throws Exception {
        Policy policy = new Policy(new Date(), 100, 1000);
        double v = policy.costCut(new Date(), 2000);
        Assert.assertTrue(Math.abs(v-1900) < 0.001);
        Date parse1 = df.parse("2016-5-28");
        Date parse2 = df.parse("2016-5-29");
        policy = new Policy(parse1, 100, 200);
        double v1 = policy.costCut(parse2, 300);
        Assert.assertTrue(Math.abs(v1-300) < 0.001);
        policy = new Policy(parse2, 100, 200);
        double v2 = policy.costCut(parse1, 300);
        Assert.assertTrue(Math.abs(v2-200) < 0.001);

    }

    @Test(expected = ShoppingException.class)
    public void costCutContainsNull() throws Exception {
        Policy policy = new Policy(null, 100, 1000);
        policy.costCut(new Date(), 2000);
    }

    @Test
    public void costCutLowerThanZero() throws Exception {
        Policy policy = new Policy(new Date(), 10000, 1000);
        try {
            policy.costCut(new Date(), 2000);
        }catch (Exception e){
            Assert.assertTrue(e instanceof ShoppingException);
            Assert.assertEquals(e.getMessage(), "After discount, costs below zero");
        }

    }

}