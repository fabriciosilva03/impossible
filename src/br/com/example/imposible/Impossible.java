package br.com.example.imposible;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Impossible extends SurfaceView implements Runnable {
	
	//Atributos
	boolean running = false;
	Thread renderThread = null;
	
	SurfaceHolder holder;
	Paint paint;
	
	private int playerX=200, playerY = 300, playerRadius=50;
	
	private int enemyX, enemyY, enemyRadius = 50;
	
	private double distance;
	
	private boolean gameover;
	
	private int score;
	
	
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
				//canvas.drawColor(Color.BLACK);
				canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sky), 0, 0, null);
				
				//desenha o player
				drawPlayer(canvas);
				
				//Inicia a criacao do inimigo
				drawEnemy(canvas);
				
				//detecta colisao
				checkCollision(canvas);
								
				if(gameover){
					stopGame(canvas);
				}
				
				//Atualiza o placar
				drawScore(canvas);
				
				//Botoes Restart e Exit
				drawButtons(canvas);
				
				//Atualiza e libera o canvas
				holder.unlockCanvasAndPost(canvas);
							
			System.out.println("Impossible Running!!!");
		}
		
	}
	
	//Desenhado o player
	private void drawPlayer(Canvas canvas){
		paint.setColor(Color.GREEN);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nave), playerX-50, playerY-50, null);
		//canvas.drawCircle(playerX, playerY, 30, paint);
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
		paint.setColor(Color.RED);
		enemyRadius++;
		canvas.drawCircle(enemyX, enemyY, enemyRadius, paint);
	}
	
	//Metodo Colisão
	private void checkCollision(Canvas canvas){
		//Calcula a hipotenusa
		distance = Math.pow(playerY - enemyY, 2)
				+ Math.pow(playerX - enemyX, 2);
		distance = Math.sqrt(distance);
		
		//Verifica a distancia entre os raios
		if(distance  <= playerRadius + enemyRadius){
			gameover = true;
		}
	}
	
	//Mensagem de GAME OVER
	private void stopGame(Canvas canvas){
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText("GAME OVER!", 20, 100, paint);
	}

	//Pontuacao do jogo
	public void addScore(int points){
		score += points;
	}
	
	//Pontuacao na tela
	private void drawScore(Canvas canvas){
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(40);
		canvas.drawText(String.valueOf(score),50, 200, paint);
	}
	
	//Criando Botoes Restart e Exit
	private void drawButtons(Canvas canvas){
		//Restart
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText("Restart", 50, 300, paint);
		
		//Exit
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		canvas.drawText("Exit", 50, 500, paint);
		
	}
	
	//Reiniciando o jogo
	public void init(){
		enemyX = enemyY = enemyRadius = 0;
		playerX = playerY = 300;
		playerRadius = 50;
		gameover = false;
	}
		
}
