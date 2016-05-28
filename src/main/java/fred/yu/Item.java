package fred.yu;

/**
 * Created by Fred on 16/5/28.
 */
public class Item {
    private String name;
    private int num;
    private double price;

    public Item(String name, int num, double price) {
        this.name = name;
        this.num = num;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
