package oyn;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Klavye extends KeyAdapter implements Runnable{

	 Point aracP;
	int hareket;
	boolean pressedA,pressedD,pressedS,pressedW;
	Rectangle pencere,arac;
	public Klavye() {

		aracP =new Point();
		pressedA=false;
		pressedD=false;
		pressedS=false;
		pressedW=false;
		hareket=2;
	new Thread(this).start();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if (e.getKeyCode()==KeyEvent.VK_P && OyunKontrol.Durum==Oyun_State.Basladi) {
			OyunKontrol.Durum=Oyun_State.Durduruldu;
			Start.frame.setVisible(true);
			}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			pressedA=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
		pressedS=true;
		}
		if (e.getKeyCode()==KeyEvent.VK_D) {
			pressedD=true; 
		}
		if (e.getKeyCode()==KeyEvent.VK_W) {
		pressedW=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE && Zaman.getZaman()%60 <= 10 && Zaman.getZaman()>10 ) {
			Panel.SilahEkle();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		
		if (e.getKeyCode()==KeyEvent.VK_A) {
			pressedA=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
		pressedS=false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_D) {
			pressedD=false; 
		}
		if (e.getKeyCode()==KeyEvent.VK_W) {
		pressedW=false;
		}
	
	}
	@Override
	public  void finalize() throws Throwable {

		super.finalize();

	}
	@Override
	public void run() {
		while (true) {
		
			if (OyunKontrol.Durum==Oyun_State.Basladi) {//oyunu genel durumunu kontrol et
			
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//---------neysneyi yok etme---
				if (OyunKontrol.Durum==Oyun_State.Bitti) {
					try {
						this.finalize();
						break;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//------------------
				
				
				aracP=OyunKontrol.getAracP();
					boolean cifttus=false;
				
				//----------çift harf kontrolü-----
				if (pressedA && pressedS) {
					cifttus=true;
					aracP =(new Point(aracP.x-hareket, aracP.y+hareket));
					
				}
				if (pressedA && pressedW) {
					aracP =(new Point(aracP.x-hareket, aracP.y-hareket));
					cifttus=true;
				}
				if (pressedD && pressedW) {
					aracP =(new Point(aracP.x+hareket, aracP.y-hareket));	
					cifttus=true;
				}
				if (pressedD && pressedS) {
					aracP =(new Point(aracP.x+hareket, aracP.y+hareket));
					cifttus=true;
				}
				if (cifttus) {
					 arac =new Rectangle(aracP.x,aracP.y,Panel.imgArac.getWidth(null),Panel.imgArac.getHeight(null));
						pencere =new Rectangle(100,100,OyunKontrol.WH.x-100,OyunKontrol.WH.y-100);
						if (arac.intersects(pencere)) {
						OyunKontrol.setAracP(aracP);
						continue;
						}	
						continue;
					}
				
				
				//--------Tek harf kontrolü-------------
				if (pressedA) {
					aracP =(new Point(aracP.x-hareket, aracP.y));
				}
				if (pressedD) {
					aracP =(new Point(aracP.x+hareket, aracP.y));	
				}
				if (pressedS) {
					aracP =(new Point(aracP.x, aracP.y+hareket));
				}
				if (pressedW) { 
					aracP =(new Point(aracP.x, aracP.y-hareket));
				}
				arac =new Rectangle(aracP.x,aracP.y,Panel.imgArac.getWidth(null),Panel.imgArac.getHeight(null));
				pencere =new Rectangle(100,100,OyunKontrol.WH.x-100,OyunKontrol.WH.y-150);
				if (arac.intersects(pencere)) {
				OyunKontrol.setAracP(aracP);	
				continue;
				}
			
				
			
			}
	}
}
}		
