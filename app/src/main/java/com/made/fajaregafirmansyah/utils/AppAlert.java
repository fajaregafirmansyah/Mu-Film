package com.made.fajaregafirmansyah.utils;

import android.app.Activity;

import com.made.fajaregafirmansyah.R;
import com.tapadoo.alerter.Alerter;

public class AppAlert {

    private final Activity activity;

    public AppAlert(Activity activity) {
        this.activity = activity;
    }

    public void authAlert(String message) {
        Alerter.create(activity)
                .setTitle("Informasi")
                .setText(message)
                .setBackgroundColorInt(activity.getResources().getColor(R.color.colorAccent))
                .show();
    }

}
