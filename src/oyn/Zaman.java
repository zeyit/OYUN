package oyn;

public class Zaman implements Runnable{

	static int zaman;
	public Zaman(int zaman) {
		this.zaman=zaman;
		new Thread(this).start();
	}
	
	public static int getZaman() {
		return zaman;
	}

	public static void setZaman(int zaman) {
		Zaman.zaman = zaman;
	}

	@Override
	public void run() {
		while (true) {
			
			if (OyunKontrol.Durum==Oyun_State.Basladi) {
				
				//oyunu genel durumunu kontrol et
			try {
				Thread.sleep(1000);
				zaman++;
			} catch (InterruptedException e) {
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
