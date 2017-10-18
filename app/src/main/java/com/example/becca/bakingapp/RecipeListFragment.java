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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.becca.bakingapp.utilities.NetworkUtils;
import com.example.becca.bakingapp.utilities.RecipeJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    private OnRecipeSelectedListener mCallback;
    ArrayList<RecipeClass> mRecipeQueryData;
    RecipeAdapter mRecipeAdapter;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnRecipeSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onRecipeSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        makeRecipeQuery();

        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_recipe_list, container, false);
        mRecipeAdapter = new RecipeAdapter(getContext(),mRecipeQueryData, mCallback);

        RecyclerView recyclerView = rootView.findViewById(R.id.recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mRecipeAdapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
                RecipeClass currentRecipe = mRecipeQueryData.get(position);
                mCallback.pullCurrentRecipeInfo(currentRecipe);
            }
        });

        return rootView;
    }

    public interface OnRecipeSelectedListener {
        void pullCurrentRecipeInfo(RecipeClass currentRecipe);
    }

    public void makeRecipeQuery() {
        URL recipeSearchUrl = NetworkUtils.getUrl();
        new RecipeQuery().execute(recipeSearchUrl);
    }

    public class RecipeQuery extends AsyncTask<URL, Void, ArrayList<RecipeClass>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<RecipeClass> doInBackground(URL... params) {
            URL searchUrl = params[0];

            try {
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                mRecipeQueryData = RecipeJsonUtils.getMovieDbStringsFromJson(jsonRecipeResponse);
                return mRecipeQueryData;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RecipeClass> recipeQueryData) {
            if (recipeQueryData != null) {
                mRecipeAdapter.setRecipeInfo(recipeQueryData);
            }
        }
    }
}
