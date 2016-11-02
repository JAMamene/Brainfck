package level2.constants;

/**
 * enum for the constants, mainly the sizes of the data.
 */
public enum Sizes {

    //The boundaries of data used in bfck
    MINMEMORYSIZE(0),
    MAXMEMORYSIZE(29999),
    MASK(127),
    MINDATASIZE(-127),
    MAXDATASIZE(127),

    // The square size that represents one instruction on images
    SQUARE_SIZE(3); // here, each instruction is represented by a 3*3 square


    private int val;

    Sizes(int val) {
        this.val = val;
    }

    // Get the val of the enum
    public int get() {
        return val;
    }

}
