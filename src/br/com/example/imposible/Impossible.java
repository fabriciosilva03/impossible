package br.com.example.imposible;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Impossible extends SurfaceView implements Runnable {
	
	//Atributos
	boolean running = false;
	Thread renderThread = null;
	
	SurfaceHolder holder;
	Paint paint;
	
	private int playerY = 300;
	
	private float enemyRadius;
	
	
	public Impossible(Context context){
		super(context);
		
		paint = new Paint();
		holder = getHolder();
	}

	//Executando o game
	@Override
	public void run() {
		while(running){
			//Verifica se a tela ja esta pronta
			if(!holder.getSurface().isValid())
				continue;	
			
				//Bloquei o canvas e prepara a tela
				Canvas canvas = holder.lockCanvas();
				canvas.drawColor(Color.BLACK);
				
				//desenha o player
				drawPlayer(canvas);
				
				//Inicia a criacao do inimigo
				drawEnemy(canvas);
				
				//O que fazer aqui
				
				//Atualiza e libera o canvas
				holder.unlockCanvasAndPost(canvas);
				
				
		
			
			System.out.println("Impossible Running!!!");
		}
		
	}
	
	//Desenhado o player
	private void drawPlayer(Canvas canvas){
		paint.setColor(Color.GREEN);
		canvas.drawCircle(100, playerY, 50, paint);
	}
	
	//Pause do jogo
	public void resume(){
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	//Mover o player para baixo
	public void moveDown(int pixels){
		playerY += pixels;
	}
	
	//Criando o inimigo
	private void drawEnemy(Canvas canvas){
		paint.setColor(Color.GRAY);
		enemyRadius++;
		canvas.drawCircle(100, 100, enemyRadius, paint);
	}
	

}
