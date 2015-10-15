package br.com.example.imposible;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity{
	
	Impossible view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		//Logica do Jogo
		view = new Impossible(this);
		
		//Configurar View
		setContentView(view);
	
	}
	
	protected void onResume(){
		super.onResume();
		view.resume();
	}
	
	

}
