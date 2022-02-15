import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacTeo {
	JFrame fr=new JFrame("TicTacTeo");
	JPanel[] pa=new JPanel[3];
	JLabel la=new JLabel("First Player Turn");
	JButton[] bt=new JButton[9];
	JButton bt1=new JButton("Restart");
	JLabel img=new JLabel(new ImageIcon(getClass().getResource("images/t2.jpg")));
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	int user=1;
	int count=0;
	boolean reset=false;
	boolean winnerfound=false;
	public TicTacTeo(){
		fr.setSize(500,500);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.add(img);
		addPanels();
		fr.setVisible(true);
	}
	private void addPanels()
	{
		img.setLayout(null);
		for(int i=0;i<3;i++)
		{	
			pa[i]=new JPanel();
			img.add(pa[i]);
		}
		pa[0].setBounds(30,20,440,30);
		pa[0].add(la);
		la.setFont(new Font("elephant",Font.BOLD,20));
		la.setForeground(Color.blue);
		pa[1].setBounds(30,70,440,350);
		pa[2].setBounds(30,430,400,35);
		addButtons();
		
	}
	private void addButtons()
	{
		TicListner listner=new TicListner();
		pa[1].setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++)
		{
			bt[i]=new JButton();
			bt[i].addActionListener(listner);
			pa[1].add(bt[i]);
			bt[i].setBackground(Color.yellow);
		}
		bt1.setFont(new Font("elephant",Font.BOLD,20));
		bt1.setForeground(Color.red);
		ResetListner listner1=new ResetListner();
		bt1.addActionListener(listner1);
		pa[2].add(bt1);
		pa[2].setOpaque(false);
		
	}
	class TicListner implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) {
			JButton bb=(JButton)evt.getSource();
			if(winnerfound)
			{
				return;
			}
				
			if(user==1)
			{
				bb.setIcon(icon1);
				la.setText("Second player turn");
				user=2;
				findWinner(icon1);
			}
			else if(user==2)
			{
				bb.setIcon(icon2);
				la.setText("First player turn");
				user=1;
				findWinner(icon2);
			}
		
			bb.setEnabled(false);
			count++;
			if(count==9 && !winnerfound)
			{
				la.setText("Match tie.. ");
				la.setForeground(Color.red);
			}
			
			
		}
	
	public  void findWinner(ImageIcon icon)
	{
		if(bt[0].getIcon()==icon && bt[1].getIcon()==icon && bt[2].getIcon()==icon)//First row
			announceWinner(0,1,2,icon);
		if(bt[3].getIcon()==icon && bt[4].getIcon()==icon && bt[5].getIcon()==icon)	
			announceWinner(3,4,5,icon);
		if(bt[6].getIcon()==icon && bt[7].getIcon()==icon && bt[8].getIcon()==icon)
			announceWinner(6,7,8,icon);
		if(bt[1].getIcon()==icon && bt[4].getIcon()==icon && bt[7].getIcon()==icon)
			announceWinner(1,4,7,icon);
		if(bt[0].getIcon()==icon && bt[3].getIcon()==icon && bt[6].getIcon()==icon)
			announceWinner(0,3,6,icon);
		if(bt[2].getIcon()==icon && bt[5].getIcon()==icon && bt[8].getIcon()==icon)
			announceWinner(2,5,8,icon);
		if(bt[0].getIcon()==icon && bt[4].getIcon()==icon && bt[8].getIcon()==icon)
			announceWinner(0,4,8,icon);
		if(bt[2].getIcon()==icon && bt[4].getIcon()==icon && bt[6].getIcon()==icon)
			announceWinner(2,4,6,icon);
	}
	private void announceWinner(int i,int j,int k,ImageIcon icon)
	{
		if(icon==icon1)
		{
			la.setText("First player won");
		}
		else {
		la.setText("Second player won");
		}
		la.setForeground(Color.blue);
		bt[i].setBackground(Color.green);
		bt[j].setBackground(Color.green);
		bt[k].setBackground(Color.green);
		winnerfound=true;
	}
	
	}
	class ResetListner implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) {
			for(int i=0;i<9;i++)
			{
				bt[i].setIcon(null);
				bt[i].setBackground(Color.yellow);
				bt[i].setEnabled(true);
			}
			la.setText("First player turn");
			la.setForeground(Color.red);
			user=1;
			count=0;
			winnerfound=false;
			
		
		}
		
	}

	public static void main(String[] args) {
		new TicTacTeo();
	}
}
