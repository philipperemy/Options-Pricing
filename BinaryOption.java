package src;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class BinaryOption
{

    public static double computePremium(double prob, double payoff, double fees)
    {
        return (-fees + prob * fees - prob * payoff) / (1 - prob); 
    }
    
    
    public static void main(String[] args)
    {
    }
    
    public static double computeProbability(int deep, int remainingLength)
    {
        double proba = 0.0;
        
        BigInteger accessiblePathsCardinal = BigInteger.ZERO;
        List<Integer> possibleIntegersP = findPossibleP(deep, remainingLength);
        
        //System.out.println("_____ accessible paths are _____");
        for(Integer pInteger : possibleIntegersP)
        {
            accessiblePathsCardinal = accessiblePathsCardinal.add(computeBinomialCoef(pInteger.intValue(), remainingLength));
            //System.out.println("(" + pInteger.intValue() + ", " + remainingLength + ")");
        }
        
        //System.out.println("_____ ALL paths are _____");
        BigInteger allPathsCardinal = BigInteger.ZERO;
        for(int k=0; k <= remainingLength; k++)
        {
            allPathsCardinal = allPathsCardinal.add(computeBinomialCoef(k, remainingLength));
            //System.out.println("(" + k + ", " + remainingLength + ")");
        }


        proba = new BigDecimal(accessiblePathsCardinal).divide(new BigDecimal(allPathsCardinal), MathContext.DECIMAL128).doubleValue();
        
        return proba;
        
    }
    
    public static BigInteger computeBinomialCoef(int k, int n)
    {
        BigInteger num = factorial(n);
        BigInteger denum = factorial(k).multiply(factorial(n-k));
        return num.divide(denum);
    }
    
    public static BigInteger factorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
    
    public static List<Integer> findPossibleP(int deep, int remainingLength)
    {
        List<Integer> possibleKIntegers = new ArrayList<Integer>();
        
        for(int p=0; p<=remainingLength; p++)
        {
            if(p>(remainingLength-p)+deep)
            {
                possibleKIntegers.add(p);
            }
        }
        
        
        return possibleKIntegers;
    }

}
