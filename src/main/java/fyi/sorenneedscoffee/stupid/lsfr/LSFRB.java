package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public class LSFRB extends LSFR {

  public LSFRB(int... seed) {
    super(seed);
  }

  @Override
  protected void transformRegister(int[] arr) {
    arr[0] = (register[0] + register[arr.length-2] + register[arr.length-1]) % 2;
  }
}
