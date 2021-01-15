package fyi.sorenneedscoffee.stupid;

public class LSFR {
    private int[] register;
    private boolean gen = false;

    public LSFR(int... bits){
        register = bits;
    }

    public int sample(){
        if (!gen) {
            gen = true;
            return register[register.length-1];
        }

        int[] temp = new int[register.length];

        if (register.length - 1 >= 0) System.arraycopy(register, 0, temp, 1, register.length - 1);

        temp[0] = (register[1] + register[temp.length-1]) % 2;

        register = temp;

        return temp[temp.length-1];
    }


    // thanks stackoverflow!
    // source: https://stackoverflow.com/questions/4895173/bitwise-multiply-and-add-in-java
    private static int bitwiseAdd(int n1, int n2) {
        int x = n1, y = n2;
        int xor, and, temp;
        and = x & y;
        xor = x ^ y;

        while (and != 0) {
            and <<= 1;
            temp = xor ^ and;
            and &= xor;
            xor = temp;
        }

        return xor;
    }
}
