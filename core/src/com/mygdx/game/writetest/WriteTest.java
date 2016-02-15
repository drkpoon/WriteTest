package com.mygdx.game.writetest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Calendar;

public class WriteTest extends ApplicationAdapter {
    SpriteBatch batch;
	Texture img;

    public void setWritePermission(Boolean writePermission) {
        this.writePermission = writePermission;
    }

    private Boolean writePermission = true;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        if (writePermission) {
            FileHandle file = Gdx.files.external(Calendar.getInstance().getTime().toString() + ".txt");
            file.writeString("My god, it's full of stars", false);
        }
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

    @Override
    public void dispose() {
        img.dispose();
        batch.dispose();
    }
}
