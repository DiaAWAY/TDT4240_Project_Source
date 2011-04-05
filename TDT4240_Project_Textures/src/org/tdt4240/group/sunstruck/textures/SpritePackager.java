package org.tdt4240.group.sunstruck.textures;

import com.badlogic.gdx.imagepacker.TexturePacker;
import com.badlogic.gdx.imagepacker.TexturePacker.Settings;

public class SpritePackager {
	public static void main (String[] args) throws Exception {
        Settings settings = new Settings();
        settings.padding = 1;
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.incremental = true;
        TexturePacker.process(settings, "Sprites", "../TDT4240_Project/data");
    }
}
