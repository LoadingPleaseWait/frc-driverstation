package org.anidev.frcds.pc;

import javax.swing.JFrame;
import javax.swing.UIManager;
import org.anidev.frcds.common.DriverStation;
import org.anidev.frcds.pc.gui.DriverStationFrame;

public class DriverStationMain {
	public static final double LOOP_HERTZ=50.0;
	private static DriverStationFrame dsFrame;
	private static DriverStation ds;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			System.err.println("Error while setting Nimbus L&F.");
			e.printStackTrace();
		}
		dsFrame=new DriverStationFrame();
		dsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dsFrame.setVisible(true);
		ds=new PCDriverStation(dsFrame);
	}
	
	public static DriverStationFrame getFrame() {
		return dsFrame;
	}
	
	public static DriverStation getDS() {
		return ds;
	}
}