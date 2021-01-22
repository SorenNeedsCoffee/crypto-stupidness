package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public class LSFRFibonacci {

  public final int[] seed;
  private int[] register;

  public LSFRFibonacci(int... seed) {
    this.seed = seed;
  }

  public int sample() {
    // for some reason the lsfr returns the last bit in the key
    if (register == null) {
      register = Arrays.copyOf(seed, seed.length);
      return register[register.length - 1];
    }

    // create temp array and copy register bits into it shifted to the right
    int[] temp = new int[register.length];
      if (register.length - 1 >= 0) {
          System.arraycopy(register, 0, temp, 1, register.length - 1);
      }

    temp[0] = (register[register.length-1] ^ register[register.length-2]);

    register = temp;

    return temp[temp.length - 1];
  }

  public int[] getRegister() {
    return Arrays.copyOf(register, register.length);
  }
}
