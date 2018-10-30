package nerdschool.bar.util;

public enum Ingredients {

    TONIC_WATER(20), GIN(85),
    RUN(65),
    GRENADINE(10),
    LIME_JUICE(10),
    GREEN_STUFF(10);

    int price;

    Ingredients(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

