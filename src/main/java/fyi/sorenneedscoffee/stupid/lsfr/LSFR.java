package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public abstract class LSFR {
  public final int[] seed;
  protected int[] register;

  public LSFR(int... seed) {
    this.seed = seed;
  }

  public int sample() {
    // for some reason the lsfr returns the last bit in the key
    if (register == null) {
      register = Arrays.copyOf(seed, seed.length);
      return register[register.length - 1];
    }

    // create temp array and copy register bits into it shifted to the right
    var temp = new int[register.length];
    if (register.length - 1 >= 0) {
      System.arraycopy(register, 0, temp, 1, register.length - 1);
    }

    transformRegister(temp);

    register = temp;

    return temp[temp.length - 1];
  }

  protected abstract void transformRegister(int[] arr);

  public int[] getRegister() {
    return Arrays.copyOf(register, register.length);
  }
}
