package se.mah.Pfi2;

import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import sun.audio.*;
import java.io.*;

public class ClockLogic {
	
	private DigitalClockGUI clockGUI;
	private int myAlarmHour;
	private int myAlarmMinute;

	//Constructor
	public ClockLogic(DigitalClockGUI digitalClockGUI) {
		// TODO Auto-generated constructor stub
		clockGUI = digitalClockGUI;
		InnerThread it = new InnerThread();
	}
	
	//Methods
	public void setAlarm() {
		myAlarmHour = Integer.parseInt(clockGUI.txtSettimme.getText());
		myAlarmMinute = Integer.parseInt(clockGUI.txtSetMinuter.getText());
		if (myAlarmHour>=0 && myAlarmHour <=23 && myAlarmMinute>=0 && myAlarmHour <=59) {
			String _myAlarmHour = "" + myAlarmHour;
			String _myAlarmMinute = "" + myAlarmMinute;

			if (myAlarmHour <= 9) {
				_myAlarmHour = "0" + myAlarmHour;
			}
			if (myAlarmMinute <= 9) {
				_myAlarmMinute = "0" + myAlarmMinute;
			}
			clockGUI.txtAlarm_1.setText(_myAlarmHour + ":" + _myAlarmMinute);
		}
	}
	/**A method that clears the alarm.*/
	public void clearAlarm() {
		clockGUI.txtAlarm_1.setText("");
		clockGUI.txtSettimme.setText("");
		clockGUI.txtSetMinuter.setText("");
		myAlarmMinute = 99;
		myAlarmHour = 99;
		clockGUI.lblBakgrundsbild.setIcon(new ImageIcon(DigitalClockGUI.class.getResource("/Images/Night4.jpg")));
	}
	/**A method that plays the alarm sound.*/
	public void music() {
	     
		/*
		AudioPlayer myAudioPlayer = AudioPlayer.player;
		AudioStream myAudioStream;
		AudioData myAudioData;
		ContinuousAudioDataStream loop = null;
		
		try {
			myAudioStream = new AudioStream(new FileInputStream("c:\\koltrast.wav"));
			//myAudioStream = new AudioStream(new FileInputStream("koltrast.wav"));
			myAudioData = myAudioStream.getData();
			loop = new ContinuousAudioDataStream(myAudioData);
		} catch (IOException error){System.out.println(error.toString());}
		
		myAudioPlayer.start(loop);
		*/
	}
	/**An innerThread to enable to get continuous update for the clock.*/
	public class InnerThread {
		//Constructor
		public InnerThread() {
			ClockThread myThread = new ClockThread();
			myThread.start();
		}
		//Inner class
		private class ClockThread extends Thread {
			//This method is called when the thread starts
			@Override
			public void run() {
				//Calendar minTid = new Calendar();
				//Run forever
				while (true) {
					try {
						Thread.sleep(1000);
						//calendar.get(Calendar.HOUR_OF_DAY);
						int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
						int minute = Calendar.getInstance().get(Calendar.MINUTE);
						int second = Calendar.getInstance().get(Calendar.SECOND);
						String clockHour = "" + hour;
						String clockMinute = "" + minute;
						String clockSecond = "" + second;
						if (hour <= 9) {
							clockHour = "0" + hour;
							//System.out.println(clockHour);
						}
						if (minute <= 9) {
							clockMinute = "0" + minute;
							//System.out.println(clockMinute);
						}
						if (second <= 9) {
							clockSecond = "0" + second;
							//System.out.println(clockSecond);
						}
						clockGUI.setTimeOnLabel(" " + clockHour + ":" + clockMinute + ":" + clockSecond + "   ");
												
						//Kollar om ställda alarmet matchar klockan och sätter igång larmmetoden.
						if (myAlarmHour == hour && myAlarmMinute == minute) {
							clockGUI.activateAlarm();
						}
					} catch (InterruptedException e) {}
					//System.out.println("Awake!");
					
					//All code for this other thread here
				}
			}
		}
	}
}
