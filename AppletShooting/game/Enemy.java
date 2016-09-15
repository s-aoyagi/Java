package game;

import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

class Enemy
{
	Image image; // 敵機の画像
	int width = 45; // 敵機の幅
	int height = 51; // 敵機の高さ
	int x, y; // 敵機の位置
	Boss bs; // ボス
	int n; // 敵機番号
	Bullet_e bl; // 弾
	// コンストラクタ
	public Enemy(AppletContext ac, URL cb, Boss bs1, int n1, Dimension size, Random rn) {
		bs = bs1;
		n  = n1;
		// 敵機画像の読み込み
		try {
			URL url = new URL(cb + "image/enemy.png");
			image = ac.getImage(url);
		}
		catch (MalformedURLException e) {
			System.out.println("Bad URL");
		}
		// 敵機の初期位置
		if (n == 0) {
			x = bs.x - 150 + rn.nextInt(100);
			y = bs.y + bs.height - 80 + rn.nextInt(100);
		}
		else {
			x = bs.x + bs.width + 50 + rn.nextInt(100);
			y = bs.y + bs.height - 80 + rn.nextInt(100);
		}
		// 弾の発射
		bl = new Bullet_e(size, rn, this);
	}
}