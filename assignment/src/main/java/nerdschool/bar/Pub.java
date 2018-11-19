package nerdschool.bar;

import nerdschool.bar.util.Ingredients;

import java.util.HashMap;
import java.util.Map;

public class Pub {


    public static final String ONE_BEER = "hansa";
    public static final String ONE_CIDER = "grans";
    public static final String A_PROPER_CIDER = "strongbow";
    public static final String GT = "gt";
    public static final String BACARDI_SPECIAL = "bacardi_special";

    Map<String, Integer> drinkStock;

    public Pub() {
        createDrinkStock();
    }

    public void createDrinkStock() {
        drinkStock = new HashMap();
        drinkStock.put(ONE_BEER, 5);
        drinkStock.put(ONE_CIDER, 5);
        drinkStock.put(A_PROPER_CIDER, 5);
        drinkStock.put(GT, 5);
        drinkStock.put(BACARDI_SPECIAL, 5);

    }

    public int computeCost(String drink, boolean student, int amount) throws Exception {
        verifyDrinksLimit(drink, amount);
        verifyDrinksInStock(drink, amount);

        int price = getPrice(drink);

        if (student) {
            price = getStudentDiscount(drink, price);
        }

        return price * amount;
    }

    private int getStudentDiscount(String drink, int price) {
        if (drink == ONE_CIDER || drink == ONE_BEER || drink == A_PROPER_CIDER) {
            price = price - price / 10;
        }
        return price;
    }

    private int getPrice(String drink) {
        int price = 0;
        if (drink.equals(ONE_BEER)) {
            price = 74;
        } else if (drink.equals(ONE_CIDER)) {
            price = 103;
        } else if (drink.equals(A_PROPER_CIDER)) price = 110;
        else if (drink.equals(GT)) {
            price = Ingredients.GIN.getPrice() + Ingredients.TONIC_WATER.getPrice() + Ingredients.GREEN_STUFF.getPrice();
        } else if (drink.equals(BACARDI_SPECIAL)) {
            price = Ingredients.GIN.getPrice() / 2 + Ingredients.RUN.getPrice()
                    + Ingredients.GRENADINE.getPrice() + Ingredients.LIME_JUICE.getPrice();
        }
        return price;
    }

    private void verifyDrinksLimit(String drink, int amount) {
        if (amount > 2 && (drink == GT || drink == BACARDI_SPECIAL)) {
            throw new RuntimeException("Too many drinks, max 2.");
        }
    }

    private void verifyDrinksInStock(String drink, int amount) throws Exception {
        if (!drinkStock.containsKey(drink)) {
            throw new Exception("No such drink exists");
        }
        Integer quantityInStock = drinkStock.get(drink);
        if (amount > quantityInStock) {
            throw new Exception("Ordered more drinks than there are in stock!");
        }
    }
}
