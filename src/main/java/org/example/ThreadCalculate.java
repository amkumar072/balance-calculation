package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ThreadCalculate extends Thread  {
    String inputCurrency;
    float inputAmount;
    HashMap<String, Float> initialValue;

    ThreadCalculate(String inputCurrency, float inputAmount, HashMap<String, Float> initialValue) {
        this.inputCurrency = inputCurrency;
        this.inputAmount = inputAmount;
        this.initialValue = initialValue;
    }

    public void run() {
        calculate(inputCurrency, inputAmount, initialValue);
    }


    void calculate(String inputCurrency, float inputAmount, HashMap<String, Float> initialValue) {

        float remainingValue = 0;
        if (initialValue.containsKey(inputCurrency)) {
            Float initialCurrencyValue = initialValue.get(inputCurrency);
            if (initialCurrencyValue > 0) {
                remainingValue = initialCurrencyValue- inputAmount;
                initialValue.replace(inputCurrency, remainingValue);
            }
        }

        Iterator it = initialValue.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (Float.parseFloat(pair.getValue().toString())>0) {
                System.out.printf("%s", pair.getKey() + " ");
                System.out.printf("%.2f", pair.getValue());
                System.out.println();
            }
        }

        try {
            Thread.sleep(10000); // Sleep for 1 min
            calculate(inputCurrency, inputAmount, initialValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
