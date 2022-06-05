




import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import javax.swing.SpringLayout;


/**
 * Class definition of History/Output Window
 * */

public class CommandHistory {
	/*private data member*/				
  	private   String    historyText;
  	private   int       textAreaColumn;
	private   int       textAreaRow;
	private   JTextArea displayWindow;
	private   JPanel    displayWindowContainer;

	/*Default constructor*/
	public CommandHistory() {

		historyText            = null;
		textAreaColumn         = 700;
		textAreaRow            = 425;
		displayWindow          = null;
		displayWindowContainer = null;
	}

	public JTextArea getDisplayWindowObject() {
		return displayWindow;
	}/*getDisplayWindowObject*/
	
	public void setHistoryText(String text) {
		historyText = text;
	}	

	public String getHistoryText() {
		return historyText;
	}
  	public void finalize() {
    	
		historyText            = null;
		displayWindow          = null;
		displayWindowContainer = null;
		/*Invoking garbage collection*/
		System.gc();
	}

  	public void addTextToDisplayWindow(String outPutText) {
		/*Adding Text to Output Windoq*/
	  	displayWindow.append(outPutText);

	}/*addTextToDisplayWindow*/

	public JPanel createCommandHistory() {

	  	displayWindow          = new JTextArea();
    
		/*Panel will hosts the TextArea*/
		displayWindowContainer = new JPanel(new GridLayout(1,1));
		/*Making displayWindow non-editable*/
		displayWindow.setEditable(false);
    	displayWindow.setFont(new Font("Serif", Font.ITALIC, 15));
    	displayWindow.setLineWrap(true);
    	displayWindow.setWrapStyleWord(true);
		/*Setting the Text Color and Window's background*/
		displayWindow.setForeground(Color.BLUE);
		displayWindow.setBackground(Color.WHITE);

		/*Adding scrollbar to display window*/
		JScrollPane scrollBar = new JScrollPane(displayWindow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollBar.setPreferredSize(new Dimension(textAreaColumn,textAreaRow));
    	/*Adding scrollbar to JPanel*/
		displayWindowContainer.add(scrollBar);

		displayWindowContainer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "History Window"));
    	return displayWindowContainer;
	}/*createCommandHistory*/
}
