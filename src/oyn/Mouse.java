package oyn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class Mouse extends MouseAdapter implements Runnable{

	
	public Mouse() {
		
		Thread mouse =new Thread(this);
		mouse.setPriority(Thread.MAX_PRIORITY);
		mouse.start();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		Panel.SilahEkle();	
		
	}

	@Override
	public void run() {
		while (true) {
			if (true) {// if içinde oyunu genel durumunu kontrol et
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (OyunKontrol.Durum==Oyun_State.Bitti) {
					try {
						this.finalize();
						break;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
}
