package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public class LSFRFibonacci extends LSFR {

  public LSFRFibonacci(int... seed) {
    super(seed);
  }

  @Override
  protected void transformRegister(int[] arr) {
    arr[0] = (register[register.length-1] ^ register[register.length-2]);
  }
}
