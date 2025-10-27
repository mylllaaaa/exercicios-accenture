package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JOptionPane;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

/*
 *Observações sobre o código: 
 *1. As posições da matriz vão de a2 até h7 (o senhor pode digitar dessa forma);
 *2. Ao tentar acessar as posições nas linhas 1 e 8 o programa mostrará um erro: A peça não pode mover para esta posição. Aqui seria o "fim da linha" para o código";
 *3. Os peões são classes, já as outras peças são apenas prints;
 *4. Peões podem ser capturados;
 *5. Melhor experiência - No Eclipse o comando de limpar a tela não funciona, mas se o senhor abrir um git bash na pasta 'bin' e digitar - java application/Program -, o comando funciona.
 */

/*
  Recado pro professor:
  	 No início do ano eu fiz um curso de java do professor Nélio Alves (Java COMPLETO Programação Orientada a Objetos + Projetos), 
  	 nesse curso um dos projetos que o professor nos ensinou foi o jogo de Xadrez.
  	  
  	 Como a atividade que o senhor passou é parecida com o projeto, eu reutilizei partes do código para fazer a atividade. 
  	 
  	 Achei melhor avisar previamente para não passar uma impressão errada.
  	 
  	 Caso o senhor queira dar uma olhada, aqui está o projeto completo com todo o versionamento salvo: 
  	 
  	 https://github.com/mylllaaaa/chess-system-java
  	 
  	 Bom final de semana :)
 */

public class Program {

	public static void main(String[] args) {

		int resposta;
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		resposta = JOptionPane.showConfirmDialog(null, "Deseja começar a jogar?");

		while (resposta == JOptionPane.YES_OPTION) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition();

				System.out.println("");

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition();

				System.out.println("");

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}

				resposta = JOptionPane.showConfirmDialog(null, "Deseja continuar jogando");
			} catch (ChessException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		JOptionPane.showInternalMessageDialog(null, "Programa encerrado.");
		UI.clearScreen();
	}
}
