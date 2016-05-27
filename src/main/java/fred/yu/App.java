package fred.yu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    private List<Category> categories;

    private void scanInput() throws ShoppingException{
        Scanner scanner = new Scanner(System.in);
        scanDiscount(scanner);
        for(int i = 0 ; i < categories.size() ; i++){
            System.out.println(categories.get(i));
        }
    }

    private void scanDiscount(Scanner scanner) throws ShoppingException{
        while(scanner.hasNextLine()){
            String s = lineContent(scanner.nextLine());
            if(s == null){
                break;
            }
            String[] split = s.split("\\|");
            if(split.length != 3){
                throw new ShoppingException("Input error", new IllegalStateException());
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date parse = df.parse(split[0]);
                Category c = findCategory(split[2].trim());
                if(c == null) {
                    throw new ShoppingException("Cannot find category for " + split[2], new NullPointerException());
                }
                else{
                    c.setDiscount(new Discount(parse, Double.parseDouble(split[1].trim())));
                }

            }catch (ParseException e){
                throw new ShoppingException("Parse date error",e);
            }
        }
    }

    private Category findCategory(String s) {
        for(int i = 0 ; i < categories.size(); i++){
            categories.get(i).getName().equals(s);
            return categories.get(i);
        }
        return null;
    }

    private String lineContent(String s) {
        String[] split = s.split("//");
        if(split[0].trim().equals("")) {
            return null;
        }
        return split[0];
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.initCategoryInfo();
            app.scanInput();
        }catch (ShoppingException e){
            System.out.println(e);
        }
    }

    private void initCategoryInfo() throws ShoppingException {
        categories = new ArrayList<>();
        Category c1 = new Category("电子");
        c1.addChildCategory(new Category("ipad"))
                .addChildCategory(new Category("iphone"))
                .addChildCategory(new Category("显示器"))
                .addChildCategory(new Category("笔记本电脑"))
                .addChildCategory(new Category("键盘"));
        Category c2 = new Category("食品");
        c2.addChildCategory(new Category("面包"))
                .addChildCategory(new Category("饼干"))
                .addChildCategory(new Category("蛋糕"))
                .addChildCategory(new Category("牛肉"))
                .addChildCategory(new Category("鱼"))
                .addChildCategory(new Category("蔬菜"));
        Category c3 = new Category("日用品");
        c3.addChildCategory(new Category("餐巾纸"))
                .addChildCategory(new Category("收纳箱"))
                .addChildCategory(new Category("咖啡杯"))
                .addChildCategory(new Category("雨伞"));
        Category c4 = new Category("酒类");
        c4.addChildCategory(new Category("啤酒"))
                .addChildCategory(new Category("白酒"))
                .addChildCategory(new Category("伏特加"));
        categories.add(c1);
        categories.addAll(c1.getChildCategories());
        categories.add(c2);
        categories.addAll(c2.getChildCategories());
        categories.add(c3);
        categories.addAll(c3.getChildCategories());
        categories.add(c4);
        categories.addAll(c4.getChildCategories());
    }
}
