package org.tdt4240.group.sunstruck.textures;

import com.badlogic.gdx.imagepacker.TexturePacker;
import com.badlogic.gdx.imagepacker.TexturePacker.Settings;

public class BackgroundPackager {
	public static void main (String[] args) throws Exception {
        Settings settings = new Settings();
        settings.padding = 2;
        settings.maxWidth = 4096;
        settings.maxHeight = 4096;
        settings.incremental = true;
        TexturePacker.process(settings, "Backgrounds", "Sheets");
    }
}
