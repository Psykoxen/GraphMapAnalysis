import java.awt.Color;

public enum PanelColors {
    CITY(Color.GREEN),
    FUN(Color.BLUE),
    FOOD(Color.RED),
    DEPARTEMENTAL(Color.BLACK),
    NATIONAL(Color.BLACK),
    HIGHWAY(Color.BLACK);

    Color color = null;

    PanelColors (Color color)
    {
        this.color = color;
    }
}
