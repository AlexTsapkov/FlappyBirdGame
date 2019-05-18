package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private Texture imgDown;    // Картинка птички
    private Texture imgUp;
    protected Vector2 pos;    // Положение птички
    private float jump;
    private float h;
    private final float G = -0.5f;
    private int birdflap = 0;

    Bird() {    // Конструктор инициализирует переменные
        imgDown = new Texture("bluebird-downflap.png");
        imgUp = new Texture("bluebird-upflap.png");
        pos = new Vector2(150, 200);
        jump = 8;
        h = 0;
    }

    public void render(SpriteBatch batch) {  // Прорисовывает птичку
        if (birdflap == 1) {
            batch.draw(imgDown, pos.x, pos.y);
        } else {
            batch.draw(imgUp, pos.x, pos.y);
        }
    }

    public void update() {   // Именение положения птички
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {     // При нажатии на пробел птичка подпрыгивает
            for (int i = 0; i < jump; i++) pos.y += 1;
            h = 0;
            birdflap = 2;
        } else {    // Есди на пробел не нажимать птичка будет падать
            h += G;
            pos.y += h;
            birdflap = 1;
        }
    }

    public void recreate() { //Обновляет значения переменных
        pos = new Vector2(150, 200);
        h = 0;
    }
}
