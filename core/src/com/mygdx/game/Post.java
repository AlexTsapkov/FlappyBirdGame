package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Post {
    class Pillars { //
        protected Vector2 pos;    //Положение столба
        private int speed;  //Скорость движения столба
        private int offset;
        protected Rectangle emptySpace;   //Пространство между труб

        Pillars(Vector2 pos) {   //Обявляет переменные
            this.pos = pos;
            speed = 2;
            offset = new Random().nextInt(200);
            emptySpace = new Rectangle(pos.x - 33, pos.y + 300 - offset, 45 + 33, betweenDistance - 23);//Пространство между столбами, в которое может пролететь птичка
        }

        public void update() {   //Обновляет положение столба и emptySpace
            for (int i = 0; i < speed; i++) pos.x -= 1;
            if (pos.x < -45) {
                pos.x = 600;
                offset = new Random().nextInt(200);
            }
            emptySpace = new Rectangle(pos.x - 33, pos.y + 300 - offset, 45 + 33, betweenDistance - 23);
        }
    }

    public static Pillars[] column = new Pillars[3]; //Массив столбов
    private Texture imgLow, imgUp;     //Картинки нижнего и верхнего столба
    private int betweenDistance;    //Расстояние между нижним и верхним столбом
    private int startPos = 400;    //Стартовая позиция столба

    Post() {    //Конструктор устанавливает значения для каждого элемента(столба) массива
        imgLow = new Texture("lower-post.jpg");
        imgUp = new Texture("upper-post.jpg");
        betweenDistance = 150;
        for (int i = 0; i < column.length; i++) {
            column[i] = new Pillars(new Vector2(startPos, -75));
            startPos += 215;
        }
    }

    public void render(SpriteBatch batch) {  //Метод прорисовывает нижний столб и соответсвующий ему верхний
        for (int i = 0; i < column.length; i++) {
            batch.draw(imgLow, column[i].pos.x, column[i].pos.y - column[i].offset);
            batch.draw(imgUp, column[i].pos.x, column[i].pos.y + 300 + betweenDistance - column[i].offset);
        }
    }

    public void update() {   //Обновляет положение нижнего столба
        for (int i = 0; i < column.length; i++) {
            column[i].update();
        }
    }

    public void recreate() { //Обновляет значения переменных
        int startPos = 400;
        for (int i = 0; i < column.length; i++) {
            column[i] = new Pillars(new Vector2(startPos, -75));
            startPos += 215;
        }
    }
}
