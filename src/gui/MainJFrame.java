package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class MainJFrame extends JFrame {
	private static final String TITLE = "EMR Scanner App";
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private Container content;
	public JLabel title;
	public JButton connect;
	public JPanel panel;
	public JPanel midpanel;
	// public JPanel[] panels;
	public JPanel dummypanel;
	public JPanel dummypanel1;
	public JTextField[] textfields;
	private Border border;

	public static MainJFrame frame;

	public MainJFrame() {
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Content initialization
		connect = new JButton("Connect to DB");
		connect.addActionListener(new connectHandler());
		title = new JLabel("Welcome to EMR Scanner App at Fl Poly", SwingConstants.CENTER);
		title.setForeground(Color.white);
		title.setFont(new Font("Helvetica", Font.BOLD, 16));
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		midpanel = new JPanel();
		midpanel.setLayout(new GridLayout(5, 1));
		midpanel.setBackground(Color.DARK_GRAY);
		border = BorderFactory.createLineBorder(Color.WHITE, 1);
		midpanel.setBorder(border);
		dummypanel = new JPanel();
		dummypanel.setBackground(Color.DARK_GRAY);
		dummypanel1 = new JPanel();
		dummypanel1.setBackground(Color.DARK_GRAY);

		textfields = new JTextField[4];
		// panels = new JPanel[5];
		for (int i = 0; i < textfields.length; i++) {
			textfields[i] = new JTextField();
			midpanel.add(textfields[i]);
		}
		midpanel.add(connect);
		panel.add(dummypanel);
		panel.add(midpanel);
		panel.add(dummypanel1);

		// GUI Initialization
		content = getContentPane();
		content.setLayout(new GridLayout(3, 2));
		content.setBackground(Color.DARK_GRAY);
		content.add(title);
		content.add(panel);
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { }

		frame = new MainJFrame();
		frame.setVisible(true);
		frame.setJMenuBar(new menuBar());
		SwingUtilities.updateComponentTreeUI(frame);
	}
}

class connectHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton pressed = (JButton) e.getSource();
		System.out.println(pressed.getText());
		JPanel parent = (JPanel) pressed.getParent();
		for (Component jtext : parent.getComponents()) {
			try {
				JTextField j = (JTextField) jtext;
				System.out.println(j.getText());
			} catch (Exception err) {
			}
		}
	}

}

class menuBar extends JMenuBar {

	private JMenu menu;
	private JMenuItem item;
	private JMenuItem item1;
	JPopupMenu popup;
	JMenuItem menuitem;

	public menuBar() {
		super();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menu);
		
		popup = new JPopupMenu();
		menuitem = new JMenuItem("A popup menu item");
		popup.add(menuitem);
		popup.addPopupMenuListener(new PopupPrintListener());
		
		item1 = new JMenuItem("Connect");
		item1.addMouseListener(new PopupListener());
		menu.add(item1);
		menu.addSeparator();
		item = new JMenuItem("Exit");
		item.addActionListener(new menuHandler());
		menu.add(item);
	}
	
	class PopupPrintListener implements PopupMenuListener {
	    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
	      System.out.println("Popup menu will be visible!");
	    }

	    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	      System.out.println("Popup menu will be invisible!");
	    }

	    public void popupMenuCanceled(PopupMenuEvent e) {
	      System.out.println("Popup menu is hidden!");
	    }
	  }
	
	class PopupListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		public void mouseClicked(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				System.out.println("PRESSED?");
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}

class menuHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem pressed = (JMenuItem) e.getSource();

		// If exit then exit!
		if (pressed.getText().equals("Exit"))
			System.exit(1);
	}

}
