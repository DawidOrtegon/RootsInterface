package Methods;
import java.util.ArrayList;

public class FixedPointMethod
{
    public double fixedPoint(ScalarFunction function,double inTrueValue, double xn, int maxIterations, double error)
    {
        if (error < 0 || error > 100)
        {
            throw new IllegalArgumentException("The error can nor be greater than 100 and less then 0");
        }

        System.out.printf("%15s %15s %15s %15s%n", "Iter", "xr","ea[%]","et[%]");
        double approximationError;
        double trueFunctionError;
        int iterator = 1;
        double resultRoot = 0;
        ArrayList<Double> valuesXr = new ArrayList<>();
        double [] xrApp = new double [maxIterations];

        do
        {
            xrApp[iterator+1] = xrApp[iterator-1] + xn;
            valuesXr.add(xrApp[iterator]);

            // CONDITIONS FOR THE ITERATIONS
            if(iterator > 1)
            {
                approximationError = appError(valuesXr.get(iterator-1), valuesXr.get(iterator-2));
                trueFunctionError = Math.abs((function.functionValue(inTrueValue)-xrApp[iterator]/function.functionValue(inTrueValue)))*100;
                System.out.printf("%15d %15g %15g %15g%n", iterator,xrApp[iterator] , approximationError , trueFunctionError);
            }

            if (iterator == 1)
            {
                approximationError = 0;
                trueFunctionError = Math.abs((function.functionValue(inTrueValue)-xrApp[iterator]/function.functionValue(inTrueValue)))*100;
                System.out.printf("%15d %15g %15g %15g%n", iterator,xrApp[iterator] , approximationError , trueFunctionError);
            }

            if(iterator == 1)
            {
                iterator ++;
                continue;
            }

            approximationError = appError(valuesXr.get(iterator-1), valuesXr.get(iterator-2));
            if(error > approximationError )
            {
                break;
            }

            iterator++;
        }
        while (iterator <= maxIterations-1);
        return resultRoot;
    }

    public double appError(double xNew, double xOld)
    {
        return Math.abs((xNew-xOld)/xNew) * 100;
    }

}

