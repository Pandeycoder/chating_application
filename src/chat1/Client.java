package chat1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import javax.swing.JFrame;
public class Client extends Frame implements ActionListener,Runnable{
	Button b1;
	TextField tf;
	TextArea ta;
	Socket s;
	PrintWriter pw1;
	PrintWriter pw;
	BufferedReader br;
	Thread th;
	public Client()
	{
		JFrame f=new JFrame("Client side chatting");
		tf=new TextField(15);
		ta=new TextArea(10,25);
		ta.setBackground(new Color(116,126,217));
		f.add(ta);
		f.setLayout(new FlowLayout());
		f.setBackground(Color.cyan);
		
		b1=new Button("Send");
		b1.addActionListener(this);
		b1.setBackground(new Color(88,150,73));
		f.addWindowListener(null);
		f.add(tf);
		f.add(b1);
		try
		{
			s=new Socket(InetAddress.getLocalHost(),1200);
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		    pw=new PrintWriter(s.getOutputStream(),true);
		   // pw1=new PrintWriter(s.getInputStream(),true);
			
		}catch(Exception e)
		{
			
		}
		th=new Thread(this);
		th.setDaemon(true);
		th.start();
		setFont(new Font("Arial",Font.BOLD,20));
		f.setBounds(200,200,300,300);
		f.setVisible(true);
		f.validate();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private class W1 extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		pw.println(tf.getText());
		//pw1.Println(tf.getText());
		pw1.println(tf.getText());
		ta.setText(" ");
		tf.setText("");
	}
	public void run()
	{
		while(true)
		{
			try {
				ta.append(br.readLine()+"\n");
				//tf.append(br.readLine()+"\n");
				
			}catch(Exception e)
			{
				
			}
		}
	}
	public static void main(String[] args)throws Exception {
		Client sp=new Client();
	}

	}

