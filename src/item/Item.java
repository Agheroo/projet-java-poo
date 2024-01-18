package item;

import entity.Player;

public abstract class Item {
    public String type;
    public String name;

    public abstract void useItem(Player player);
}
