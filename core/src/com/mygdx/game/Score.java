package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Score {
    protected Vector2 pos;
    private int access = 1;//доступ (1,2,3 столб)
    private int count = 0;//счет
    private String strCount;
    private Texture[] img = new Texture[10];
    private Post.Pillars post;
    private int countNum, countI, countWidth, countLength; // Цифра, Промежуточное значение, Ширина картинки, Кол-во разрядов в числе

    Score() {
        pos = new Vector2(1, 363);
        for (int i = 0; i < 10; i++) {
            img[i] = new Texture("num\\" + i + ".png");
        }
    }

    public void render(SpriteBatch batch) {
        strCount = String.valueOf(count);// Переводим число(count) в String
        countLength = strCount.length();// Устанавливаем кол-во разрядов
        countI = count;// Присваеваем "промежуточному значению" значение числа(count)
        countWidth = 0;// Устанавливаем Х позицию для картинки
        for (int i = 0; i < strCount.length(); i++) { // Цикл проходится по каждому разряду
            countNum = (int) (countI / Math.pow(10, countLength - 1)); // countNum присваивается первая цифра промежуточного значения
            for (int l = 0; l < 10; l++) {// Цикл номеров картинок
                if (l == countNum) {// Проверяет совпадает ли цифра с номером
                    batch.draw(img[l], pos.x + countWidth, pos.y);// Прорисовывает картинку с соответствующим номером
                    countLength -= 1; // Отнимает от кол-ва разрядов 1
                    countI %= Math.pow(10, countLength);// Присваевает промежуточному значению count, но уже без цифры с наибольшим разрядом
                    countWidth += img[l].getWidth();// Устанавливаем Х позицию для следующей картинки
                }
            }
        }
    }

    public void update() {// Обновляет счет
        switch (access) {
            case 1:
                if (Post.column[0].pos.x + 45 < 150) {
                    count++;
                    access = 2;
                }
                break;
            case 2:
                if (Post.column[1].pos.x + 45 < 150) {
                    count++;
                    access = 3;
                }
                break;
            case 3:
                if (Post.column[2].pos.x + 45 < 150) {
                    count++;
                    access = 1;
                }
                break;
        }
    }

    public void recreate() {//Обновляет значения переменных
        pos = new Vector2(1, 363);
        access = 1;
        count = 0;
    }
}
