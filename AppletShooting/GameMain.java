import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.JApplet;

import panel.MainPanel;

public class GameMain extends JApplet {
	// 初期設定
	public void init() {
		Dimension size = getSize(); // アプレットの大きさの取得
		size.width -=10;
		size.height -=10;
		Container con = getContentPane(); // ContentPane
		con.setLayout(null); // レイアウトマネージャ停止
		con.setBackground(new Color(220, 255, 220)); // 背景色
		AppletContext AC = getAppletContext(); // アプレットのコンテキスト
		URL CB = getCodeBase(); // HTMLのあるURL
		MainPanel pn = new MainPanel(AC, CB, size); // MainPanel生成
		con.add(pn); // MainPanelをContentPaneに追加
		pn.setSize(size.width, size.height);
		pn.setLocation(5, 5);
	}
}