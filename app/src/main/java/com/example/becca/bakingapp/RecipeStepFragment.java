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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.becca.bakingapp.org.RecipeClass;
import com.example.becca.bakingapp.org.RecipeStepClass;

import java.util.ArrayList;

public class RecipeStepFragment extends Fragment {

    public RecipeStepFragment() {
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
        final View rootView =  inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        //Retrieve steps from bundle
        Bundle bundle = this.getArguments();
        RecipeClass currentRecipe = bundle.getParcelable("RecipeKey"); //TODO static
        ArrayList<RecipeStepClass> recipeStepClasses = currentRecipe.getStepsList();

        //Create array to hold steps to use with adapter
        ArrayList<String> recipeSteps = new ArrayList<>();
        for (int i = 0; i < recipeStepClasses.size(); i++) {
            recipeSteps.add(recipeStepClasses.get(i).getShortDescription());
        }

        ListView listView = rootView.findViewById(R.id.steps_list);
        listView.setAdapter
                (new ArrayAdapter<>(getActivity(), R.layout.list_item, recipeSteps));

        return rootView;
    }
}
