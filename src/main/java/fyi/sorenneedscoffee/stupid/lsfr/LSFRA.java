package fyi.sorenneedscoffee.stupid.lsfr;

import java.util.Arrays;

public class LSFRA extends LSFR {

  public LSFRA(int... seed) {
    super(seed);
  }

  @Override
  protected void transformRegister(int[] arr) {
    arr[0] = (register[1] + register[arr.length - 1]) % 2;
  }
}
