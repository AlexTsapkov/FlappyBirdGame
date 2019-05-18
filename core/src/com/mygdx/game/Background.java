package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    class BGPicture {
        private Texture img;    // Картинка фона
        protected Vector2 pos;    // Его положение

        BGPicture(Vector2 pos) {    // Конструктор инициализирует переменные
            img = new Texture("un_hills.jpg");
            this.pos = pos;
        }
    }

    private BGPicture[] ground = new BGPicture[2];  // Массив фонов.
    private int speed;  // Скорость движения фона.

    Background() {
        ground[0] = new BGPicture(new Vector2(0, -312));   // Фон первого элемента массива.
        ground[1] = new BGPicture(new Vector2(1024, -312));    // Фон второго элемента массива.
        speed = 4;
    }

    public void render(SpriteBatch batch) {  //  Прорисовывает фоны
        for (int i = 0; i < ground.length; i++) {
            batch.draw(ground[i].img, ground[i].pos.x, ground[i].pos.y);    // Прорисовывает фон
        }
    }

    public void update() {   // Меняет положение фонов
        for (int i = 0; i < ground.length; i++) {   // Цикл двигает фон.
            ground[i].pos.x -= speed;
            if (ground[i].pos.x == -1024) {    // Если фон полностью сдвинулся перемещает его.
                ground[i].pos.x = 1024;
            }
        }
    }

    public void recreate() { //Обновляет значения переменных
        ground[0] = new BGPicture(new Vector2(0, -312));
        ground[1] = new BGPicture(new Vector2(1024, -312));
    }
}
