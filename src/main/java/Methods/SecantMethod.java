package Methods;

import java.util.ArrayList;

public class SecantMethod
{
    public double secantMethod(ScalarFunction function, double inTrueValue, double xn1, double xn2, double error, int maxIterations)
    {
        // xn1 = xl
        // xn2 = xu
        if (error < 0 || error > 100)
        {
            throw new IllegalArgumentException("The error can nor be greater than 100 and less then 0");
        }

        System.out.printf("%15s %15s %15s %15s%n", "Iter", "xrApp","ea[%]","et[%]");
        double approximationError;
        double trueFunctionError;
        int iterator = 1;
        double resultRoot = 0;
        ArrayList<Double> valuesXr = new ArrayList<>();
        double [] xrApp = new double [maxIterations+1];
        xrApp[iterator-1] = xn2 - (function.functionValue(xn2)*(xn1-xn2))/(function.functionValue(xn1)-function.functionValue(xn2));
        xrApp[iterator] = xrApp[iterator] - (function.functionValue(xrApp[iterator])*(xn2-xrApp[iterator]))/(function.functionValue(xn2)-function.functionValue(xrApp[iterator]));

        do
        {
            xrApp[iterator + 1] = xrApp[iterator] - (function.functionValue(xrApp[iterator])*(xrApp[iterator-1]-xrApp[iterator]))/(function.functionValue(xrApp[iterator-1])-function.functionValue(xrApp[iterator]));
            valuesXr.add(xrApp[iterator+1]);

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
