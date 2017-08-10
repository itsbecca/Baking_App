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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{
    private ArrayList<RecipeClass> mRecipeList;
    //private final RecipListItemClickListener mOnClickListener;

//    public interface RecipListItemClickListener {
//        void onListItemClick(int clickedItemIndex);
//    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public RecipeAdapterViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.recipe_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO on click???
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public RecipeAdapter(ArrayList<RecipeClass> recipeList) {
        mRecipeList = recipeList;
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.list_item, parent, false);
//
//        return new RecipeAdapterViewHolder(view);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RecipeAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder recipeAdapterViewHolder, int position) {
        String recipeName = mRecipeList.get(position).getName();
        recipeAdapterViewHolder.getTextView().setText(recipeName);
    }



    @Override
    public int getItemCount() {
        if (mRecipeList == null) {
            return 0;
        }

        return mRecipeList.size();
    }

    public void setRecipeInfo(ArrayList<RecipeClass> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}