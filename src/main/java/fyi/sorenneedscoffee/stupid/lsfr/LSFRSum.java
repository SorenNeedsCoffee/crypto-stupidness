package fyi.sorenneedscoffee.stupid.lsfr;

public class LSFRSum extends LSFR {

  private final LSFR3bit lsfr3bit;
  private final LSFR5bit lsfr5bit;

  public LSFRSum(int... seed) {
    this.lsfr3bit = new LSFR3bit(seed[0], seed[1], 1);
    this.lsfr5bit = new LSFR5bit(seed[2], seed[3], seed[4], seed[5], 1);
  }

  @Override
  public int sample() {
    return (lsfr3bit.sample() ^ lsfr5bit.sample()) % 2;
  }

  @Override
  protected void transformRegister(int[] arr) {}

  private static class LSFR3bit extends LSFR {

    protected LSFR3bit(int... seed){
      super(seed);
    }

    @Override
    protected void transformRegister(int[] arr) {
      arr[0] = (arr[2] + arr[1]) % 2;
    }
  }

  private static class LSFR5bit extends LSFR {

    protected LSFR5bit(int... seed){
      super(seed);
    }

    @Override
    protected void transformRegister(int[] arr) {
      arr[0] = (arr[1] + arr[3] + arr[4]) % 2;
    }
  }
}
