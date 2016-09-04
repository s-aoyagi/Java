package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import panel.MainPanel;

public class Edit extends JPanel implements ActionListener
{
	Dimension size;   // アプレットの大きさ
	MainPanel mp;
	boolean mouse = false;   // マウスがクリックされたか否か
	int k1, k2;   // マウスでクリックされた位置
	JButton bt;
	// コンストラクタ
	public Edit(Dimension size1, MainPanel mp1)
	{
		size = size1;
		mp   = mp1;
		// レイアウトマネージャの停止
		setLayout(null);
		// 背景色の設定
		setBackground(Color.white);
		// リスナの追加
		addMouseListener(new Mouse());   // マウスリスナ
		addKeyListener(new Key());   // キーリスナ
		// ボタンの配置
		Font f = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics fm = getFontMetrics(f);
		String str = "OK";
		int w = fm.stringWidth(str) + 40;
		int h = fm.getHeight();
		bt = new JButton(str);
		bt.setFont(f);
		bt.addActionListener(this);
		bt.setSize(w, h);
		int x = size.width - w - 10;
		int y = size.height / 2 - h / 2;
		bt.setLocation(x, y);
		add(bt);
	}
	// 描画
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);   // 親クラスの描画
		// 背景の描画
		for (int i1 = 0; i1 < Map.map_col; i1++) {
			int x = i1 * Map.block_width;
			for (int i2 = 0; i2 < Map.map_row; i2++) {
				if (Map.map[i2][i1] > 0) {
					int y = i2 * Map.block_height;
					g.drawImage(Map.block_image[Map.map[i2][i1]-1], x, y, Map.block_width, Map.block_height, this);
				}
			}
			g.drawLine(x, 0, x, Map.map_row * Map.block_height);
			if (i1 == Map.map_col-1)
				g.drawLine(x+Map.block_width, 0, x+Map.block_width, Map.map_row * Map.block_height);
		}
		for (int i1 = 0; i1 < Map.map_row; i1++) {
			int y = i1 * Map.block_height;
			g.drawLine(0, y, Map.map_col * Map.block_width, y);
		}
		// この Component が入力フォーカスを取得することを要求
		requestFocusInWindow();
	}
	// マウスイベントの処理
	class Mouse extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			k1 = e.getY() / Map.block_height;
			k2 = e.getX() / Map.block_width;
			if (k1 >= 0 && k1 < Map.map_row && k2 >= 0 && k2 < Map.map_col)
				mouse = true;
			else
				mouse = false;
		}
	}
	// キーイベントの処理
	class Key extends KeyAdapter {
		public void keyPressed(KeyEvent e)
		{
			if(mouse && e.getKeyCode() >= 48 && e.getKeyCode() <= 50) {
				if (e.getKeyCode() == 48)   // キー 0
					Map.map[k1][k2] = 0;
				else if (e.getKeyCode() == 49)   // キー 1
					Map.map[k1][k2] = 1;
				else if (e.getKeyCode() == 50)   // キー 2
					Map.map[k1][k2] = 2;
				repaint();
			}
		}
	}
	// ボタンがクリックされたときの処理
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bt)
			mp.state = 0;
	}
}