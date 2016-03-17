import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class test  extends JFrame
{
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;
	private JTextField b1;
	private JTextField b2;
	private JTextField b3;
	private JTextField b4;
	private JButton c1;
	private JButton c2;
	private JButton c3;
	private JButton c4;
	private JTextArea text;
	public test()
	{
		creatComponents()	;
		layoutComponents();
		registerHandlers();
		setTitle("存款应用程序");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	private void  creatComponents()	
	{
		a1=new JLabel("本  金 ");
		a1.setFont(new Font("宋体",Font.BOLD,18));
		a2=new JLabel("年利率 ");
		a2.setFont(new Font("宋体",Font.BOLD,18));
		a3=new JLabel("年 数 ");
		a3.setFont(new Font("宋体",Font.BOLD,18));
		a4=new JLabel("本息 ");
		a4.setFont(new Font("宋体",Font.BOLD,18));
		
		
		
		b1=new JTextField(10);
		b2=new JTextField(10);
		b3=new JTextField(10);
		b4=new JTextField(10);
		c1=new JButton("复利");
		c1.setFont(new Font("宋体",Font.BOLD,15));
		c3=new JButton("单利");
		c3.setFont(new Font("宋体",Font.BOLD,15));
		c4=new JButton("定投");
		c4.setFont(new Font("宋体",Font.BOLD,15));
		c2=new JButton("清除");
		c2.setFont(new Font("宋体",Font.BOLD,15));
		text=new JTextArea();
	}
	private void layoutComponents()
	{
		//Container c=this.getContentPane();	
		setLayout(new FlowLayout());
		JPanel panel1=new JPanel();
		panel1.add(a1);
		panel1.add(b1);
		JPanel panel2=new JPanel();
		panel2.add(a2);
		panel2.add(b2);
		JPanel panel3=new JPanel();
		panel3.add(a3);
		panel3.add(b3);
		JPanel panel4=new JPanel();
		panel3.add(a4);
		panel3.add(b4);
		JPanel panel5=new JPanel();
		panel4.add(c1);
		panel4.add(c3);
		panel4.add(c4);
		panel4.add(c2);
		JPanel  leftpanel=new JPanel(new GridLayout(6,2));
		leftpanel.add(panel1);
		leftpanel.add(panel2);
		leftpanel.add(panel3);
		leftpanel.add(panel4);
		leftpanel.add(panel5);
		JScrollPane panel6=new JScrollPane(text);
		panel6.setPreferredSize(new Dimension(250,100));
		add(leftpanel);
		add(panel6);
	}
	private  void registerHandlers()
	{
		 c1ActionEventHander hander1=new  c1ActionEventHander();
		 c1.addActionListener(hander1);
		 c2ActionEventHander hander2=new  c2ActionEventHander();
		 c2.addActionListener(hander2);
	c3ActionEventHander hander3=new  c3ActionEventHander();
		c3.addActionListener(hander3);
		c4ActionEventHander hander4=new  c4ActionEventHander();
		c4.addActionListener(hander4);
	 
	}
	private class c1ActionEventHander implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			double principal;
			double amount;
			double rate;
		    int n;
		    NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
		    String output="年"+"/t"+"复利存款"+"/n";
		     int year =1;
		   //  principal=Double.parseDouble(b1.getText());
		     rate=Double.parseDouble(b2.getText()); 
		     n=Integer.parseInt(b3.getText());
		     if(b1.getText().equals(""))
		     {
		    	 if (b4.getText().equals(""))
		    	 {
		    		 JOptionPane.showMessageDialog(null, "请输入本金或者本息");
				
		    	 }
		    	 else{
				amount = Double.parseDouble(b4.getText());
				principal=0;
				while (year <= n) {
					principal=amount/(Math.pow(1 + rate, year));
					year = year + 1;
				}
				
				output="本金"+currencyformatter.format(principal) + "\n";
				text.setText(output);
			}
		}
		else {
			principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
			
			if (b4.getText().equals("")) {
				while (year <= n) {
					amount = principal * Math.pow(1 + rate, year);
					output += String.valueOf(year) + "\t"+ currencyformatter.format(amount) + "\n";
					year = year + 1;
				}

				text.setText(output);
				}
			else if (b3.getText().equals("")) {
				amount = Double.parseDouble(b4.getText());
				while (year <= n) {
					rate=Math.pow(amount/principal,1/year)-1;
					year = year + 1;
				}
				
				output="本金"+currencyformatter.format(principal) + "\n";
				text.setText(output);
			}
			else{
				JOptionPane.showMessageDialog(null, "本金和期望值输入一个即可");
			}
		}
}
}
	private class  c2ActionEventHander implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			b1.setText("");
			b2.setText("");
			b3.setText("");
			text.setText("");
		}
	}
	private class c3ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal;
			double amount;
			double rate;
			int n;
			NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();
			String output = "年" + "/" + "单利存款";
			int year = 1;
			
			rate = Double.parseDouble(b2.getText());
			n = Integer.parseInt(b3.getText());
			if(b1.getText().equals(""))
			{
				if (b4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "请输入本金或者期望值");
					
				}
				else{
					amount = Double.parseDouble(b4.getText());
					principal=0;
					while (year <= n) {
						principal = amount / (1 + rate* year);
						year = year + 1;
					}
					
					output="本金"+currencyformatter.format(principal) + "\n";
					text.setText(output);
				}
			}
			else {
				principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
				
				if (b4.getText().equals("")) {
					while (year <= n) {
						amount = principal * (1 + rate* year);
						output += String.valueOf(year) + "\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}
	
					text.setText(output);
					}
			else if (b3.getText().equals("")) {
				amount = Double.parseDouble(b4.getText());
				while (year <= n) {
					rate=amount/principal/year-1;
					year = year + 1;
				}
				
				output="本金"+currencyformatter.format(principal) + "\n";
				text.setText(output);
			}
				else{
					JOptionPane.showMessageDialog(null, "本金和期望值输入一个即可");
				}
			}
			
		}
	}
	private class c4ActionEventHander implements ActionListener
	{
	public void actionPerformed(ActionEvent e)
	{
		double principal;//本金
		double amount;//本息
		double rate;//利率
	    int n;//年数
	    NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
	    String output="年"+"/t"+"存款"+"/n";
	     int year =1;
	     amount=Double.parseDouble(b4.getText());
	     rate=Double.parseDouble(b2.getText()); 
	     n=Integer.parseInt(b3.getText());
	     principal=amount/Math.pow(1+rate,year);
	    output=String.valueOf(year)+"\t"+currencyformatter.format(principal)+"\n";
	     text.setText(output);
	    
	}
	}
	public static void main(String[] args)
	{
		 test frame=new test();
		frame.setVisible(true);
		frame.setSize(600,450); 
	  frame.setResizable(false);
	}
}

