package game;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.JPanel;

import panel.MainPanel;

public class GamePanel extends JPanel implements Runnable {
	Image image; // 背景画像
	int width = 1024; // 背景画像の幅
	int height = 768; // 背景画像の高さ
	int x, y; // 背景画像の位置
	AppletContext ac;   // アプレットのコンテキスト
	URL cb;   // HTML ファイルが存在する URL
	Dimension size;   // アプレットの大きさ
	MainPanel mp;
	Player player;   // 自機
	Boss bs;   // ボス
	int no = 2;   // 敵機の数
	Enemy em[] = new Enemy [no];   // 敵機
	Key key = new Key();
	boolean ex[] = new boolean [no];   // 敵機の存在
	Random rn;
	Thread td;
	boolean in_game = true;
	//プライヤーの移動用フラグ
	boolean left_push = false;
	boolean right_push = false;
	boolean down_push = false;
	boolean up_push = false;
	// コンストラクタ
	public GamePanel(AppletContext ac1, URL cb1, Dimension size1, MainPanel mp1)
	{
		ac   = ac1;
		cb   = cb1;
		size = size1;
		mp   = mp1;
		// レイアウトマネージャの停止
		setLayout(null);
		// フィールド画像の読み込み
		try {
			URL url = new URL(cb + "image/field.png");
			image = ac.getImage(url);
		}
		catch (MalformedURLException e) {
			System.out.println("Bad URL");
		}
		// ランダム変数の初期化
		rn = new Random();
		// 自機，ボス，敵機の配置
		player = new Player(ac, cb,size);
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
				Thread.sleep(10);
			}
			catch (InterruptedException e) {}
			// 自機の弾による命中判定
			boolean hit = false;
			int xb, yb, xt, yt, h, w;
		//移動処理
			if(up_push == true) {
				player.y -= 2;
			}
			else if(down_push == true) {
				player.y += 2;
			}
			else if(left_push == true) {
				player.x -= 2;
			}
			else if(right_push == true) {
				player.x += 2;
			}
			// ボスに対して
			for (int i1 = 0; i1 < player.bl.no && !hit; i1++) {
				if (player.bl.ex[i1]) {
					xb = player.bl.x[i1] + player.bl.width / 2;
					yb = player.bl.y[i1] + player.bl.width / 2;
					w  = bs.width / 2 + player.bl.width / 2;
					h  = bs.height / 2 + player.bl.width / 2;
					xt = bs.x + bs.width / 2;
					yt = bs.y + bs.height / 2;
					if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
						player.bl.ex[i1] = false;
						bs.h_ct++;
						if (bs.h_ct > bs.h_max) {
							hit = true;
							in_game = false;
							bs.in_game = false;
							bs.bl.in_game = false;
							player.bl.in_game = false;
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
						for (int i2 = 0; i2 < player.bl.no && !hit; i2++) {
							if (player.bl.ex[i2]) {
								xb = player.bl.x[i2] + player.bl.width / 2;
								yb = player.bl.y[i2] + player.bl.width / 2;
								w  = em[i1].width / 2 + player.bl.width / 2;
								h  = em[i1].height / 2 + player.bl.width / 2;
								xt = em[i1].x + em[i1].width / 2;
								yt = em[i1].y + em[i1].height / 2;
								if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
									hit = true;
									ex[i1] = false;
									player.bl.ex[i2] = false;
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
						w  = player.width / 2;
						h  = player.width / 2;
						xt = player.x + w;
						yt = player.y + h;
						if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
							hit = true;
							in_game = false;
							bs.in_game = false;
							bs.bl.in_game = false;
							player.bl.in_game = false;
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
								w  = player.width / 2;
								h  = player.width / 2;
								xt = player.x + w;
								yt = player.y + h;
								if (xb > xt-w && xb < xt+w && yb > yt-h && yb < yt+h) {
									hit = true;
									in_game = false;
									bs.in_game = false;
									bs.bl.in_game = false;
									player.bl.in_game = false;
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
		g.drawImage(this.image, this.x, this.y, this);
		g.drawImage(player.image, player.x, player.y, this);
		g.setColor(Color.green);
		for (int i1 = 0; i1 < player.bl.no; i1++) {
			if (player.bl.ex[i1])
				g.fillOval(player.bl.x[i1], player.bl.y[i1], player.bl.width, player.bl.width);
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
		requestFocusInWindow();
	}

	// キーが押されたときの処理（イベントアダプタ）
	class Key extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {// 「↑」キー（ 38 ）
				up_push = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// 「↓」キー（ 40 ）
				down_push = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 「←」キー（ 37 ）
				left_push = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 「→」キー（ 39 ）
				right_push = true;
			}
			else if (e.getKeyCode() == KeyEvent.VK_SPACE) {// 「スペース」キー（ 32 ）
				player.bl.shoot();
			}
		}
		//キーを離すとフラグが消滅
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {// 「↑」キー（ 38 ）
				up_push = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// 「↓」キー（ 40 ）
				down_push = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// 「←」キー（ 37 ）
				left_push = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// 「→」キー（ 39 ）
				right_push = false;
			}
		}
	}
}