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
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.becca.bakingapp.org.RecipeClass;
import com.example.becca.bakingapp.org.RecipeStepClass;

import java.util.ArrayList;

public class RecipeStepListFragment extends Fragment {

    private Context mContext;

    public RecipeStepListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_recipe_steps_list, container, false);

        //Retrieve steps from bundle
        Bundle bundle = this.getArguments();
        final RecipeClass currentRecipe = bundle.getParcelable("RecipeKey"); //TODO static
        final ArrayList<RecipeStepClass> recipeStepClasses = currentRecipe.getStepsList();

        //Create array to hold steps to use with adapter
        ArrayList<String> recipeStepsShort = new ArrayList<>();

        for (int i = 0; i < recipeStepClasses.size(); i++) {
            recipeStepsShort.add(recipeStepClasses.get(i).getShortDescription());
        }

        ListView listView = rootView.findViewById(R.id.steps_list);
        listView.setAdapter
                (new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.recipe_name_list,
                        recipeStepsShort)); //todo change id name for list_item View to make more general

        //Open detail for clicked step,
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                RecipeStepClass currentStep = recipeStepClasses.get(position);

                //send currentStep and it's number in the list to the new Activity
                Bundle stepBundle = new Bundle();
                stepBundle.putParcelable("StepKey", currentStep); //todo statics
                stepBundle.putInt("StepNumber", position);

                Intent intent = new Intent(mContext, StepDetail.class);
                intent.putExtras(stepBundle);

                mContext.startActivity(intent);
            }
        });

        return rootView;
    }
}
