package com.alerman.advent2019.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) {
        String input = "10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL";
        Map<Ingredient, List<Ingredient>> recipes = new HashMap<>();
        for(String lineStr : input.split("\n"))
        {
            String[] splitLine = lineStr.split("=>");
            String[] result = splitLine[1].trim().split(" ");
            String[] ingredients = splitLine[0].split(",");
            Ingredient made = parseIngredient(result);
            List<Ingredient> ings = new ArrayList<>();
            for(int i=0; i<ingredients.length;i++)
            {
                ings.add(parseIngredient(ingredients[i].trim().split(" ")));
            }
            recipes.put(made, ings);

        }

        Ingredient fuel = new Ingredient(1, "FUEL");
        int totalOre = recurse(0, 1, fuel, recipes);

        System.out.println(recipes);
    }

    private static int recurse(int oreRequired,int needed,  Ingredient ing, Map<Ingredient, List<Ingredient>> recipes) {

        Ingredient makes = recipes.keySet().stream().filter(s -> s.getName().equals(ing.getName())).findAny().get();
        List<Ingredient> requirements = recipes.get(makes);
        int numMade = makes.getRequired();
        for(Ingredient req : requirements)
        {
            if(req.getName().equals("ORE"))
            {
                oreRequired =  numMade*(oreRequired + req.getRequired());
            }else{
                oreRequired =  numMade*(recurse(oreRequired,req.getRequired(), req, recipes));
            }
        }
        int made = numMade;
        int ore = oreRequired;
        while(made<needed)
        {
            made += numMade;
            oreRequired += ore;
        }
        return oreRequired;
    }

    private static Ingredient parseIngredient(String[] result) {
        Ingredient ing = new Ingredient(Integer.parseInt(result[0].trim()), result[1]);
        return ing;
    }
}

