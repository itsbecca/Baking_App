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

public class StepDetail extends AppCompatActivity {
    private static final String TAG = StepDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        //access clicked recipe information from the intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.containsKey("StepKey")) { //todo replace key with static
                RecipeMediaFragment mediaFragment = new RecipeMediaFragment();
                RecipeStepFragment stepFragment = new RecipeStepFragment();

                mediaFragment.setArguments(bundle);
                stepFragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        //.add(R.id.media_container, mediaFragment) todo
                        .add(R.id.single_step_container, stepFragment)
                        .commit();
            }
        } else Log.i(TAG, "Extras are null");

    }
}

