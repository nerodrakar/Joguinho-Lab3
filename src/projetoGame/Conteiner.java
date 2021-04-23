package projetoGame;

import javax.swing.JFrame;

import projetoGame.Model.Stage;

public class Conteiner extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Classe com todos os componentes necessários para a janela do jogo
	 */
	public Conteiner() {
		add(new Stage());                                     //Classe Stage add no construtor padrão da classe
		setTitle("THE GAME");                                 //Titulo da Janela
		setSize(500, 500);                                    //Tamanho da Janela 500x500
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Comando para fechar a Janela
		setLocationRelativeTo(null);                          //Local onde abre a Janela (meio da tela)
		this.setResizable(false);                             //controla o tamanho da tela (false = não pode alterar)
		setVisible(true);                                     //Define a visibilidade da serie de comandos
	}
	
	public static void main(String []args) {
		new Conteiner();
	}
}
