package xyz.xesacat.rpg.util;

public class ArrayConverter {
    private String[] args;
    public ArrayConverter(String[] args) {
        this.args = args;
    }

    public int[] StringToInt() {
        int[] output = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            output[i] = Integer.parseInt(args[i]);
        }
        return output;
    }
}
