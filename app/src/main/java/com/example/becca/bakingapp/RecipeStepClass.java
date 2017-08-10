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


import android.net.Uri;

public class RecipeStepClass {
    private String shortDescription;
    private String description;
    private Uri videoUri;
    private Uri thumbnail;

    public RecipeStepClass(String shortDescription, String description, Uri videoUri, Uri thumbnail) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUri = videoUri;
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

}

