package projetoGame.Model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
	/*
	 * Cont�m os metodos para cria��o e movimenta��o dos inimigos Funciona
	 * exatamente da mesma forma que a classe Shot ao contr�rio do shot os enemys
	 * andam de cima para baixo
	 */
	private Image imagem; // Imagem gr�fica do shot
	private int x, y; // Posi��o
	private int largura, altura; // tamanho
	private boolean isVisivel; // Verifica se o shot chegou no limite da tela

	// private static final int LARGURA = 900; //Tamanho ideal para o limite da tela
	// para o shot sumir
	private static int VELOCIDADE = 3; // Velocidade do shot

	public Enemy1(int x, int y) { // Construtor com par�metros equivalente a posi��o do player
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void load() {
		// Metodos para definir a imagem do shot
		ImageIcon referencia = new ImageIcon("res\\enemy1.png");
		imagem = referencia.getImage();
		// Metodos para definir o tamanho do shot
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	/*
	 * Metodos para atualizar a posi��o Velocidade Verificar alcan�ou o limite da
	 * tela
	 */
	public void update() {
		this.y += VELOCIDADE;
		/*
		 * if(this.x >LARGURA) { isVisivel = false; //Altera o valor tornando a variavel
		 * false }
		 */
	}

	/*
	 * Metodo para gerar as colis�es cria um retangulo envolta dos objetos
	 * (player/mob)
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	// Getters and Setters
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

}
