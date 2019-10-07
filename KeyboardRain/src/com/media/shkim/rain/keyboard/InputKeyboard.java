package com.media.shkim.rain.keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.media.shkim.rain.panel.PanelObject;
import com.media.shkim.rain.panel.TransparentPanel;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class InputKeyboard extends Thread {
	private static boolean run = true;
	static GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
	static TransparentPanel transparentPanel = new TransparentPanel();
	static InputKeyboard inputKey = new InputKeyboard();
	static Random generator = new Random();
	static long start = 0;
	static long end = 0;
	static public List<PanelObject> panelObjectList = new ArrayList<PanelObject>();
	static public int[] x_stack = new int[192]; 
	
	
	private static final Map<Integer, Character> korCharMap= new HashMap<Integer, Character>();
	static {
		korCharMap.put(81, 'ㅂ');
		korCharMap.put(87, 'ㅈ');
		korCharMap.put(69, 'ㄷ');
		korCharMap.put(82, 'ㄱ');
		korCharMap.put(84, 'ㅅ');
		korCharMap.put(89, 'ㅛ');
		korCharMap.put(85, 'ㅕ');
		korCharMap.put(73, 'ㅑ');
		korCharMap.put(79, 'ㅐ');
		korCharMap.put(80, 'ㅔ');
		korCharMap.put(65, 'ㅁ');
		korCharMap.put(83, 'ㄴ');
		korCharMap.put(68, 'ㅇ');
		korCharMap.put(70, 'ㄹ');
		korCharMap.put(71, 'ㅎ');
		korCharMap.put(72, 'ㅗ');
		korCharMap.put(74, 'ㅓ');
		korCharMap.put(75, 'ㅏ');
		korCharMap.put(76, 'ㅣ');
		korCharMap.put(90, 'ㅋ');
		korCharMap.put(88, 'ㅌ');
		korCharMap.put(67, 'ㅊ');
		korCharMap.put(86, 'ㅍ');
		korCharMap.put(66, 'ㅠ');
		korCharMap.put(78, 'ㅜ');
		korCharMap.put(77, 'ㅡ');
		korCharMap.put(8, '←');
		korCharMap.put(27, '★');
	}
	
	private static final Map<Integer, Character> engCharMap= new HashMap<Integer, Character>();
	static {
		engCharMap.put(81, 'q');
		engCharMap.put(87, 'w');
		engCharMap.put(69, 'e');
		engCharMap.put(82, 'r');
		engCharMap.put(84, 't');
		engCharMap.put(89, 'y');
		engCharMap.put(85, 'u');
		engCharMap.put(73, 'i');
		engCharMap.put(79, 'o');
		engCharMap.put(80, 'p');
		engCharMap.put(65, 'a');
		engCharMap.put(83, 's');
		engCharMap.put(68, 'd');
		engCharMap.put(70, 'f');
		engCharMap.put(71, 'g');
		engCharMap.put(72, 'h');
		engCharMap.put(74, 'j');
		engCharMap.put(75, 'k');
		engCharMap.put(76, 'l');
		engCharMap.put(90, 'z');
		engCharMap.put(88, 'x');
		engCharMap.put(67, 'c');
		engCharMap.put(86, 'v');
		engCharMap.put(66, 'b');
		engCharMap.put(78, 'n');
		engCharMap.put(77, 'm');
		engCharMap.put(8, '←');
		engCharMap.put(27, '★');
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < panelObjectList.size(); i++) {
				if(i >= 15) {
					break;
				}
				if(i >= 3) {
					panelObjectList.get(i).y_coord = panelObjectList.get(i).y_coord + 50;
				} else {
					panelObjectList.get(i).y_coord = panelObjectList.get(i).y_coord + generator.nextInt((25 - 5) + 1) + 5;
				}
				panelObjectList.get(i).x_coord = panelObjectList.get(i).x_coord + generator.nextInt(10) - 5;
				
				int current_x = panelObjectList.get(i).x_coord;
				if(current_x < 0) {
					current_x = 0;
				} else if(current_x >= 1920) {
					current_x = 1910;
				}
				int stacked_x = 950 - 80 * (x_stack[Math.round(current_x/10)]) - (panelObjectList.get(i).size/2);
				if(panelObjectList.get(i).y_coord > stacked_x) {
					panelObjectList.remove(i);
					transparentPanel.jLabelList.remove(i);
//					transparentPanel.romoveLastContent();
					x_stack[Math.round(current_x/10)] = x_stack[Math.round(current_x/10)] + 1;
				} else {
					transparentPanel.drawPanelObject(panelObjectList.get(i), i);
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		
		inputKey.start();
		transparentPanel.initial();
		for(int i = 0; i < x_stack.length; i++) {
			x_stack[i] = 0;
		}
		System.out.println("finish initializing");
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			boolean isKorean = false;
			@Override
			public void keyPressed(GlobalKeyEvent event) {
				int x_coord = generator.nextInt((1920 - 0) + 1) + 0;
				int y_coord = (generator.nextInt((1080 - 30) + 1) + 30) - (1080/2);
				int size = generator.nextInt((150 - 30) + 1) + 30;
				float h = generator.nextFloat() * (300 - 0) + 0;
				float s = generator.nextFloat() * (100 - 0) + 0;
				float b = generator.nextFloat() * (100 - 0) + 0;
				
				PanelObject newPanel = new PanelObject();
				
				
				if(event.getVirtualKeyCode() == 21 && isKorean == false) {
					isKorean = true;
				} else if(event.getVirtualKeyCode() == 21 && isKorean == true){
					isKorean = false;
				}
				if(isKorean) {
					if(korCharMap.get(event.getVirtualKeyCode()) != null) {
						newPanel.sentence = Character.toString(korCharMap.get(event.getVirtualKeyCode()));
					} else {
						newPanel.sentence = Character.toString(event.getKeyChar());
					}
				} else {
					if(engCharMap.get(event.getVirtualKeyCode()) != null) {
						newPanel.sentence = Character.toString(engCharMap.get(event.getVirtualKeyCode()));
					} else {
						newPanel.sentence = Character.toString(event.getKeyChar());
					}
				}
				newPanel.size = size;
				newPanel.y_coord = y_coord;
				newPanel.x_coord = x_coord;
				newPanel.h = h;
				newPanel.s = s;
				newPanel.b = b;
				if(panelObjectList.size() < 16) {
					transparentPanel.addPanelObject(newPanel);
					panelObjectList.add(newPanel);
				}
//				if(event.getVirtualKeyCode() == 13) {
//					transparentPanel.removeAll();
//					panelObjectList.removeAll(panelObjectList);
//					transparentPanel.resetPanelObject();
//				}
				
//				if(transparentPanel.getContentPaneNum() >= 15) {
//					transparentPanel.romoveLastContent();
//				}
			}

		});
		
//		try {
//            while(run) Thread.sleep(100);
//        } catch(InterruptedException e) { /* nothing to do here */ }
//          finally { keyboardHook.shutdownHook(); }
	}

}