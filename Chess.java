import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chess extends JPanel
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.getContentPane().add(new Chess());
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * We fill in the background and systematically clear out the whit squares.
	 * 
	 * TODO render the file and row numbers. TODO render the board in the center of the frame.
	 */
	public void paint(Graphics g)
	{
		for (int file = 0; file < 8; file++)
		{
			for (int rank = 0; rank < 8; rank++)
			{
				boolean isLightSquare = (file + rank) % 2 != 0;
				
				if (isLightSquare)
				{
					// We clear a square from the top right point.
					g.clearRect((file * 100), (rank * 100), 100, 100);
				}
				else
				{
					g.fillRect((file * 100), (rank * 100), 100, 100);
				}
			}
		}
	}
	
	/**
	 * We are going to use bytes to represent pieces The 3 bits on the right, indicate piece. The 2
	 * bits on the left, indicate color.
	 * 
	 * @author Administrator
	 *
	 */
	public static class Piece
	{
		public static final int none = 0;
		public static final int king = 1;
		public static final int pawn = 2;
		public static final int knight = 3;
		public static final int bishop = 4;
		public static final int rook = 5;
		public static final int queen = 6;
		
		public static final int white = 8;
		public static final int black = 16;
		
	}
	
	/**
	 * We will use FEN to set the board up. We use FEN to determine which piece goes where, then use
	 * the helper method to get the correct spirtes and render them in the correct places.
	 * 
	 * @author Administrator
	 *
	 */
	public static class Board
	{
		public static int[] square;
		
		public Board()
		{
			square = new int[64];
			
			// We use a bitwise or here
			square[0] = Piece.white | Piece.bishop;
			square[63] = Piece.black | Piece.knight;
		}
	}
	
	/**
	 * Helper method for getting the sprites of the pieces.
	 * 
	 * @param piece
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getSprite(int piece) throws IOException
	{
		switch (piece)
		{
			case (1 | 8):
				return ImageIO.read(getClass().getResource("klt60.png"));
			case (1 | 16):
				return ImageIO.read(getClass().getResource("kdt60.png"));
			case (2 | 8):
				return ImageIO.read(getClass().getResource("plt60.png"));
			case (2 | 16):
				return ImageIO.read(getClass().getResource("pdt60.png"));
			case (3 | 8):
				return ImageIO.read(getClass().getResource("nlt60.png"));
			case (3 | 16):
				return ImageIO.read(getClass().getResource("ndt60.png"));
			case (4 | 8):
				return ImageIO.read(getClass().getResource("blt60.png"));
			case (4 | 16):
				return ImageIO.read(getClass().getResource("bdt60.png"));
			case (5 | 8):
				return ImageIO.read(getClass().getResource("rlt60.png"));
			case (5 | 16):
				return ImageIO.read(getClass().getResource("rdt60.png"));
			case (6 | 8):
				return ImageIO.read(getClass().getResource("qlt60.png"));
			case (6 | 16):
				return ImageIO.read(getClass().getResource("qdt60.png"));
			default:
				return null;
		}
	}
}
