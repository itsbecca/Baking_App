/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.becca.bakingapp.utilities;

import android.net.Uri;

import com.example.becca.bakingapp.RecipeClass;
import com.example.becca.bakingapp.RecipeStepClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeJsonUtils {

    public static ArrayList getMovieDbStringsFromJson(String recipeJsonString)
            throws JSONException {
        final String RECIPE_NAME = "name";
        final String INGREDIENTS = "ingredients";
        final String INGREDIENT_QUANTITY = "quantity";
        final String INGREDIENT_MEASUREMENT = "measure";
        final String INGREDIENT_NAME= "ingredient";
        final String RECIPE_STEPS = "steps";
        final String STEP_SHORT_DESCRIPTION = "shortDescription";
        final String STEP_DESCRIPTION = "description";
        final String STEP_VIDEO = "videoURL";
        final String STEP_THUMB = "thumbnailURL";
        final String SERVINGS = "servings";
        //constants

        ArrayList<RecipeClass> recipes = new ArrayList<>();

        JSONArray reader = new JSONArray(recipeJsonString);

        for (int i = 0; i < reader.length(); i++) { //get the individual recipes
            JSONObject recipesObj = reader.getJSONObject(i);

            String name = recipesObj.getString(RECIPE_NAME);
            String servings = String.valueOf(recipesObj.getInt(SERVINGS));

            JSONArray ingredients = recipesObj.getJSONArray(INGREDIENTS); //get the individual ingredient lines
            ArrayList<String> ingredientList = new ArrayList<>();
            for (int j = 0; j < ingredients.length(); j++) {
                JSONObject ingredient = ingredients.getJSONObject(j);
                String quantity = String.valueOf(ingredient.getInt(INGREDIENT_QUANTITY));
                String measurement = ingredient.getString(INGREDIENT_MEASUREMENT);
                String ingredientName = ingredient.getString(INGREDIENT_NAME);
                ingredientList.add(quantity + " " + measurement + " " + ingredientName);
                //TODO measurements are caps and have no plurals, And what is 1K Nutella?
            }

            JSONArray recipeSteps = recipesObj.getJSONArray(RECIPE_STEPS);
            ArrayList<RecipeStepClass> stepList = new ArrayList<>();
            for (int k = 0; k < recipeSteps.length(); k++) { //get the individual recipe steps
                JSONObject recipeStep = recipeSteps.getJSONObject(k);
                String shortDescription = recipeStep.getString(STEP_SHORT_DESCRIPTION);
                String description = recipeStep.getString(STEP_DESCRIPTION);
                Uri videoUri = Uri.parse(STEP_VIDEO);
                Uri thumbnail = Uri.parse(STEP_THUMB);
            }

            recipes.add(new RecipeClass(name, servings, ingredientList, stepList));
        }

        return recipes;

    }
}