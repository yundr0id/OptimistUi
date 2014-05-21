package com.yundroid.optimistui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private View decorView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		makeFullScreen();
		setContentView(R.layout.activity_main);
		
		keepItAwake();
		hideNavBar();		
	}

	private void makeFullScreen(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	private void keepItAwake(){
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	private void hideNavBar(){
		decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
		
		decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
			
			@Override
			public void onSystemUiVisibilityChange(int visibility) {
				if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0){
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
						}
					}, 2000);
				}
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
