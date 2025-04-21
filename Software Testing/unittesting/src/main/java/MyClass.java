public class MyClass {
    public  float div (float x, float y)
    {
        if(y==0) throw new ArithmeticException("Division by zero");
        return x/y;
    }
}
