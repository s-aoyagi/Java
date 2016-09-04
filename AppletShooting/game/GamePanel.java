package game;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Random;

import javax.swing.JPanel;

import panel.MainPanel;

public class GamePanel extends JPanel implements Runnable
{
	AppletContext ac;   // アプレットのコンテキスト
	URL cb;   // HTML ファイルが存在する URL
	Dimension size;   // アプレットの大きさ
	MainPanel mp;
	My my;   // 自機
	Boss bs;   // ボス
	int no = 2;   // 敵機の数
	Enemy em[] = new Enemy [no];   // 敵機
	boolean ex[] = new boolean [no];   // 敵機の存在
	Random rn;
	Thread td;
	boolean in_game = true;
	// コンストラクタ
	public GamePanel(AppletContext ac1, URL cb1, Dimension size1, MainPanel mp1)
	{
		ac   = ac1;
		cb   = cb1;
		size = size1;
		mp   = mp1;
		// レイアウトマネージャの停止
		setLayout(null);
		// 背景色の設定
		setBackground(Color.white);
		// ランダム変数の初期化
		rn = new Random();
		// 自機，ボス，敵機の配置
		my = new My(ac, cb,size);
		if (mp.level == 1) {
			ex[0] = false;
			ex[1] = false;
		}
		else {
			ex[0] = true;
			ex[1] = true;
		}
		bs = new Boss(ac, cb, size, rn, this);
		if (ex[0])
			em[0] = new Enemy(ac, cb, bs, 0, size, rn);
		if (ex[1])
			em[1] = new Enemy(ac, cb, bs, 1, size, rn);
		// スレッドの生成
		td = new Thread(this);
		td.start();
		// キーリスナの追加
		addKeyListener(new Key());
	}
	// スレッドの実行
	public void run()
	{
		while (in_game) {
			try {
				td.sleep(10);
			}
			catch (InterruptedException e) {}
			// 自機の弾による命中判定
			boolean hit = false;
			int xb, yb, xt, yt, h, w;
			// ボスに対して
			for (int i1 = 0; i1 < my.bl.no && !hit; i1++) {
				if (my.bl.ex[i1]) {
					xb = my.bl.x[i1] + my.bl.width / 2;
					yb = my.bl.y[i1] + my.bl.width / 2;
					w  = bs.width / 2 + my.bl.width / 2;
					h  = bs.height / 2 + my.bl.width / 2;
					xt = bs.x + bs.width / 2;
					yt = bs.y + bs.height / 2;
					if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
						my.bl.ex[i1] = false;
						bs.h_ct++;
						if (bs.h_ct > bs.h_max) {
							hit = true;
							in_game = false;
							bs.in_game = false;
							bs.bl.in_game = false;
							my.bl.in_game = false;
							for (int i2 = 0; i2 < no; i2++) {
								if (ex[i2])
									em[i2].bl.in_game = false;
							}
							mp.state = 2;   // ゲームクリア
						}
					}
				}
			}
			// 敵機に対して
			if (!hit) {
				for (int i1 = 0; i1 < no && !hit; i1++) {
					if (ex[i1]) {
						for (int i2 = 0; i2 < my.bl.no && !hit; i2++) {
							if (my.bl.ex[i2]) {
								xb = my.bl.x[i2] + my.bl.width / 2;
								yb = my.bl.y[i2] + my.bl.width / 2;
								w  = em[i1].width / 2 + my.bl.width / 2;
								h  = em[i1].height / 2 + my.bl.width / 2;
								xt = em[i1].x + em[i1].width / 2;
								yt = em[i1].y + em[i1].height / 2;
								if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
									hit = true;
									ex[i1] = false;
									my.bl.ex[i2] = false;
									em[i1].bl.in_game = false;
								}
							}
						}
					}
				}
			}
			// ボスの弾による命中判定
			if (!hit) {
				for (int i1 = 0; i1 < bs.bl.no && !hit; i1++) {
					if (bs.bl.ex[i1]) {
						xb = bs.bl.x[i1] + bs.bl.width / 2;
						yb = bs.bl.y[i1] + bs.bl.width / 2;
						w  = my.width / 2;
						h  = my.width / 2;
						xt = my.x + w;
						yt = my.y + h;
						if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
							hit = true;
							in_game = false;
							bs.in_game = false;
							bs.bl.in_game = false;
							my.bl.in_game = false;
							for (int i3 = 0; i3 < no; i3++) {
								if (ex[i3])
									em[i3].bl.in_game = false;
							}
							mp.state = 3;   // ゲームオーバー
						}
					}
				}
			}
			// 敵機の弾による命中判定
			if (!hit) {
				for (int i1 = 0; i1 < no && !hit; i1++) {
					if (ex[i1]) {
						for (int i2 = 0; i2 < em[i1].bl.no && !hit; i2++) {
							if (em[i1].bl.ex[i2]) {
								xb = em[i1].bl.x[i2] + em[i1].bl.width / 2;
								yb = em[i1].bl.y[i2] + em[i1].bl.width / 2;
								w  = my.width / 2;
								h  = my.width / 2;
								xt = my.x + w;
								yt = my.y + h;
								if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
									hit = true;
									in_game = false;
									bs.in_game = false;
									bs.bl.in_game = false;
									my.bl.in_game = false;
									for (int i3 = 0; i3 < no; i3++) {
										if (ex[i3])
											em[i3].bl.in_game = false;
									}
									mp.state = 3;   // ゲームオーバー
								}
							}
						}
					}
				}
			}
			// 再描画
			repaint();
		}
	}
	// 描画
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);   // 親クラスの描画
		// 自機と弾
		g.drawImage(my.image, my.x, my.y, this);
		g.setColor(Color.green);
		for (int i1 = 0; i1 < my.bl.no; i1++) {
			if (my.bl.ex[i1])
				g.fillOval(my.bl.x[i1], my.bl.y[i1], my.bl.width, my.bl.width);
		}
		// ボスと弾
		g.drawImage(bs.image, bs.x, bs.y, this);
		g.setColor(Color.orange);
		for (int i1 = 0; i1 < bs.bl.no; i1++) {
			if (bs.bl.ex[i1])
				g.fillOval(bs.bl.x[i1], bs.bl.y[i1], bs.bl.width, bs.bl.width);
		}
		// 敵機と弾
		g.setColor(Color.red);
		for (int i1 = 0; i1 < no; i1++) {
			if (ex[i1]) {
				g.drawImage(em[i1].image, em[i1].x, em[i1].y, this);
				for (int i2 = 0; i2 < em[i1].bl.no; i2++) {
					if (em[i1].bl.ex[i2])
						g.fillOval(em[i1].bl.x[i2], em[i1].bl.y[i2],
								em[i1].bl.width, em[i1].bl.width);
				}
			}
		}
		// この Component が入力フォーカスを取得することを要求
		requestFocusInWindow();
	}
	// キーが押されたときの処理（イベントアダプタ）
	class Key extends KeyAdapter {
		public void keyPressed(KeyEvent ke)
		{
			if (ke.getKeyCode() == KeyEvent.VK_UP)   // 「↑」キー（ 38 ）
				my.y -= my.v;
			else if (ke.getKeyCode() == KeyEvent.VK_DOWN)   // 「↓」キー（ 40 ）
				my.y += my.v;
			else if (ke.getKeyCode() == KeyEvent.VK_LEFT)   // 「←」キー（ 37 ）
				my.x -= my.v;
			else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)   // 「→」キー（ 39 ）
				my.x += my.v;
			else if (ke.getKeyCode() == KeyEvent.VK_SPACE)   // 「スペース」キー（ 32 ）
				my.bl.shoot();
		}
	}
}