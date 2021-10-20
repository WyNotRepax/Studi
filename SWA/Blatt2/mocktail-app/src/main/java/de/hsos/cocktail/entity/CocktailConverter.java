package de.hsos.cocktail.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

@ApplicationScoped
public class CocktailConverter {
    public static final String ID_KEY = "idDrink";
    public static final String NAME_KEY = "strDrink";
    public static final String INSTRUCTION_KEY = "strInstructionsDE";
    public static final String INSTRUCTION_KEY_FALLBACK = "strInstructions";
    public static final String INGREDIENT_KEY = "strIngredient";
    public static final String MEASURE_KEY = "strMeasure";
    public static final int MAX_INGREDIENTS = 15;

    public List<Cocktail> convert(JsonObject json) {
        JsonArray drinks = json.getJsonArray("drinks");
        ArrayList<Cocktail> cocktails = new ArrayList<>();
        for (int i = 0; i < drinks.size(); i++) {
            cocktails.add(this.convertDrink(drinks.getJsonObject(i)));
        }
        return cocktails;
    }

    private Cocktail convertDrink(JsonObject drink) {
        return new Cocktail(getName(drink), getId(drink), getInstructions(drink), getIngredients(drink));
    }

    private String getName(JsonObject drink) {
        return this.getString(drink.get(NAME_KEY));
    }

    private long getId(JsonObject drink) {
        return Long.valueOf(this.getString(drink.get(ID_KEY)));
    }

    private String getString(JsonValue value) {
        if (value instanceof JsonString) {
            return ((JsonString) value).getString();
        }
        return "";
    }

    private String getInstructions(JsonObject drink) {

        String instructionString = this.getString(drink.get(INSTRUCTION_KEY));
        if (instructionString.length() > 0) {
            return instructionString;
        }
        return this.getString(drink.get(INSTRUCTION_KEY_FALLBACK));
    }

    private String getIngredients(JsonObject drink) {
        String[] ingredientStrings = new String[15];
        for (int ingredientIndex = 0; ingredientIndex < MAX_INGREDIENTS; ingredientIndex++) {
            String ingredient = this.getString(drink.get(INGREDIENT_KEY + String.valueOf(ingredientIndex + 1)));
            String measure = this.getString(drink.get(MEASURE_KEY + String.valueOf(ingredientIndex + 1)));
            if (ingredient.length() > 0) {
                ingredientStrings[ingredientIndex] = ingredient + (measure.length() > 0 ? (": " + measure) : "");
            }
        }
        return Arrays.stream(ingredientStrings).filter(v -> v != null).collect(Collectors.joining(", "));
    }
}
