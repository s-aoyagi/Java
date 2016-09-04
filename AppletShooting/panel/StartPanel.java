package panel;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener, KeyListener
//public class StartPanel extends JPanel implements ActionListener
{
	boolean in_game = true;
	AppletContext ac;   // アプレットのコンテキスト
	URL cb;   // HTML ファイルが存在する URL
	Dimension size;   // アプレットの大きさ
	MainPanel mp;
	JButton bt;
	// コンストラクタ
	public StartPanel(AppletContext ac1, URL cb1, Dimension size1, MainPanel mp1)
	{
		ac   = ac1;
		cb   = cb1;
		size = size1;
		mp   = mp1;
		// レイアウトマネージャの停止
		setLayout(null);
		// 背景色の設定
		setBackground(Color.white);
		// キーリスナの追加
		addKeyListener(this);
		//		addKeyListener(new Key());
	}
	// 描画
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);   // 親クラスの描画
		FontMetrics fm;
		Font f;
		String str1, str = "シューティング";
		int w, h;

		f    = new Font("Serif", Font.BOLD, 25);
		fm   = g.getFontMetrics(f);
		w    = fm.stringWidth(str);
		h    = fm.getHeight() + 60;
		g.setFont(f);
		g.drawString(str, size.width/2-w/2, h);

		f    = new Font("Serif", Font.PLAIN, 20);
		fm   = g.getFontMetrics(f);
		str1 = "ゲーム開始：「 s 」キー";
		w    = fm.stringWidth(str1);
		h    = size.height - fm.getHeight() - 10;
		g.setFont(f);
		g.drawString(str1, size.width/2-w/2, h);
		// この Component が入力フォーカスを取得することを要求
		requestFocusInWindow();
	}
	// ボタンがクリックされたときの処理
	public void actionPerformed(ActionEvent e)
	{
		URL url;
		if (e.getSource() == bt) {
			try {
				url = new URL(cb+"start/method.htm");
				ac.showDocument(url, "method");
				requestFocusInWindow();
			}
			catch (MalformedURLException me)
			{
				System.out.println("Bad URL: method.htm");
			}
		}
	}
	// キーが押されたときの処理（イベントリスナ）
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == 83)   // 「s」キー
			mp.state = 1;
	}
	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke) {}
	// キーが押されたときの処理（イベントアダプタ）
	/*
		class Key extends KeyAdapter {
			public void keyPressed(KeyEvent ke)
			{
				if (ke.getKeyCode() == 83)   // 「s」キー
					mp.state = 1;
			}
		}
	 */
}