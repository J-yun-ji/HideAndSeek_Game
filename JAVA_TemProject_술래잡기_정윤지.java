import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class test extends JFrame {
	Image bufferImage; // ȭ���� �̹����� ���� �׷��� ��ü�� ����, ���� �̹��� ��ü��.
	Graphics screenGraphic; 
	
	Image Background = new ImageIcon("C:\\\\Users\\\\Yunji\\\\Desktop\\\\dgs1.jpg").getImage();
	Image Sponge = new ImageIcon("C:\\\\Users\\\\Yunji\\\\Desktop\\\\sss.png").getImage();
	Image Patrick = new ImageIcon("C:\\\\Users\\\\Yunji\\\\Desktop\\\\pkk.png").getImage();
	
	ImageIcon spon = new ImageIcon("C:\\Users\\Yunji\\Desktop\\ss.png");
	Image imspon = spon.getImage();
	Image change = imspon.getScaledInstance(150, 150, Image.SCALE_SMOOTH );
	
	Clip clip;


	ImageIcon back1 = new ImageIcon("C:\\Users\\Yunji\\Desktop\\lolo.png");
    Image imback1 = back1.getImage();
	
    private MyPanel1 panel = new MyPanel1();
    private MyPanel2 pane2 = new MyPanel2();
    
	int Sponge_X, Sponge_Y;	// �������� ��ġ
	int Sponge_Width = Sponge.getWidth(null);
	int Sponge_Height = Sponge.getHeight(null);	// �������� ����, ���� ũ��
	int Patrick_X, Patrick_Y;	// ���� ��ġ
	int Patrick_Width = Patrick.getWidth(null);
	int Patrick_Height = Patrick.getHeight(null);	// ���� ����, ���� ũ��
	
	int COUNT = 0;	// ���� Ƚ��
	 
	private boolean key_up, key_down, key_left, key_right;	// Ű ����
	// �Ҹ����� ���ϸ� Ű �ΰ��� �Է��� �� �޾Ƶ��δ�.  
	
	public test() {
		setTitle("'����' ��� GAME!"); // ������ Ÿ��Ʋ.
		setVisible(true); // 
		setSize(850, 500);
		setLocationRelativeTo(null); 
		setResizable(false); // ������ â ũ�⸦ �������� ���ϰ� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		setContentPane(panel); 
		
		addKeyListener(new KeyAdapter() { // Ű ������
			public void keyPressed(KeyEvent e) { // Ű�� ������ ��
				switch(e.getKeyCode()) {
				case KeyEvent.VK_UP :
					key_up = true;
					break;
				case KeyEvent.VK_DOWN :
					key_down = true;
					break;
				case KeyEvent.VK_LEFT :
					key_left = true;
					break;
				case KeyEvent.VK_RIGHT :
					key_right = true;
					break;
				}
			}
			public void keyReleased(KeyEvent e) { // Ű�� ������ ��
				switch(e.getKeyCode()) { // �Էµ� Ű �� ����
				case KeyEvent.VK_UP : // ����Ű�� UP�� ��
					key_up = false;
					break;
				case KeyEvent.VK_DOWN : // ����Ű�� DOWN�� ��
					key_down = false;
					break;
				case KeyEvent.VK_LEFT : // ����Ű�� LEFT�� ��
					key_left = false;
					break;
				case KeyEvent.VK_RIGHT : // ����Ű�� RIGTH�� ��
					key_right = false;
					break;
				}
			}
			
		});	// Ű���� ������ ó���� ���� Ű������ ����
	 
		while (true) { // ���� �޼ҵ���� ����ؼ� �ݺ����ش�. 
			// ��� �ð� ���� ��� �ݺ��ϸ� �ʹ� ������ �� �����Ƿ� ��¦�� ���ð��� ��.
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			keyMove();
			p_Catch();
		}
	}
	private void loadAudio(String pathName) {
		try {
			clip = AudioSystem.getClip(); // ����ִ� ����� Ŭ�� �����
			File audioFile = new File("C:\\Users\\Yunji\\Desktop\\end.wav"); // ����� ������ ��θ�
			AudioInputStream audioStream =
				AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream); // ����� ����� ��Ʈ�� ����
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}

	class MyPanel extends JPanel
    {
        MyPanel() { }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(imback1, 0, 0, getWidth(), getHeight(), this); // ���ȭ��
            g.drawImage(imspon, 200, 600, 200, 100, this);
           
        }
    }
	class MyPanel2 extends JPanel
    {
        MyPanel2()
        { 
            setLayout(new FlowLayout()); 
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(imback1, 0, 0, getWidth(), getHeight(), this); // ���ȭ��
            g.drawImage(imspon, 200, 600, 200, 100, this);
            
        }
    }
	
	
	public void keyMove() { // �Ҹ� ������ uo, down ... �÷��̾� �̵���ų �޼ҵ�. (����� ���� 
		// �÷��̾��� ����,���α���,�̵��Ÿ� ���� ���.
		if (key_up && Sponge_Y - 5 > 40) {
			Sponge_Y -= 4; 
		}
		if (key_down && Sponge_Y + Sponge_Height + 4 < 500) {
			Sponge_Y += 4;
		}
		if (key_left && Sponge_X - 4 > 0) {
			Sponge_X -= 4;
		}
		if (key_right && Sponge_X + Sponge_Width + 4 < 850) {
			Sponge_X += 4;
		}
	}	// �÷��̾� ������
	
	public void p_Catch() { // ���������� ���̸� ��Ҵ��� üũ  
		if (Sponge_X + Sponge_Width > Patrick_X && Patrick_X + Patrick_Width > Sponge_X 
				&& Sponge_Y + Sponge_Height > Patrick_Y && Patrick_Height + Patrick_Y > Sponge_Y) {
			
			COUNT += 1; // ����� �� ���� Ƚ�� �÷��ְ� ���� ��ġ �̵�. 
		
			Patrick_X = (int)(Math.random()*(851-Sponge_Width));
			Patrick_X = (int)(Math.random()*(501-Sponge_Height-30))+30;
			// �� �޼ҵ带 ���� while���� �־���.
		}
	}	

	class MyPanel1 extends JPanel {
		public void paint(Graphics g) { // ȭ�� ũ���� ���� �̹����� ����. getGraphics()�� ���� �׷����� ������.
			bufferImage = createImage(850, 500);
			screenGraphic = bufferImage.getGraphics();
			paintComponent(screenGraphic);  // screenDrawȣ���ϰ� �� �����̹����� ȭ�鿡 �׷��ִ� �۾��� �ݺ�.
			g.drawImage(bufferImage, 0, 0, null);
		}	// ȭ�� ������ ���� �߻�. �ذ�� -> ���� ���۸� : ���� �̹����� ���� ȭ���� �������� �ּ�ȭ.
		
		
		public void paintComponent(Graphics g) { // ����� ����
			g.drawImage(Background, 0, 0, null); // (�̹���, x��ǥ, y��ǥ, null)
			g.drawImage(Patrick, Patrick_X, Patrick_Y, null); // ��ǥ ������ playerX, plaY, coinX, coinY�ֱ�
			g.drawImage(Sponge, Sponge_X, Sponge_Y, null);
			g.setColor(Color.PINK); // ���� ��.
			g.setFont(new Font("ITALIC", Font.BOLD, 30)); // ��Ʈ, ���� ũ�� ����.
			g.drawString("" + COUNT, 430, 80); // ��¹���, x��ǥ, y��ǥ
			// �Ʊ� yó�� ������ Ʋ�� ���̸� ������ y��ǥ�� ��ũ������.
			this.repaint(); // ���ϸ� �̵� �ȉ�
		}
	}
	
	public static void main(String[] args) {
		new test();
	}

}