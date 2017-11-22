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

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//todo issue with not showing this activity on Up from Step Detail

public class RecipeDetail extends AppCompatActivity {
    private static final String TAG = RecipeDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //access clicked recipe information from the intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.containsKey("RecipeKey")) { //todo replace key with static
                RecipeTitleFragment titleFragment = new RecipeTitleFragment();
                RecipeStepListFragment stepFragment = new RecipeStepListFragment();
                titleFragment.setArguments(bundle);
                stepFragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.title_container, titleFragment)
                        .add(R.id.step_list_container, stepFragment)
                        .commit();
            }
        } else Log.i(TAG, "Bundle is null");

    }
}

