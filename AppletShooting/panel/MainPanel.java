package panel;

import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.JPanel;

import game.GamePanel;

public class MainPanel extends JPanel implements Runnable {
	AppletContext ac; // アプレットのコンテキスト
	URL cb; // HTMLのあるURL
	Dimension size; //アプレットの大きさ
	boolean in_game = true; // ゲーム実行中はtrueとする
	public int state = 0; // ゲーム状態（0:開始画面，1:ゲーム，2:クリア，3:オーバー，4:終了）
	public int level = 1; // ゲームレベル
	int old_state = 0; // 直前のゲーム状態
	StartPanel sp;
	GamePanel gp;
	ClearPanel cp;
	OverPanel op;
	Thread td;
			// コンストラクタ
	public MainPanel(AppletContext ac1, URL cb1, Dimension size1) {
		ac = ac1;
		cb = cb1;
		size = size1;
		// レイアウト
		setLayout(new GridLayout(1, 1, 0, 0));
		// ゲームパネルの生成
		sp = new StartPanel(ac, cb, size, this); // タイトル
		add(sp);
		// スレッドの生成
		td = new Thread(this);
		td.start();
	}
	// ゲームの状態を変更
	public void run() {
		while (in_game) {
			try {
				Thread.sleep(100); // スリープ処理
			}
			catch (InterruptedException e) {}
			if (state != old_state) {
				// 前のパネルの削除
				if (old_state == 0)
					remove(sp);
				else if (old_state == 1)
					remove(gp);
				else if (old_state == 2)
					remove(cp);
				else
					remove(op);
				// 新しいパネルの追加
				if (state == 4) // ゲーム終了
					in_game = false;
				else {
					if (state == 0) { // StartPanel呼び出し
						sp = new StartPanel(ac, cb, size, this);
						add(sp);
					}
					else if (state == 1) { // GamePanel呼び出し
						gp = new GamePanel(ac, cb, size, this);
						add(gp);
					}
					else if (state == 2) { // ClearPanel呼び出し
						cp = new ClearPanel(ac, cb, size, this);
						add(cp);
					}
					else { // OverPanel呼び出し
						op = new OverPanel(ac, cb, size, this);
						add(op);
					}
					validate();
					old_state = state;
				}
			}
		}
	}
}