package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background bg;
    private Bird bird;
    private Post post;
    private Score score;
    private boolean gameover, again;
    private Texture imgGameover, imgGameoverBg, imgRestart;
    private int restart = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Background();
        bird = new Bird();
        post = new Post();
        score = new Score();
        gameover = false;
        again = false;
        imgGameover = new Texture("gameover.png");
        imgGameoverBg = new Texture("un_hills.jpg");
        imgRestart = new Texture("message.png");
    }

    @Override
    public void render() {// Отрисовывает игру
        gameoverMethot();
        if (!gameover) {
            update();
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            bg.render(batch);
            post.render(batch);
            bird.render(batch);
            score.render(batch);
        } else {
            restart++;
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(imgGameoverBg, 0, -312);
            score.render(batch);
            if (restart > 90) {
                batch.draw(imgRestart, 208, 61);
                again = true;
                restart--;
            } else {
                batch.draw(imgGameover, 204, 179);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void update() { // Вызывает методы update всех классов
        bg.update();
        bird.update();
        post.update();
        score.update();
    }

    public void gameoverMethot() {// Метод проверяет не столкнулась ли птичка
        for (int i = 0; i < Post.column.length; i++) {
            if (bird.pos.x > Post.column[i].pos.x - 34 && bird.pos.x < Post.column[i].pos.x + 45) {
                if (!Post.column[i].emptySpace.contains(bird.pos)) {
                    gameover = true;
                    break;
                }
            }
        }

        if (bird.pos.y <= 0 || bird.pos.y >= 376) {
            gameover = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameover && again) {
            recreate();
        }
    }

    public void recreate() {//Обновляет значения переменных
        bg.recreate();
        bird.recreate();
        post.recreate();
        score.recreate();
        gameover = false;
        again = false;
        restart = 0;
    }
}
