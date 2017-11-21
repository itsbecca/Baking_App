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
package com.example.becca.bakingapp.org;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class RecipeStepClass implements Parcelable {
    private String shortDescription;
    private String description;
    private String videoUri;
    private String thumbnail;

    public RecipeStepClass(String shortDescription, String description,
                           String videoUri, String thumbnail) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUri = videoUri;
        this.thumbnail = thumbnail;
    }

    protected RecipeStepClass(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        videoUri = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoUri);
        dest.writeString(thumbnail);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<RecipeStepClass> CREATOR = new Creator<RecipeStepClass>() {
        @Override
        public RecipeStepClass createFromParcel(Parcel in) {
            return new RecipeStepClass(in);
        }

        @Override
        public RecipeStepClass[] newArray(int size) {
            return new RecipeStepClass[size];
        }
    };

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public Uri getVideoUri() {
        return Uri.parse(videoUri);
    }

    public Uri getThumbnail() {
        return Uri.parse(thumbnail);
    }
}

