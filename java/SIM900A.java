import javax.swing.*;
import java.awt.*;
import javax.swing.JMenuBar.*;
import javax.swing.JMenu.*;
import javax.swing.JMenuItem.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import javax.swing.SpringLayout;




/**
 * Class Definition of Tabbed Pane 
 * */

public class SIM900A {

	public static void main(String[] args) {
    int            idx           = 0;
    Tabbed         tabbed        = new Tabbed();					
    JFrame         frame         = new JFrame("SIM900");
    CommandHistory historyWindow = new CommandHistory();

		frame.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
        System.exit(0);							
			}				
		});
		/*Creating MenuBar*/
		/*
		String[] menuNameList = {"File",
						                 "View",
														 "AT",
														 "Window"
		                        };
		String[][] menuItemList = {{"Item1", "Item2"},
						                   {"Item3", "Item4"},
						                   {"Item5", "Item6"},
						                   {"Item7", "Item8","Item9"}
		                          };
		MenuWidget menuBar = new MenuWidget(menuNameList, menuItemList);
    */
		JTabbedPane tabbedPane  = tabbed.createTabbedPane();
    JPanel      displayArea = historyWindow.createCommandHistory();
    
    //frame.setJMenuBar(menuBar.getMenuBar());
		frame.add(tabbedPane);
    frame.add(displayArea);
		/*Creating the Window splitter/separater*/
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
										                tabbedPane,
																		displayArea);
		jsp.setOneTouchExpandable(true);
		jsp.setDividerLocation(0.25);
		frame.add(jsp);

		/*If not packed then last added component will be displayed*/
		frame.pack();
    frame.setVisible(true);	
		/*static member function for adding text to Output Window*/
    Widget.setDisplayWindow(historyWindow.getDisplayWindowObject());
		
		UdpClient uClient = new UdpClient();
		Widget.setUdpObject(uClient);

    tabbedPane.addChangeListener(tabbed.getChangeListener());
	}/*main*/
}
