package ru.betry;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello LibGDX");

        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.fullscreen = false;
        configuration.width = 1920;
        configuration.height = 1080;
        new LwjglApplication(new PopIt(), configuration);
    }
}
