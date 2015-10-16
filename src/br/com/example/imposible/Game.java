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
		
		if(event.getX() < 100 && event.getY() > 290 && event .getY() < 310){
			view.init();
		}
		
		//Exit
		if(event.getX() < 110 && event.getY() > 490 && event.getY() < 510){
			System.exit(0);
		}
		
		//Incrementa em 10 pixels a posicao
		//Vertical do player e o placar
		
		view.moveDown(10);
		view.addScore(100);
		
		return true;
	}
	

}
