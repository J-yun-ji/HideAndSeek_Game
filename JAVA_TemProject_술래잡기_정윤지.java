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
	Image bufferImage; // 화면의 이미지를 얻어올 그래픽 객체를 생성, 버퍼 이미지 객체와.
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
    
	int Sponge_X, Sponge_Y;	// 스폰지밥 위치
	int Sponge_Width = Sponge.getWidth(null);
	int Sponge_Height = Sponge.getHeight(null);	// 스폰지밥 가로, 세로 크기
	int Patrick_X, Patrick_Y;	// 뚱이 위치
	int Patrick_Width = Patrick.getWidth(null);
	int Patrick_Height = Patrick.getHeight(null);	// 뚱이 가로, 세로 크기
	
	int COUNT = 0;	// 잡은 횟수
	 
	private boolean key_up, key_down, key_left, key_right;	// 키 눌림
	// 불린으로 안하면 키 두개의 입력을 못 받아들인다.  
	
	public test() {
		setTitle("'뚱이' 잡기 GAME!"); // 프레임 타이틀.
		setVisible(true); // 
		setSize(850, 500);
		setLocationRelativeTo(null); 
		setResizable(false); // 프레임 창 크기를 변경하지 못하게 고정.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		setContentPane(panel); 
		
		addKeyListener(new KeyAdapter() { // 키 리스너
			public void keyPressed(KeyEvent e) { // 키를 눌렀을 때
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
			public void keyReleased(KeyEvent e) { // 키를 떼었을 때
				switch(e.getKeyCode()) { // 입력된 키 값 저장
				case KeyEvent.VK_UP : // 방향키가 UP일 때
					key_up = false;
					break;
				case KeyEvent.VK_DOWN : // 방향키가 DOWN일 때
					key_down = false;
					break;
				case KeyEvent.VK_LEFT : // 방향키가 LEFT일 때
					key_left = false;
					break;
				case KeyEvent.VK_RIGHT : // 방향키가 RIGTH일 때
					key_right = false;
					break;
				}
			}
			
		});	// 키보드 움직임 처리를 위한 키리스너 부착
	 
		while (true) { // 만든 메소드들을 계속해서 반복해준다. 
			// 대기 시간 없이 계속 반복하면 너무 빨라질 수 있으므로 살짝의 대기시간을 줌.
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
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			File audioFile = new File("C:\\Users\\Yunji\\Desktop\\end.wav"); // 오디오 파일의 경로명
			AudioInputStream audioStream =
				AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream); // 재생할 오디오 스트림 열기
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
            g.drawImage(imback1, 0, 0, getWidth(), getHeight(), this); // 배경화면
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
            g.drawImage(imback1, 0, 0, getWidth(), getHeight(), this); // 배경화면
            g.drawImage(imspon, 200, 600, 200, 100, this);
            
        }
    }
	
	
	public void keyMove() { // 불린 값으로 uo, down ... 플레이어 이동시킬 메소드. (사용자 정의 
		// 플레이어의 가로,세로길이,이동거리 등을 고려.
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
	}	// 플레이어 움직임
	
	public void p_Catch() { // 스폰지밥이 뚱이를 잡았는지 체크  
		if (Sponge_X + Sponge_Width > Patrick_X && Patrick_X + Patrick_Width > Sponge_X 
				&& Sponge_Y + Sponge_Height > Patrick_Y && Patrick_Height + Patrick_Y > Sponge_Y) {
			
			COUNT += 1; // 닿았을 때 잡은 횟수 올려주고 뚱이 위치 이동. 
		
			Patrick_X = (int)(Math.random()*(851-Sponge_Width));
			Patrick_X = (int)(Math.random()*(501-Sponge_Height-30))+30;
			// 이 메소드를 위의 while문에 넣어줌.
		}
	}	

	class MyPanel1 extends JPanel {
		public void paint(Graphics g) { // 화면 크기의 버퍼 이미지를 생성. getGraphics()를 통해 그래픽을 가져옴.
			bufferImage = createImage(850, 500);
			screenGraphic = bufferImage.getGraphics();
			paintComponent(screenGraphic);  // screenDraw호출하고 이 버퍼이미지를 화면에 그려주는 작업이 반복.
			g.drawImage(bufferImage, 0, 0, null);
		}	// 화면 깜빡임 현상 발생. 해결법 -> 더블 버퍼링 : 버퍼 이미지를 통해 화면의 깜빡임을 최소화.
		
		
		public void paintComponent(Graphics g) { // 사용자 정의
			g.drawImage(Background, 0, 0, null); // (이미지, x좌표, y좌표, null)
			g.drawImage(Patrick, Patrick_X, Patrick_Y, null); // 좌표 값에는 playerX, plaY, coinX, coinY넣기
			g.drawImage(Sponge, Sponge_X, Sponge_Y, null);
			g.setColor(Color.PINK); // 점수 색.
			g.setFont(new Font("ITALIC", Font.BOLD, 30)); // 폰트, 글자 크기 설정.
			g.drawString("" + COUNT, 430, 80); // 출력문자, x좌표, y좌표
			// 아까 y처럼 프레임 틀의 길이를 생각해 y좌표를 더크게해줌.
			this.repaint(); // 안하면 이동 안됌
		}
	}
	
	public static void main(String[] args) {
		new test();
	}

}