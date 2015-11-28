package oyn;

import java.awt.Point;

public class Silah {

	int Aci,uzaklik;
	Point Startp,Endp;
	Point p;
	public Silah(Point Startp,Point Endp,int Aci) {
		this.Startp=Startp;
		this.Endp=Endp;
		this.Aci=Aci;
		uzaklik=50;
		p=Startp;
	}
}
