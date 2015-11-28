package oyn;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

public class OyunKontrol implements Runnable{

	static Point mouseP=new Point(0, 0) ,aracP;
	static Point StartframeP =new Point(400, 100),FrameP=new Point(300, 0);
	static Point WH =new Point(600, 700);
	static String oyunAdi="Uzay Mekiði Kontrolü";
	static Oyun_State Durum=Oyun_State.Bitti;
	static int skor=0;
	int zorluk,adet;
	Random rnd;
	Image asteroid1,asteroid2,asteroid3;
	public OyunKontrol(int zorluk,Point p) {
		this.zorluk=zorluk;
		aracP=p;
		adet=5;
		rnd =new Random();
		/*asteroid1 =Toolkit.getDefaultToolkit().getImage("img/asteroid0.jpg");
		asteroid2 =Toolkit.getDefaultToolkit().getImage("img/asteroid1.jpg");
		asteroid3 =Toolkit.getDefaultToolkit().getImage("img/asteroid2.jpg");
		*/
		asteroid1 =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("oyn/asteroid0.png"));
		asteroid2 =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("oyn/asteroid1.png"));
		asteroid3 =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("oyn/asteroid0.png"));
		
		new Thread(this).start();
	}
	public static Point getMouseP() {
		return mouseP;
	}
	public static void setMouseP(Point mouseP) {
		OyunKontrol.mouseP = mouseP;
	}
	public static Point getAracP() {
		return aracP;
	}
	public static void setAracP(Point aracP) {
		OyunKontrol.aracP = aracP;
	}
	public static Point getStartframeP() {
		return StartframeP;
	}
	public static void setStartframeP(Point startframeP) {
		StartframeP = startframeP;
	}
	public static Point getFrameP() {
		return FrameP;
	}
	public static void setFrameP(Point frameP) {
		FrameP = frameP;
	}
	public static Point getWH() {
		return WH;
	}
	public static void setWH(Point wH) {
		WH = wH;
	}
	public static String getOyunAdi() {
		return oyunAdi;
	}
	public static void setOyunAdi(String oyunAdi) {
		OyunKontrol.oyunAdi = oyunAdi;
	}
	public static Oyun_State getDurum() {
		return Durum;
	}
	public static void setDurum(Oyun_State durum) {
		Durum = durum;
	}
	public static int getSkor() {
		return skor;
	}
	public static void setSkor(int skor) {
		OyunKontrol.skor = skor;
	}
	@Override
	public void run() {
		while (true) {
			if (true) {//oyun durumunu kontrol et
				try {
					Thread.sleep(zorluk*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (OyunKontrol.Durum==Oyun_State.Bitti) {
					try {
						this.finalize();
						break;
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
				for (int i = 0; i < adet-Panel.AsteroidSize(); i++) {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int img =rnd.nextInt(3);
					 if (img==0) {
						 Panel.AsteroidEkle(new Asteroid(asteroid1));
					}else if(img==1){
						 Panel.AsteroidEkle(new Asteroid(asteroid2));
					}else {
						 Panel.AsteroidEkle(new Asteroid(asteroid3));
					}
				}
			}
			
		}
		
	}
	
}
enum Oyun_State {

	Basladi,Durduruldu,Bitti;
}
