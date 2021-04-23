package projetoGame.Model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	/*
	 * Classe com os metodos principais do jogo x,y,dx,dy utlizados para mover
	 * altura, largura colisão
	 */
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;

	private List<Shot> shots; // Lista para armazenar a quantidade infinita de shots do player
	private boolean isVisivel; // boolean para verificar se o player esta na tela

	public Player() {
		// coordenadas da tela onde o Player irá surgir
		this.x = 200;
		this.y = 350;
		isVisivel = true;

		shots = new ArrayList<Shot>(); // array para armazenar a quantidade indeterminada de shots
	}

	public void load() {
		// metodos para definir a imagem do player
		ImageIcon referencia = new ImageIcon("res\\spaceship1.GIF"); // imagem de referencia do player
		imagem = referencia.getImage();

		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	/*
	 * Métodos para receber os comandos do teclado para mover o player
	 */
	public void update() { // Movimenta o player nos eixos x e y
		x += dx;
		y += dy;
	}

	public void Shot1() {
		this.shots.add(new Shot((x + 21), (y - 20))); // Shot surge no topo da nave
	}

	/*
	 * Metodo para gerar as colisões cria um retangulo envolta dos objetos
	 * (player/mob)
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) { // Metodo para receber entrada do teclado
		int codigo = tecla.getKeyCode(); // reconhece a tecla pressionada

		if (codigo == KeyEvent.VK_W) {
			dy = -3; // movimenta o player 3pixels para cima
		}
		if (codigo == KeyEvent.VK_S) {
			dy = 3; // movimenta o player 3pixels para baixo
		}
		if (codigo == KeyEvent.VK_A) {
			dx = -3; // movimenta o player 3pixels para esquerda
		}
		if (codigo == KeyEvent.VK_D) {
			dx = 3; // movimenta o player 3pixels para direita
		}
	}

	public void keyRelease(KeyEvent tecla) { // Metodo que retorna dx,dy para 0 quando a tecla é solta
		int codigo = tecla.getKeyCode(); // reconhece a tecla pressionada
		/*
		 * Quando dx,dy retona o valor para 0 o player para
		 */
		if (codigo == KeyEvent.VK_SPACE) { // Evento para os shots
			Shot1();
		}

		if (codigo == KeyEvent.VK_W) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_S) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_A) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_D) {
			dx = 0;
		}
	}

	/*
	 * Getters and Setters
	 */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Shot> getShots() {
		return shots;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

}
