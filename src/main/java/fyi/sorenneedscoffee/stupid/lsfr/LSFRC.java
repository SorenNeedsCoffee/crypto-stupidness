package fyi.sorenneedscoffee.stupid.lsfr;

public class LSFRC extends LSFR {

  public LSFRC(int... seed) {
    super(seed);
  }

  @Override
  protected void transformRegister(int[] arr) {
    arr[0] = (register[0] + register[arr.length - 1]) % 2;
  }
}
