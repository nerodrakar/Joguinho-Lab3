package projetoGame.Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Classe que contém os elementos do cenário do jogo player mobs shots
	 */
	private Image fundo; // Add um objeto do tipo imagem (plano de fundo do jogo)
	private Player player; // Variavel para add o player na fase
	private Timer timer;

	private List<Enemy1> enemy1; // Lista para guardar os mobs

	private boolean inGame;

	public Stage() {
		// Metodos para melhorar o desempenho
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\backgroundSprite1.png"); // recebe uma imagem (plano de fundo do espaço)
		fundo = referencia.getImage();

		player = new Player(); // Instânciando Player() em Stage
		player.load(); // Carrega a imagem do player

		addKeyListener(new TecladoAdapter()); // Instânciando TecladoAdapter() em Stage

		// Seta a velocidade do jogo quanto maior mais rápido
		timer = new Timer(5, this);
		timer.start();

		mobStart();
		inGame = true;
	}

	public void mobStart() {
		int coordenadas[] = new int[30]; // vetor que inicializa a quantidade de mobs
		enemy1 = new ArrayList<Enemy1>();
		// for para gera a posição aleatória do mob
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 500); // posição aleatória em x
			int y = (int) (Math.random() *  - 3000 - 900); // posição aleatória em y
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void paint(Graphics g) { // Prita na tela o objeto fundo
		Graphics2D graficos = (Graphics2D) g;
		if (inGame == true) {
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this); // Printa o player na tela de
																						// acordo
																						// com sua posição dx,dy
			// Imprime na tela a imagem dos shots
			List<Shot> shots = player.getShots();
			for (int i = 0; i < shots.size(); i++) {
				Shot m = shots.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			// Imprime na tela a imagem dos enemys
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		}else { //tela de game over
			ImageIcon gameOver = new ImageIcon("res\\gameover.jpg");
			graficos.drawImage(gameOver.getImage(), 0, 0, null);
		}

		g.dispose();
	}

	/*
	 * Metodos para atualizar a tela quando o player se move
	 */
	public void actionPerformed(ActionEvent e) {
		player.update(); // Atualiza player

		List<Shot> shots = player.getShots(); // adiciona a lista de shots em stage
		// for gerado para os infinitos shots gerados
		for (int i = 0; i < shots.size(); i++) {
			Shot m = shots.get(i);
			if (m.isVisivel()) { // Verifica se shot está visivel
				m.update();
			} else {
				shots.remove(i);
			}
		}
		// for para movimentar os mobs na tela
		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}
		checarColisoes();
		
		repaint(); // Reprinta a imagem constantemente na tela

	}

	/*
	 * Metodos para gerar as colisões retorna resultados diferentes para cada objeto
	 * colidindo ship - enemy = death shot - enemy = kill
	 */
	public void checarColisoes() {
		Rectangle formShip = player.getBounds();
		Rectangle formEnemy1;
		Rectangle formShot;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formEnemy1 = tempEnemy1.getBounds();
			if (formShip.intersects(formEnemy1)) { //colisão entre player-enemy
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				
				inGame = false;
			}
		}
		
		List<Shot> shots = player.getShots();
		for (int j = 0; j < shots.size(); j++) {
			Shot tempShot = shots.get(j);
			formShot = tempShot.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formEnemy1 = tempEnemy1.getBounds();
				if(formShot.intersects(formEnemy1)) { //colisão entre shot-enemy
					tempEnemy1.setVisivel(false);
					tempShot.setVisivel(false);
				}
			}
			
		}

	}

	/*
	 * Metodos de entrada do teclado para Stage
	 */
	private class TecladoAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}

	}
}
