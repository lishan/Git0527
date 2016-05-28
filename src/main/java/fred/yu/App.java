package fred.yu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    private List<Category> categories;
    private List<Item> items;
    private Date broughtDate;
    private InputPolicy inputPolicy;
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    private void scanInput() throws ShoppingException{
        Scanner scanner = new Scanner(System.in);
        scanDiscount(scanner);
        scanBrought(scanner);
        scanDateAndPolicy(scanner);
    }

    private void scanDateAndPolicy(Scanner scanner) throws ShoppingException{
        if(scanner.hasNextLine()){
            String s = lineContent(scanner.nextLine());
            try {
                broughtDate = df.parse(s);
            }catch (ParseException e){
                throw new ShoppingException("Brought date error " + s, e);
            }
        }
        if(scanner.hasNextLine()){
            String s = lineContent(scanner.nextLine());
            if(s == null){
                inputPolicy = null;
                return;
            }
            String[] split = s.split(" ");
            if(split.length != 3){
                throw new ShoppingException("Preferential policy input error " + s);
            }
            try{
                Date date = df.parse(split[0].trim());
                inputPolicy = new InputPolicy(date, Double.parseDouble(split[1].trim()) , Double.parseDouble(split[2].trim()));
            } catch (ParseException e){
                throw new ShoppingException("Preferential policy date error " + s, e);
            }
        }

    }

    private void scanBrought(Scanner scanner) throws ShoppingException {
        items = new LinkedList<>();
        while(scanner.hasNextLine()){
            String s = lineContent(scanner.nextLine());
            if(s == null){
                break;
            }
            String[] split = s.split(":");
            if(split.length != 2){
                throw new ShoppingException("Input brought item error " + s, new IllegalStateException());
            }
            String[] split1 = split[0].split("\\*");
            if(split1.length != 2){
                throw new ShoppingException("Input brought item error " + s, new IllegalStateException());
            }
            int num = Integer.parseInt(split1[0].trim());
            double price = Double.parseDouble(split[1].trim());
            items.add(new Item(split1[1].trim(),num,price));
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
            try {
                Date parse = df.parse(split[0].trim());
                Category c = findCategory(split[2].trim());
                if(c == null) {
                    throw new ShoppingException("Cannot find category for " + split[2], new NullPointerException());
                }
                else{
                    c.addDiscount(new Discount(parse, Double.parseDouble(split[1].trim())));
                }

            }catch (ParseException e){
                throw new ShoppingException("Parse date error",e);
            }
        }
    }

    Category findCategory(String s) {
        for(Category category : categories){
            if(category.getName().equals(s)) {
                return category;
            }
        }
        return null;
    }

    String lineContent(String s) {
        String[] split = s.split("//");
        if(split[0].trim().equals("")) {
            return null;
        }
        return split[0].trim();
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.initCategoryInfo();
            app.scanInput();
            System.out.printf("%.2f", app.computeResult());
        }catch (ShoppingException e){
            e.printStackTrace();
        }
    }

    private double computeResult() throws ShoppingException{
        double result = 0;
        for(Item item : items){
            result += computeDiscount(broughtDate, item.getNum() * item.getPrice(), findCategory(item.getName()));
        }
        if(inputPolicy != null){
            if(result >= inputPolicy.getCosts() && broughtDate.compareTo(inputPolicy.getEndDate()) <= 0){
                result -= inputPolicy.getDiscount();
            }
        }
        if(result < 0){
            throw new ShoppingException("Compute result error, result lower than zero " + result);
        }
        return result;
    }

    private double computeDiscount(Date broughtDate, double price, Category category) throws ShoppingException{
        if(category == null){
            throw new ShoppingException("Error input for computing discount", new NullPointerException());
        }
        return category.afterDiscount(broughtDate, price);
    }

    void initCategoryInfo() throws ShoppingException {
        categories = new LinkedList<>();
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
