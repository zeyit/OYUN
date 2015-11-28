package oyn;

import java.awt.Point;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable{

	
	public Frame(String oyunAdi,Point p,Point WH,int zorluk) {
		super(oyunAdi);
		this.setBounds(p.x, p.y, WH.x, WH.y);
	//	this.addMouseListener(new Mouse());
		this.add(new Panel(WH.x, WH.y,zorluk));
		this.addKeyListener(new Klavye());
	//	this.addMouseMotionListener(new MouseMoved());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Thread(this).start();
		}
	@Override
	public  void finalize() throws Throwable {

		
		super.finalize();

	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (OyunKontrol.Durum==Oyun_State.Bitti) {
				try {
					this.finalize();
					this.dispose();
					break;
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	

}
