package game;

import java.awt.Dimension;
import java.util.Random;

class Bullet_b implements Runnable
{
	int width = 24;   // 弾の幅
	int no = 15;   // 弾の全数
	int x[], y[];   // 弾の位置
	int v = 30;   // 弾の速さ
	int vx[], vy[];   // 横及び縦方向の弾の速さ
	int pr = 5;   // 弾の発射間隔
	int ct = 0;
	boolean ex[];   // 弾の存在
	boolean in_game = true;
	Thread td;
	Dimension size;
	Random rn;
	Boss bs;
	My my;
	// コンストラクタ
	public Bullet_b(Dimension size1, Boss bs1, My my1)
	{
		size = size1;
		bs   = bs1;
		my   = my1;
		// 初期設定と最初の弾の発射
		x  = new int [no];
		y  = new int [no];
		vx = new int [no];
		vy = new int [no];
		ex = new boolean [no];
		for (int i1 = 0; i1 < no; i1++)
			ex[i1] = false;
		shoot();
		// スレッドの生成
		td = new Thread(this);
		td.start();
	}
	// スレッドの実行
	public void run()
	{
		while (in_game) {
			try {
				td.sleep(100);
			}
			catch (InterruptedException e) {}
			// 弾の移動
			for (int i1 = 0; i1 < no; i1++) {
				if (ex[i1]) {
					x[i1] += vx[i1];
					y[i1] += vy[i1];
					if (x[i1] < -width || x[i1] > size.width || y[i1] > size.height)
						ex[i1] = false;
				}
			}
			// 次の弾の発射
			ct = (ct + 1) % pr;
			if (ct == 0)
				shoot();
		}
	}
	// 弾の発射
	void shoot()
	{
		boolean sw = true;
		int xt, yt;
		for (int i1 = 1; i1 < no && sw; i1++) {
			if (!ex[i1]) {
				sw     = false;
				ex[i1] = true;
				x[i1]  = bs.x + bs.width / 2 - width / 2;
				y[i1]  = bs.y + bs.height;
				yt     = my.y + my.height / 2 - (y[i1] + width / 2);
				xt     = my.x + my.width / 2 - (x[i1] + width / 2);
				double ang = Math.atan2(yt, xt);
				vx[i1] = (int)(v * Math.cos(ang) + 0.5);
				vy[i1] = (int)(v * Math.sin(ang) + 0.5);
			}
		}
	}
}