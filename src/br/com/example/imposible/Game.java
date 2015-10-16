package br.com.example.imposible;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Game extends Activity implements OnTouchListener{
	
	Impossible view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		//Logica do Jogo
		view = new Impossible(this);
		
		//Recebendo os onTouch
		view.setOnTouchListener(this);
		
		//Configurar View
		setContentView(view);
	
	}
	
	protected void onResume(){
		super.onResume();
		view.resume();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event){
		view.moveDown(10);
		view.addScore(100);
		return true;
	}
	

}
