package GFX;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Main.GuiGame;

public class Window extends Canvas {

    private static final long serialVersionUID = 1L;

    public Window(int width, int height, String title, GuiGame guiGame) {
	JFrame frame = new JFrame(title);
	frame.setPreferredSize(new Dimension(width, height));
	frame.setMaximumSize(new Dimension(width, height));
	frame.setMinimumSize(new Dimension(width, height));

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.add(guiGame);

	frame.setVisible(true);
	guiGame.start();
		
    }

}
