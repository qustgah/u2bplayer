package com.ijiaban.u2bplayer.common.bean;

import java.util.HashMap;
import java.util.Map; 

import com.iiijiaban.u2bplayer.R;
/*
 * Copyright 2013 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class Constants {

    public static final int SIMULATED_REFRESH_LENGTH = 4000;
    
    public static final String URL_SEARCHLIST = "https://www.googleapis.com/youtube/v3/search?part=snippet";
    
    public static final String URL_VIDEOCATEGORY = "https://www.googleapis.com/youtube/v3/videoCategories?part=snippet&regionCode=US";
    public static final String URL_VIDEOSBYVIDEOCATEGORY  =  "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=5";
    //https://www.googleapis.com/youtube/v3/videos?part=snippet%2Cstatistics
    public static final String URL_VIDEODETAIL = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2Cstatistics";
    
}
