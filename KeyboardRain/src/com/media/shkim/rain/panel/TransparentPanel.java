package com.media.shkim.rain.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class TransparentPanel extends JDialog {
   // public static void main(String[] args) throws IOException {
	public JFrame frame = new JFrame("Hello");
	public List<JLabel> jLabelList = new ArrayList<JLabel>();
	
	public void initial(){
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setType(javax.swing.JFrame.Type.UTILITY);
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.setLocation(0, 0); //1620 780
        frame.setSize(1920, 1080);
        frame.getContentPane().setLayout(new java.awt.BorderLayout(0, 0));
        frame.setBackground(new Color(0, 0, 0, 0));  
	}
	
	
	void drawImage(){
		ImageIcon myPicture0 = new ImageIcon("res/SpeechBubble.png");
	    Image image0 = myPicture0.getImage();
	    Image resizedImage0 = image0.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
	    ImageIcon resizedMypic = new ImageIcon(resizedImage0);
	    JLabel pitureLabel0 = new JLabel(resizedMypic);
	    
		pitureLabel0.setBorder(BorderFactory.createEmptyBorder(500, 500, 0, 0));
		frame.getContentPane().add(pitureLabel0,0); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true); 
	}
	

//	public void drawString(String sentence, int size, int y_coord, int x_coord, float h, float s, float b){
//		JLabel key = new JLabel();
//		key.setText(sentence);
//		Font myFont = new Font("현대하모니 B",Font.BOLD, size);
//		key.setFont(myFont);
//		key.setForeground(Color.getHSBColor(h, s, b));
//		key.setBorder(BorderFactory.createEmptyBorder(y_coord, x_coord, 0, 0));
//	    frame.getContentPane().add(key, 0);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//	}
	
	public void resetPanelObject() {
		frame.getContentPane().removeAll();
	}
	
	public void addPanelObject(PanelObject panelObject) {
		JLabel key = new JLabel();
		key.setText(panelObject.sentence);
		Font myFont = new Font("현대하모니 B",Font.BOLD, panelObject.size);
		key.setFont(myFont);
		key.setForeground(Color.getHSBColor(panelObject.h, panelObject.s, panelObject.b));
		jLabelList.add(key);
	}
	
	public void drawPanelObject(PanelObject panelObject, int num) {
	    jLabelList.get(num).setBorder(BorderFactory.createEmptyBorder(panelObject.y_coord, panelObject.x_coord, 0, 0));
		frame.getContentPane().add(jLabelList.get(num), 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	public int getContentPaneNum() {
		return frame.getContentPane().countComponents();
	}
	
	public void romoveLastContent() {
		frame.getContentPane().remove(getContentPaneNum()-1);
	}
	
	public static void main(String[] args) {
		TransparentPanel kk = new TransparentPanel();
		kk.initial();
		//kk.drawFinger1();
//		kk.drawString("hello 안녕하세요", 20, 0, 1800, 0, 0, 0);
		
	}
}