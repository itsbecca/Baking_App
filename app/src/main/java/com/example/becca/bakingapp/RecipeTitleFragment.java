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
package com.example.becca.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.becca.bakingapp.org.RecipeClass;

import java.util.ArrayList;

public class RecipeTitleFragment extends Fragment {

    private static final String TAG = RecipeDetail.class.getSimpleName();
    TextView mNameView;
    TextView mServingsView;
    TextView mIngredientsView;

    public RecipeTitleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_recipe_title, container, false);

        mNameView = rootView.findViewById(R.id.recipe_name_detail);
        mServingsView = rootView.findViewById(R.id.recipe_servings);
        mIngredientsView = rootView.findViewById(R.id.recipe_ingredients);

        Bundle bundle = this.getArguments();
        RecipeClass currentRecipe = bundle.getParcelable("RecipeKey"); //TODO static
        String recipeName = currentRecipe.getName();
        String recipeServings = currentRecipe.getServings();
        ArrayList<String> ingredients = currentRecipe.getIngredientList();

        for (int i = 0; i < ingredients.size(); i++) {

        }

        mNameView.setText(recipeName);
        mServingsView.setText("Serves " + recipeServings);

        return rootView;
    }
}
