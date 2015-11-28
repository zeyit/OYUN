package oyn;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class MouseMoved extends MouseMotionAdapter implements Runnable{

	public MouseMoved() {
		//new Thread(this).start();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		
		OyunKontrol.setMouseP(e.getPoint());
		
	}
	@Override
	public void run() {
		while (true) {
			if (OyunKontrol.Durum==Oyun_State.Basladi) {// if içinde oyunu genel durumunu kontrol et
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
