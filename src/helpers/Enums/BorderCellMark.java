package helpers.Enums;

public enum BorderCellMark {
     PlayerOne(coloredStringBackground(" 1 ", 1)),
     PlayerTwo(coloredStringBackground(" 2 ", 2)),
     Empty(" * ");

    public final String mark;

    private BorderCellMark(String mark) {
        this.mark = mark;
    }
    private static String coloredStringBackground(String x, int colorId) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        if (colorId == 1) {
            return ANSI_RED_BACKGROUND + x + ANSI_RESET;
        } else {
            return ANSI_GREEN_BACKGROUND + x + ANSI_RESET;
        }
    }
}
