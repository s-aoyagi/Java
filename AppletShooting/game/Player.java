package game;

import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

class Player
{
	Image image; // 自機の画像
	int width = 38; // 自機の幅
	int height = 38; // 自機の高さ
	int x, y; // 自機の位置
	int v = 20; // 移動キーが押されたときの移動量
	Bullet bl; // 弾
	// コンストラクタ
	public Player(AppletContext ac, URL cb, Dimension size) {
		// 自機画像の読み込み
		try {
			URL url = new URL(cb + "image/player.png");
			image = ac.getImage(url);
		}
		catch (MalformedURLException e) {
			System.out.println("Bad URL");
		}
		// 自機の初期位置
		x = size.width / 2 - width / 2;
		y = size.height - height -10;
		// 弾の発射
		bl = new Bullet(size, this);
	}
}