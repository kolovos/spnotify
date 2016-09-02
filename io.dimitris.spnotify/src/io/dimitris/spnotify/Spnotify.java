package io.dimitris.spnotify;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class Spnotify {
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Spnotify().run();
	}

	protected void run() throws Exception {
		
		final JFrame dialog = new JFrame();
		dialog.setUndecorated(true);
		dialog.setSize(100, 100);
		//dialog.setOpacity(0.0f);
		
		final MotionLabel label = new MotionLabel(dialog);
		label.setText(getSpotifyInfo());
		Border border = BorderFactory.createEmptyBorder(10,10,10,10);
		label.setBorder(border);
		//label.setOpaque(true);
		dialog.getRootPane().setLayout(new BorderLayout());
		dialog.getRootPane().add(label, BorderLayout.CENTER);
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				label.setText(getSpotifyInfo());
				dialog.pack();
			}
		}, 0, 1000);
		
		dialog.pack();
		dialog.setVisible(true);
		
		//		HudWindow hud = new HudWindow("Window");
//		hud.getJDialog().setSize(300, 350);
//		hud.getJDialog().setLocationRelativeTo(null);
//		hud.getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		hud.getJDialog().setVisible(true);
//		hud.getJDialog().setUndecorated(true);
	}
	
	protected String getSpotifyInfo() {
		try {
			Object result = AppleScriptEngine.getInstance().eval(
					"tell application \"Spotify\"",
					"	set currentArtist to artist of current track as string",
					"	set currentTrack to name of current track as string",
					"	return \"<html>\" &currentArtist & \"<br>\" & currentTrack & \"</html>\"",
					"end tell"
					);
			return result + "";
		}
		catch (Exception ex) {
			return null;
		}
	}
	
}
