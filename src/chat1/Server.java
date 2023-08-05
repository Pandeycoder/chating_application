package chat1;
import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

import java.awt.event.*;
public class Server extends Frame implements ActionListener,Runnable {
	Button b1;
	TextField tf;
	TextArea ta;
	ServerSocket ss;
	Socket s;
	PrintWriter pw;
	BufferedReader br;
	Thread th;
	
	public Server()
	{
	
		JFrame f=new JFrame("Server Side chatting");
		f.setLayout(new FlowLayout());
		f.setBackground(Color.orange);
		b1=new Button("Send");
		b1.setBackground(Color.pink);
		b1.addActionListener(this);
		tf=new TextField(15);
		ta=new TextArea(12,20);
		ta.setBackground(Color.cyan);
		
		f.addWindowListener(null);
		f.add(tf);
		f.add(b1);
		f.add(ta);
		try
		{
			ss=new ServerSocket(1200);
			s=ss.accept();
			br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw=new PrintWriter(s.getOutputStream(),true);
			
		}
		catch(Exception e)
		{
			System.err.print(e);
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
		tf.setText("");
	}
	public void run()
	{
		while(true)
		{
			try {
				ta.append(br.readLine()+"\n");
				
			}catch(Exception e)
			{
				
			}
		}
	}
	public static void main(String[] args)throws Exception {
		Server sp=new Server();
	}

}
