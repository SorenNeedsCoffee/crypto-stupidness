package fyi.sorenneedscoffee.stupid;

public class LSFR {
    private int[] register;
    private boolean gen = false;

    public LSFR(int... bits){
        register = bits;
    }

    public int sample(){
        // for some reason the lsfr returns the last bit in the key
        if (!gen) {
            gen = true;
            return register[register.length-1];
        }

        // create temp array and copy register bits into it shifted to the right
        int[] temp = new int[register.length];
        if (register.length - 1 >= 0) System.arraycopy(register, 0, temp, 1, register.length - 1);

        temp[0] = (register[1] + register[temp.length-1]) % 2;

        register = temp;

        return temp[temp.length-1];
    }
}
