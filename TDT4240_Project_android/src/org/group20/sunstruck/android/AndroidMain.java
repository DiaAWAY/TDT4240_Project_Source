package org.group20.sunstruck.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

import org.group20.sunstruck.Main;

public class AndroidMain extends AndroidApplication {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        	initialize(new Main(), false);
    }
}