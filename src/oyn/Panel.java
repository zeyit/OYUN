package oyn;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{

	
	Zaman zaman;
	int width,height;
	static Image imgUzay,imgArac;
	Point aracP,mouseP;
	static int Aci;
	static int KursunAci;
	static ArrayList<Asteroid> asteroid;
	static ArrayList<Silah> silah;
	public Panel(Point p) {
		this(p.x, p.y,5);
	}
	public Panel(int width,int height,int zorluk) {
		this.width=width;
		this.height=height;
		this.addMouseListener(new Mouse());
		this.addMouseMotionListener(new MouseMoved());
		asteroid =new ArrayList<Asteroid>();
		silah=new ArrayList<Silah>();
		aracP=new Point(width/2, height/2);
		new OyunKontrol(zorluk,new Point(width/2, height/2));
		OyunKontrol.skor=0;
		imgArac=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("oyn/arac.png"));
		imgUzay=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("oyn/uzay2.gif"));
		OyunKontrol.setDurum(Oyun_State.Basladi);
		zaman=new Zaman(0);
		new Thread(this).start();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		aracP =OyunKontrol.getAracP();
		mouseP=OyunKontrol.getMouseP();
		g.drawImage(imgUzay, 0, 0, width, height, null);
		
		
		
		//Sað panel renk
		
		
		
		
		g.setColor(Color.green);
		g.drawString("Skor : "+Integer.toString(OyunKontrol.getSkor()), width-100, 25);
		g.drawString("MouseX :"+Integer.toString(mouseP.x)
							 +" MouseY : "+Integer.toString(mouseP.y)
							 +"Açý :"+Double.toString(10*Aci-90), width-200, 60);
		g.drawString("Süre : "+Integer.toString(zaman.getZaman()), width-100, 40);
		if ( Zaman.getZaman()%60 <= 10 && Zaman.getZaman()>10 ) {
		g.drawString("Ulti açýlmýþtýr Space ile kullanabilirsin", 20, 20);
		}
		AsteroidCiz(g);
		
		AracAciHesapla(aracP,mouseP);
		AracYonlendir(g, aracP);
		g.setColor(Color.WHITE);
	
		
	}
	private static void AsteroidCiz(Graphics g) {
		for (int i = 0; i <AsteroidSize(); i++) {
			g.drawImage(asteroid.get(i).img, asteroid.get(i).x, asteroid.get(i).y, null);
		}
		for (int i = 0; i < SilahSize(); i++) {
			//kursunlarý çizwd
			 Point p=	KursunAciHesapla(silah.get(i).Startp, silah.get(i).uzaklik, silah.get(i).Aci);
			silah.get(i).p=p;
			 silah.get(i).uzaklik+=8;
			 g.setColor(Color.green);
			g.fillOval(p.x, p.y, 15, 15);
		}

	}
	
	public static void AsteroidEkle(Asteroid ast) {
		asteroid.add(ast);
	}
	public static int AsteroidSize() {
		return asteroid.size();
	}
	public static void SilahEkle() {
		silah.add(new Silah(OyunKontrol.aracP,OyunKontrol.mouseP,KursunAci));
	}
	public static int SilahSize() {
		return silah.size();
	}
	private void AracYonlendir(Graphics g,Point aracP) {
		Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
        g2d.translate(aracP.x, aracP.y); // Translate the center of our coordinates.
        g2d.rotate(Math.toRadians(10*Aci-90));  // Rotate the image by 1 radian.
        g2d.drawImage(imgArac, 
        		-imgArac.getWidth(null)/2,
        		-imgArac.getHeight(null)/2, 
        		 imgArac.getWidth(null),
        		 imgArac.getHeight(null), 
        		 this);	
	}
	
private void AracAciHesapla(Point aracP,Point mouseP) {
		int x =Math.abs(aracP.x);
		int y =Math.abs(aracP.y);
		int mx =Math.abs(mouseP.x);
		int my =Math.abs(mouseP.y);

		int uzaklik=(int) Math.floor(Math.sqrt((Math.pow((x - mx), 2)+Math.pow((y-my), 2))));
		//g.setColor(Color.white);
	    for(int i=1;i<=36;i++){
			double hour=Math.PI/18;
		 	int x1=(int)(x-5+(uzaklik)*Math.sin(i*hour));
			int y1=(int)(y+5-(uzaklik)*Math.cos(i*hour));	         
		//	g.drawString(Integer.toString(i),x1,y1);
		//	g.drawRect(mykontrol.getMouseX()-30, mykontrol.getMouseY()-30, 60	, 50);
			if ((mx-30 <x1 && x1 < mx +60)&& (my-30 < y1 && y1 < my +50)) {
				if (i>=15) {
				Aci=(i+10);	
				}else {
					Aci=(i+9);
				}
				if (i>16) {
					KursunAci=++i;	
				}else {
					KursunAci=i;
				}
				
				break;
			}
	    }
	}
private static Point KursunAciHesapla(Point aracP,int uzaklik,int Aci) {
	int x =Math.abs(aracP.x);
	int y =Math.abs(aracP.y);
	double hour=Math.PI/18;
	int x1=(int)(x-5+(uzaklik)*Math.sin(Aci*hour));
	int y1=(int)(y+5-(uzaklik)*Math.cos(Aci*hour));	         
	return new Point(x1,y1);
}
	@Override
	public void run() {
		while (true) {
			if (OyunKontrol.Durum==Oyun_State.Basladi) {//oyun durumunu kontrol et
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i <AsteroidSize(); i++) {
					//asteroid in width ve height i frame in dýþýna çýkýp çýkmadýðýný kontrolet
						if ((asteroid.get(i).x +asteroid.get(i).img.getWidth(null) > width ||asteroid.get(i).x  < 0)) {
							asteroid.get(i).hx=-1*asteroid.get(i).hx;
						}
						if (asteroid.get(i).y +asteroid.get(i).img.getHeight(null) > height || asteroid.get(i).y <0) {
							asteroid.get(i).hy=-1*asteroid.get(i).hy;
						}
						asteroid.get(i).x+=asteroid.get(i).hx;
						asteroid.get(i).y+=asteroid.get(i).hy;
				}
				for (int i = 0; i <AsteroidSize(); i++) {
					//asteroid lerin kendileri ile çarpýþmalrýný kontrol et
					Rectangle r1 =new Rectangle(asteroid.get(i).x, asteroid.get(i).y, asteroid.get(i).img.getWidth(null), asteroid.get(i).img.getHeight(null));
						for (int j = i; j < AsteroidSize(); j++) {
							if (i==j) {
								continue;
							}
							Rectangle r2 =new Rectangle(asteroid.get(j).x, asteroid.get(j).y, asteroid.get(j).img.getWidth(null), asteroid.get(j).img.getHeight(null));
							if (r1.intersects(r2)) {
								int tut=asteroid.get(i).hx;
								asteroid.get(i).hx=asteroid.get(j).hx;
								asteroid.get(j).hx=tut;
								 tut=asteroid.get(i).hy;
								asteroid.get(i).hy=asteroid.get(j).hy;
								asteroid.get(j).hy=tut;
								if (asteroid.get(i).x ==0 && asteroid.get(i).y==0 || asteroid.get(i).x==width && asteroid.get(i).y==height) {
									asteroid.get(i).x=asteroid.get(i).x+2;
									asteroid.get(i).y=asteroid.get(i).y+2;
									
								}
								
							}
						}
						
				}
				for (int i = AsteroidSize()-1; i >=0; i--) {
					Rectangle r1 =new Rectangle(asteroid.get(i).x, asteroid.get(i).y, asteroid.get(i).img.getWidth(null), asteroid.get(i).img.getHeight(null));
					for (int j = SilahSize()-1; j >=0; j--) {
						//asteroid leri vurup vurmadýðýmýzý kontrol etme
							Rectangle r2 =new Rectangle(silah.get(j).p.x,silah.get(j).p.y,20,20);
							if (r1.intersects(r2)) {
								asteroid.remove(i);
								silah.remove(j);
								
								
								if (zaman.getZaman()>30) {
									OyunKontrol.setSkor((zaman.getZaman()/30)*2+(OyunKontrol.getSkor()));
								}else {
									OyunKontrol.setSkor(1+(OyunKontrol.getSkor()));
								}
								break;
							}
							}
					}
				}
				for (int i = SilahSize()-1; i >= 0; i--) {
					if (silah.get(i).p.x +10<0 && silah.get(i).p.y +10<0 || silah.get(i).p.x > width && silah.get(i).p.y >height) {
						//kurþunlerýn frame dýþýna çýkmadurumunu kontro et
						silah.remove(i);
						
					}
				}
				Rectangle r =new Rectangle(OyunKontrol.aracP.x, OyunKontrol.aracP.y, imgArac.getWidth(null), imgArac.getHeight(null));
				for (int i = 0; i < AsteroidSize(); i++) {
					Rectangle r1 =new Rectangle(asteroid.get(i).x, asteroid.get(i).y, asteroid.get(i).img.getWidth(null), asteroid.get(i).img.getHeight(null));
					if (r.intersects(r1)) {//çapýþma varmý kontrol et
						OyunKontrol.setDurum(Oyun_State.Bitti);
						
						Start.frame.setVisible(true);
					}
				}
				
				repaint();
				//-------------------------
				if (OyunKontrol.Durum==Oyun_State.Bitti) {
					try {
						this.finalize();
						break;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//------------------------
				
			}
		
			
		}
		
	}
	

