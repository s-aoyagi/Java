package game;

import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

class Boss implements Runnable {
	Image image; // ボス画像
	int width = 95; // ボスの幅
	int height = 66; // ボスの高さ
	int x, y; // ボスの位置
	int h_ct = 0; // 命中した弾の数
	int h_max = 5; // 耐えられる命中した弾の数
	Thread td;
	boolean in_game = true;
	int ptn1[][] = {{-5, 0, 50}, {0, 20, 55}, {5, 0, 105}, {0, -20, 110}};
	int ptn2[][] = {{5, 0, 50}, {0, 20, 55}, {-5, 0, 105}, {0, -20, 110}};
	int ptn[][];
	int ct = 1;
	Random rn;
	GamePanel gp;
	Bullet_b bl; // 弾
	// コンストラクタ
	public Boss(AppletContext ac, URL cb, Dimension size, Random rn1, GamePanel gp1) {
		rn = rn1;
		gp = gp1;
		// ボス画像の読み込み
		try {
			URL url = new URL(cb + "image/boss.png");
			image = ac.getImage(url);
		}
		catch (MalformedURLException e) {
			System.out.println("Bad URL");
		}
		// ボスの初期位置
		x = 100 + rn.nextInt(size.width - 200 - width);
		y = 10 + rn.nextInt(5);
		if (x > size.width/2-width/2)
			ptn = ptn1;
		else
			ptn = ptn2;
		// スレッドの生成
		td = new Thread(this);
		td.start();
		// 弾の発射
		bl = new Bullet_b(size, this, gp.player);
	}
	// スレッドの実行
	public void run() {
		while (in_game) {
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {}
			ct++;
			if (ct > 110)
				ct = 1;
			// ボスの位置
			int k = -1;
			for (int i1 = 0; i1 < ptn.length-1 && k < 0; i1++) {
				if (ct <= ptn[i1][2])
					k = i1;
			}
			if (k < 0)
				k = ptn.length - 1;
			x += ptn[k][0];
			y += ptn[k][1];
			// 敵機の位置
			if (gp.ex[0]) {
				gp.em[0].x += ptn[k][0];
				gp.em[0].y += ptn[k][1];
			}
			if (gp.ex[1]) {
				gp.em[1].x += ptn[k][0];
				gp.em[1].y += ptn[k][1];
			}
		}
	}
}