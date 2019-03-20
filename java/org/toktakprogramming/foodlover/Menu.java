package org.toktakprogramming.foodlover;

public class Menu {
    private String menu_name;
    private String menu_price;

    public Menu(String menu_name, String menu_price) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMenu_price() {
        return menu_price;
    }
}
