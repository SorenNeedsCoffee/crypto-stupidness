package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public class LSFRB extends LSFR {

  public LSFRB(int... seed) {
    super(seed);
  }

  @Override
  protected void transformRegister(int[] arr) {
    arr[0] = (arr[0] + arr[arr.length-2] + arr[arr.length-1]) % 2;
  }
}
