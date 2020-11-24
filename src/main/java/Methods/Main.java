package Methods;

public class Main
{
    public static void main(String[] args)
    {
        double trueValue = 3.3891;
        double inTrueValue = 4d;
        double error = 1.0E-15;
        BisectionMethod bisectionMethod = new BisectionMethod();
        bisectionMethod.bisectionMethod((double x) -> Math.exp(x-(Math.sqrt(x)))-x,trueValue,2,4,error);

        FixedPointMethod fixedPointMethod = new FixedPointMethod();
        fixedPointMethod.fixedPoint((double x) -> Math.exp(x-(Math.sqrt(x)))-x,inTrueValue,2,100,error);

        SecantMethod secantMethod = new SecantMethod();
        secantMethod.secantMethod((double x) -> Math.exp(x-(Math.sqrt(x)))-x, inTrueValue,2,4,error,100);









    }

}
