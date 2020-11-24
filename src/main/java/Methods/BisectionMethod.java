package Methods;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BisectionMethod
{
    public double bisectionMethod(ScalarFunction function, double trueValue, double xl, double xu, double error)
    {
        // Condition for the error.
        if (error < 0 || error > 100)
        {
            throw new IllegalArgumentException("The error can not be greater than 100 and less than 0");
        }

        // Validation for the condition in the method.

        if(function.functionValue(xl) * function.functionValue(xu) > 0)
        {
            throw new IllegalArgumentException("In this points there are no roots for the function");
        }
        else if (function.functionValue(xl) * function.functionValue(xu) == 0)
        {
            throw new IllegalArgumentException("This points are already roots of the  function");
        }

        // System.out.println("Iter\t\txl\t\t\txu\t\t\txr\t\t\tea[%]");
        System.out.printf("%15s %15s %15s %15s %15s %15s%n", "Iter", "xl", "xu", "xr", "ea[%]", "et[%]");
        double xr;
        // Error of the values of the xr when it changes.
        double approximationError;
        // Error with the value of the function itself.
        double trueFunctionError;
        int iterator = 1;
        double resultRoot = 0;
        ArrayList<Double> valuesXr = new ArrayList<>();


        do
        {
            // In case of need a format for the numbers.
            DecimalFormat numFormat = new DecimalFormat("##.##");
            xr = (xl+xu)/2;
            valuesXr.add(xr);

            // ITERATOR CONDITIONS: If Iterator is only 1 is because the root is already found.
            if (iterator == 1)
            {
                approximationError = 0;
                // trueFunctionError = Math.abs((trueValue()-function.functionValue(xr))/trueValue())*100;
                trueFunctionError = Math.abs((trueValue-(xr))/trueValue)*100;
                System.out.printf("%15d %15g %15g %15g %15g %15g%n", iterator, xl, xu, xr, approximationError , trueFunctionError);
                // System.out.println(numFormat.format(iterator) + "\t\t\t" + numFormat.format(xl) + "\t\t\t" + numFormat.format(xu) + "\t\t\t" + numFormat.format(xr) + "\t\t\t" + numFormat.format(approximationError));
            }

            // If the iterator is greater than 1 there are calculations and it has to be printed all.
            if (iterator > 1)
            {
                approximationError = appError(valuesXr.get(iterator-1), valuesXr.get(iterator-2));
                // trueFunctionError = Math.abs((trueValue()-function.functionValue(xr))/trueValue())*100;
                trueFunctionError = Math.abs((trueValue-(xr))/trueValue)*100;
                System.out.printf("%15d %15g %15g %15g %15g %15g%n", iterator, xl, xu, xr, approximationError, trueFunctionError);
                // System.out.println(numFormat.format(iterator) + "\t\t\t" + numFormat.format(xl) + "\t\t\t" + numFormat.format(xu) + "\t\t\t" + numFormat.format(xr) + "\t\t\t" + numFormat.format(approximationError));
            }


            // ALGORITHM CONDITIONS
            if(function.functionValue(xl) * function.functionValue(xr) < 0)
            {
                xu = xr;
            }

            else if (function.functionValue(xl) * function.functionValue(xr) > 0)
            {
                xl = xr;
            }

            else if(function.functionValue(xl) * function.functionValue(xr) == 0 || function.functionValue(xr) * function.functionValue(xu) == 0)
            {
                resultRoot = xr;
            }

            if(iterator == 1)
            {
                iterator ++;
                continue;
            }

            // CONDITION FOR THE ERROR AND TO STOP THE PROGRAM.
            approximationError = appError(valuesXr.get(iterator-1), valuesXr.get(iterator-2));
            if(error > approximationError)
            {
                break;
            }
            iterator++;

        }
        while(function.functionValue(xl) * function.functionValue(xu) < 0);
        String toStringResult = String.format("%.3g", resultRoot);
        System.out.println("The approximate root is:" +toStringResult);
        return resultRoot;

    }

    public double appError(double xNew, double xOld)
    {
        return Math.abs((xNew-xOld)/xNew) * 100;
    }
}
