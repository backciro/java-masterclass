import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import grocerystore.*;


@SuppressWarnings({ "serial", "unused" })
public class CounterFrame extends JFrame {
  public CounterFrame(String title)  {
	  super(title);
	  Container container = getContentPane();
	  container.setLayout( new FlowLayout() );
	  JLabel product = new JLabel("product code:");
	  container.add( product );
	  JTextField code = new JTextField( "P001",4 );
	  container.add( code );
	  JLabel number = new JLabel("number of pieces:");
	  container.add( number );
	  JTextField num = new JTextField("2", 3 );
	  container.add( num );
	  JButton read = new JButton("Read");
	  container.add( read );
	  JButton print = new JButton("Print Invoice");
	  container.add( print );
	  JButton reset = new JButton("Reset Counter");
	  container.add( reset );
	  JTextArea invoice = new JTextArea("*****************************\n 2 x Coffee	> 6.0\n 1 x Olive oil	> 2.0\n 3 x Tuna	> 4.5\n\nTotal:	> 12.5\nVAT 20%	> 2.5\n\nGrossTotal	> 15.0\n*****************************\n	", 10,25);
	  container.add( new JScrollPane(invoice) );    
	  setDefaultCloseOperation(DISPOSE_ON_CLOSE);	    
	  setSize(400, 300);
	  setResizable(false);
	  setVisible(true);
  }
	 
  public static void main(String[] args) {
	CounterFrame frame = new CounterFrame("Counter Check out");
  }
}
