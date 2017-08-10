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

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipeClass implements Parcelable{
    private String name;
    private String servings;
    private ArrayList<String> ingredientList;
    private ArrayList<RecipeStepClass> stepsList;

    public RecipeClass(String name, String servings, ArrayList<String> ingredientList,
                       ArrayList<RecipeStepClass> stepsList) {
        this.name = name;
        this.servings = servings;
        this.ingredientList = ingredientList;
        this.stepsList = stepsList;
    }

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public ArrayList<String> getIngredientList() {
        return ingredientList;
    }

    public ArrayList<RecipeStepClass> getStepsList() {
        return stepsList;
    }


    protected RecipeClass(Parcel in) {
        name = in.readString();
        servings = in.readString();
        ingredientList = in.readArrayList(null);
        stepsList = in.readArrayList(null);
    }

    public static final Creator<RecipeClass> CREATOR = new Creator<RecipeClass>() {
        @Override
        public RecipeClass createFromParcel(Parcel in) {
            return new RecipeClass(in);
        }

        @Override
        public RecipeClass[] newArray(int size) {
            return new RecipeClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeList(ingredientList);
        dest.writeList(stepsList);
    }
}
