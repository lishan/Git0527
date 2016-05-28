package fred.yu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fred on 16/5/28.
 */
public class AppTest {
    private App app;
    @Before
    public void setUp() throws Exception {
        app = new App();
    }
    @Test
    public void findCategory() throws Exception {
        app.initCategoryInfo();
        Assert.assertNull(app.findCategory("硬盘"));
        Assert.assertTrue(app.findCategory("电子").getChildCategories().size() == 5);
    }

    @Test
    public void lineContent() throws Exception {
        String s = app.lineContent("This is content   //This is comments");
        Assert.assertEquals(s, "This is content");
        s = app.lineContent("//This is comments");
        Assert.assertNull(s);
    }

    @After
    public void tearDown() throws Exception {
        app = null;
    }
}