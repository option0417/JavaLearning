package tw.com.wd.servlet.model;

public class NumberModel {
    private int a;
    private int b;
    private int sum;

    public NumberModel(int a, int b) {
        super();
        this.a = a;
        this.b = b;
        this.sum = a + b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getSum() {
        return sum;
    }
}
