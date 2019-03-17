package com.ecclissi.calculadora;

import android.util.Log;

public class Calculae {

    public String calc(String mcalc){
        int wio = whereIsOpera(mcalc);
        String result = "";
        try {
            if (wio > 0) {
                Log.d("DEBUG", mcalc);
                int num1 = Integer.parseInt(mcalc.substring(0, wio));
                int num2 = Integer.parseInt(mcalc.substring(wio + 1));
                if (mcalc.charAt(wio) == '+') result = "" + (num1 + num2);
                else if (mcalc.charAt(wio) == '-') result = "" + (num1 - num2);
                else if (mcalc.charAt(wio) == '/') result = "" + Math.round(num1 / num2);
                else result = "" + Math.round(num1 * num2);
            }
        } catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        return result;
    }
    private int whereIsOpera(String mcalc){
        char[] numbers = mcalc.toCharArray();
        for (int i =0;i<numbers.length;i++) {
            if (numbers[i] == '+' ||
                    numbers[i] == '-' ||
                    numbers[i] == '/' ||
                    numbers[i] == '*')
                return i;
        }
        return -1;
    }
}
