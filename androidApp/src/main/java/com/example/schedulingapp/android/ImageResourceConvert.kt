package com.example.schedulingapp.android

import com.example.schedulingapp.ImageResource

fun getImageAssetName(profileImage: ImageResource): Int {
    return when (profileImage) {
        ImageResource.PROFILE_IMAGE -> R.drawable.profile
        ImageResource.LOGO -> R.drawable.tree__2_
        ImageResource.CLOCK_ICON -> R.drawable.clock
        ImageResource.MEETING_ICON -> R.drawable.record_meet_meeting_svgrepo_com
        ImageResource.PLANET_ICON -> R.drawable.planet_earth_world_earth_svgrepo_com
        ImageResource.CALENDAR_ICON -> R.drawable.calendar_svgrepo_com
        ImageResource.EMAIL_ICON -> R.drawable.email_1_svgrepo_com
    }
}