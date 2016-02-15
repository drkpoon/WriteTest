package com.mygdx.game.writetest;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	public static final int REQUEST_CODE = 300;
    public static final String ANDROID_PERMISSION_WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private WriteTest writeTest;

    @Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        writeTest = new WriteTest();
        if (! checkWritePermission()){
            requestWritePermission();
        }
        initialize(writeTest, config);
	}

    public boolean checkWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return(checkSelfPermission(ANDROID_PERMISSION_WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED);
        }else {
            return true;
        }
    }

    public void requestWritePermission() {
        String[] permission = {ANDROID_PERMISSION_WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE){
            writeTest.setWritePermission( grantResults[0] == PackageManager.PERMISSION_GRANTED );
        }
    }
}