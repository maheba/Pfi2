package se.mah.Pfi2;

import sun.audio.*;
import java.io.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DigitalClockGUI extends JFrame {
	
	private ClockLogic clockLogic;

	private JPanel contentPane;
	private JTextField txtTiden;
	private JTextField txtAlarm;
	private JTextField textFieldMinut;
	protected JTextField txtSettimme;
	protected JTextField txtSetMinuter;
	protected JLabel lblBakgrundsbild;
	protected JTextField txtAlarm_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClockGUI frame = new DigitalClockGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DigitalClockGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DigitalClockGUI.class.getResource("/Images/alarm.png")));
		setTitle("Alarmklocka");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAlarm_1 = new JTextField();
		txtAlarm_1.setForeground(new Color(165, 42, 42));
		txtAlarm_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		txtAlarm_1.setEditable(false);
		txtAlarm_1.setColumns(10);
		txtAlarm_1.setBorder(null);
		txtAlarm_1.setBackground(new Color(255, 228, 181));
		txtAlarm_1.setBounds(157, 177, 128, 37);
		contentPane.add(txtAlarm_1);
		
		txtAlarm = new JTextField();
		txtAlarm.setBorder(null);
		txtAlarm.setEditable(false);
		txtAlarm.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		txtAlarm.setBackground(new Color(255, 228, 181));
		txtAlarm.setText(" Alarmtid: ");
		txtAlarm.setBounds(32, 177, 274, 37);
		contentPane.add(txtAlarm);
		txtAlarm.setColumns(10);
		
		txtSettimme = new JTextField();
		txtSettimme.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSettimme.setBounds(106, 258, 54, 37);
		contentPane.add(txtSettimme);
		txtSettimme.setColumns(10);
		
		JTextField txtTimme = new JTextField();
		txtTimme.setEditable(false);
		txtTimme.setBorder(null);
		txtTimme.setBackground(new Color(0, 0, 0));
		txtTimme.setForeground(new Color(255, 215, 0));
		txtTimme.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		txtTimme.setText("Timme:");
		txtTimme.setBounds(32, 269, 72, 26);
		contentPane.add(txtTimme);
		txtTimme.setColumns(10);
		
		txtSetMinuter = new JTextField();
		txtSetMinuter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSetMinuter.setColumns(10);
		txtSetMinuter.setBounds(252, 258, 54, 37);
		contentPane.add(txtSetMinuter);
		
		textFieldMinut = new JTextField();
		textFieldMinut.setEditable(false);
		textFieldMinut.setText("Minuter:");
		textFieldMinut.setForeground(new Color(255, 215, 0));
		textFieldMinut.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		textFieldMinut.setColumns(10);
		textFieldMinut.setBorder(null);
		textFieldMinut.setBackground(Color.BLACK);
		textFieldMinut.setBounds(168, 269, 79, 26);
		contentPane.add(textFieldMinut);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockLogic.setAlarm();
			}
		});
		btnOk.setBackground(new Color(255, 140, 0));
		btnOk.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		btnOk.setBounds(346, 258, 81, 37);
		contentPane.add(btnOk);
		
		JButton btnClear = new JButton("T\u00F6m");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clockLogic.clearAlarm();
			}
		});
		btnClear.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		btnClear.setBackground(new Color(255, 140, 0));
		btnClear.setBounds(346, 178, 81, 37);
		contentPane.add(btnClear);
		//btnClear.addActionListener(arg0);
		
		txtTiden = new JTextField();
		txtTiden.setBounds(32, 48, 395, 82);
		contentPane.add(txtTiden);
		txtTiden.setHorizontalAlignment(SwingConstants.LEFT);
		txtTiden.setBorder(null);
		txtTiden.setEditable(false);
		txtTiden.setFont(new Font("Segoe UI Black", Font.PLAIN, 85));
		txtTiden.setBackground(Color.ORANGE);
		txtTiden.setColumns(10);
		txtTiden.setAlignmentX(RIGHT_ALIGNMENT);
		
		lblBakgrundsbild = new JLabel("");
		lblBakgrundsbild.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblBakgrundsbild.setIcon(new ImageIcon(DigitalClockGUI.class.getResource("/Images/Night4.jpg")));
		lblBakgrundsbild.setBounds(0, 0, 617, 455);
		contentPane.add(lblBakgrundsbild);
		
		clockLogic = new ClockLogic(this);	
		//clockLogic.music();
	}
	
	//Methods
	/**A method to show the clock on the Tiden-label.*/
	public void setTimeOnLabel (String time) {
		txtTiden.setText(time);
	}
	/**A method to run the alarm-effect.*/	
	public void activateAlarm () {
		lblBakgrundsbild.setIcon(new ImageIcon(DigitalClockGUI.class.getResource("/Images/alarm.gif")));
		//clockLogic.music();
	}
}
