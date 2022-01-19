/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pandaq.uires.widget.cardview;

import android.content.Context;

/**
 * Interface for platform specific CardView implementations.
 */
interface PandaCardViewImpl {
    void initialize(PandaCardViewDelegate cardView, Context context, int backgroundColor, float radius,
                    float elevation, float maxElevation, int startShadowColor, int endShadowColor);

    void setRadius(PandaCardViewDelegate cardView, float radius);

    float getRadius(PandaCardViewDelegate cardView);

    void setElevation(PandaCardViewDelegate cardView, float elevation);

    float getElevation(PandaCardViewDelegate cardView);

    void initStatic();

    void setMaxElevation(PandaCardViewDelegate cardView, float maxElevation);

    float getMaxElevation(PandaCardViewDelegate cardView);

    float getMinWidth(PandaCardViewDelegate cardView);

    float getMinHeight(PandaCardViewDelegate cardView);

    void updatePadding(PandaCardViewDelegate cardView);

    void onCompatPaddingChanged(PandaCardViewDelegate cardView);

    void onPreventCornerOverlapChanged(PandaCardViewDelegate cardView);

    void setBackgroundColor(PandaCardViewDelegate cardView, int color);
}
