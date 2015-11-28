package oyn;





import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JFrame implements ActionListener{

	static JFrame frame;
	static JPanel panel;
	JButton btnBasla,btnDevam,btnHakkinda,btnCikis; 
	JComboBox cbSeviye;
	JLabel lbl;
	Container container;
	static String oyunAdi;
	static int width,height;
	Point frameP,WH;
	public Start(String oyunAdi) {
		super(oyunAdi);
		this.oyunAdi=oyunAdi;
		width=300;
		height=400;
		WH=OyunKontrol.WH;
		frameP=OyunKontrol.StartframeP;
		String [] seviye={"KOLAY","ORTA","ZOR"};
		panel =new JPanel();
		
		btnBasla =new JButton("Baþla");
		btnBasla.setEnabled(false);
		btnDevam =new JButton("Devam Et");
		cbSeviye=new JComboBox(seviye);
		btnHakkinda=new JButton("Hakýnda");
		btnCikis =new JButton("Çýkýþ");
		lbl =new JLabel(new ImageIcon(getClass().getClassLoader().getResource("oyn/uzay_1.jpg")));
		
		panel.setLayout(null);
		btnDevam.setEnabled(false);//oyunun durumuna göre true false
		
		btnBasla.addActionListener(this);
		btnDevam.addActionListener(this);
		//cbSeviye.addActionListener(this);
		btnHakkinda.addActionListener(this);
		btnCikis.addActionListener(this);
		
		this.setBounds(frameP.x, frameP.y, width, height);
		lbl.setBounds(0, 0, width, height);
		btnBasla.setBounds(70, 75,160,30);
		btnDevam.setBounds(70, 125,160,30);
		cbSeviye.setBounds(70, 175,160,30);
		btnHakkinda.setBounds(70, 225,160,30);
		btnCikis.setBounds(70, 275,160,30);
		
		lbl.add(btnBasla);
		lbl.add(btnDevam);
		lbl.add(cbSeviye);
		lbl.add(btnHakkinda);
		lbl.add(btnCikis);
		panel.add(lbl);
		/*panel.add(btnBasla);
		panel.add(btnDevam);
		panel.add(cbSeviye);
		panel.add(btnHakkinda);
		panel.add(btnCikis);*/
		
		this.add(panel);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnBasla.setEnabled(true);
		
	}
	public static void main(String[] args) {
		frame =new Start(OyunKontrol.oyunAdi);
		
	}
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		if (OyunKontrol.Durum==Oyun_State.Durduruldu) {
			btnDevam.setEnabled(true);
			btnBasla.setEnabled(false);
		}
		if (OyunKontrol.Durum==Oyun_State.Bitti) {
			btnDevam.setEnabled(false);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource()==btnBasla) {
				frame.setVisible(false);
				frameP=OyunKontrol.FrameP;
				OyunKontrol.Durum=oyn.Oyun_State.Basladi;
				new oyn.Frame(oyunAdi, frameP, WH,(5-cbSeviye.getSelectedIndex()));
				// cbSeviye.getSelectedIndex(); zorluk seviyesini al
				//oyunu baþlat
			}else if (e.getSource()==btnDevam) {
				//devam et
				OyunKontrol.Durum=Oyun_State.Basladi;
				frame.setVisible(false);
			}else if (e.getSource()==cbSeviye) {
				//JOptionPane.showConfirmDialog(null, cbSeviye.getSelectedIndex());
				//cbSeviye.getSelectedIndex();
			}else if (e.getSource()==btnHakkinda) {
				//hakkýnfa
			}else if (e.getSource()==btnCikis) {
				System.exit(0);
			}

	}
}
