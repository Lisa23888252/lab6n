/**
 * Лабораторна робота №6
 * Тема: Наслідування та поліморфізм
 */

import java.util.*;

// Базовий клас "Овоч"
abstract class Vegetable {
    private String name;
    private double calories; // калорійність на 100 г

    public Vegetable(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public abstract String getType(); // Поліморфічний метод

    @Override
    public String toString() {
        return String.format("%s (%.2f калорій/100г, тип: %s)", name, calories, getType());
    }
}

// Клас-нащадок "Коренеплід"
class RootVegetable extends Vegetable {
    public RootVegetable(String name, double calories) {
        super(name, calories);
    }

    @Override
    public String getType() {
        return "Коренеплід";
    }
}

// Клас-нащадок "Листовий овоч"
class LeafyVegetable extends Vegetable {
    public LeafyVegetable(String name, double calories) {
        super(name, calories);
    }

    @Override
    public String getType() {
        return "Листовий овоч";
    }
}

// Клас-нащадок "Плідний овоч"
class FruitingVegetable extends Vegetable {
    public FruitingVegetable(String name, double calories) {
        super(name, calories);
    }

    @Override
    public String getType() {
        return "Плідний овоч";
    }
}

// Клас "Салат"
class Salad {
    private List<Vegetable> ingredients;

    public Salad() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Vegetable vegetable) {
        ingredients.add(vegetable);
    }

    public double calculateTotalCalories() {
        return ingredients.stream().mapToDouble(Vegetable::getCalories).sum();
    }

    public void sortIngredientsByCalories() {
        ingredients.sort(Comparator.comparingDouble(Vegetable::getCalories));
    }

    public List<Vegetable> findIngredientsByCaloriesRange(double minCalories, double maxCalories) {
        List<Vegetable> result = new ArrayList<>();
        for (Vegetable veg : ingredients) {
            if (veg.getCalories() >= minCalories && veg.getCalories() <= maxCalories) {
                result.add(veg);
            }
        }
        return result;
    }

    public void displayIngredients() {
        for (Vegetable veg : ingredients) {
            System.out.println(veg);
        }
    }
}

// Головний клас
public class Main {
    public static void main(String[] args) {
        try {
            Salad salad = new Salad();

            // Додавання інгредієнтів до салату
            salad.addIngredient(new RootVegetable("Морква", 35));
            salad.addIngredient(new LeafyVegetable("Салат Айсберг", 14));
            salad.addIngredient(new FruitingVegetable("Помідор", 20));

            System.out.println("Інгредієнти салату:");
            salad.displayIngredients();

            System.out.println("\nЗагальна калорійність салату: " + salad.calculateTotalCalories() + " калорій");

            System.out.println("\nСортування інгредієнтів за калорійністю:");
            salad.sortIngredientsByCalories();
            salad.displayIngredients();

            System.out.println("\nПошук інгредієнтів з калорійністю від 15 до 40:");
            List<Vegetable> found = salad.findIngredientsByCaloriesRange(15, 40);
            for (Vegetable veg : found) {
                System.out.println(veg);
            }
        } catch (Exception e) {
            System.err.println("Сталася помилка: " + e.getMessage());
        }
    }
}
