package ru.betry;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class PopIt extends ApplicationAdapter {

    SpriteBatch batch;

    class Pop {
        int X;
        int Y;
        int radius;
        String color;

        public Pop (int x, int y, int radius, String color) {
            this.X = x;
            this.Y = y;
            this.radius = radius;
            this.color = color;
        }
    }

    ArrayList<Pop> popArrayList = new ArrayList<Pop>();
    String[] hex = new String[]{"#f92d33", "#fe6735", "#f5e15c", "#84e6c9", "#71b6e5", "#cd5cd5"};
    ShapeRenderer shapeRenderer;
    Sound sound;

    @Override
    public void create() {

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            popArrayList.add(
                    new Pop(
                            random.nextInt(1920),
                            random.nextInt(1080),
                            random.nextInt(50) + 30,
                            hex[random.nextInt(hex.length)]
                    )
            );
        }

        sound = Gdx.audio.newSound(Gdx.files.internal("popit.mp3"));

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        for (int i = 0; i < 10; i++) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(
                    popArrayList.get(i).X,
                    popArrayList.get(i).Y,
                    popArrayList.get(i).radius
            );
            shapeRenderer.setColor(Color.valueOf(popArrayList.get(i).color));
            shapeRenderer.end();
        }

        batch.end();

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) { return false; }
            @Override
            public boolean keyUp(int keycode) { return false; }
            @Override
            public boolean keyTyped(char character) { return false; }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    System.out.println("touchDown");
                    sound.play();
                    sound.resume();
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
            @Override
            public boolean mouseMoved(int screenX, int screenY) { return false; }
            @Override
            public boolean scrolled(int amount) { return false; }
        });
    }

    @Override
    public void dispose() {
    }
}
