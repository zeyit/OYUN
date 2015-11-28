package oyn;

import java.awt.Image;
import java.util.Random;

public class Asteroid {

	Image img;
	int x,y;
	int hx,hy;
	private static int width,height;
	Random rnd;
	public Asteroid(Image img) {
		this.img=img;
		width=OyunKontrol.WH.x;
		height=OyunKontrol.WH.y;
		rnd=new Random();
		int rasgele_konum =rnd.nextInt(100);
		rasgele_konum*=rnd.nextInt(1000);
		if (rasgele_konum%4==0) {
			x=rnd.nextInt(width-100);
			y=2;
		}else if (rasgele_konum%4==1) {
			x=width-img.getWidth(null)-100;
			y=rnd.nextInt(height-img.getHeight(null)-20);
		}else if (rasgele_konum%4==2) {
			x=rnd.nextInt(width-20);
			y=height-img.getHeight(null)-100;
		}else if (rasgele_konum%4==3) {
			x=2;
			y=rnd.nextInt(height-20);
		}
		hx=rnd.nextInt(3);
		hx++;
		hy=rnd.nextInt(3);
		hy++;
	}
}
