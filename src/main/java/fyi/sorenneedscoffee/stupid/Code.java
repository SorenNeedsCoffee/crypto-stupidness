package fyi.sorenneedscoffee.stupid;

import java.util.Arrays;

public class Code {
    public final int[] bits;

    public Code(int... bits) {
        this.bits = bits;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bits);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Code)) return false;

        return Arrays.equals(bits, ((Code) obj).bits);
    }
}
