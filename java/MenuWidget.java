import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;



public class MenuWidget {
  private JMenuBar      menuBarObj;
  private JMenu[]	      menuObj;
	private JMenuItem[][] menuItemObj;
  private String[]      menuNameList;
	private String[][]    menuItemNameList;

  public MenuWidget() {
	  menuBarObj       = null;
		menuObj          = null;
		menuItemObj      = null;
		menuNameList     = null;
		menuItemNameList = null;
	}/*MenuWidget*/

	public MenuWidget(String[]    menuName,
									  String[][]  menuItem) {
    int menuIdx;
    int menuItemIdx;

		/*Allocating Memory*/
		menuObj     = new JMenu[menuName.length];
		menuItemObj = new JMenuItem[menuName.length][menuItem.length];

    menuBarObj  = new JMenuBar();
    for(menuIdx = 0; menuIdx < menuName.length; menuIdx++) {
		  menuObj[menuIdx] = new JMenu(menuName[menuIdx]);
      for(menuItemIdx = 0; menuItemIdx < menuItem[menuIdx].length; menuItemIdx++) {
			  menuItemObj[menuIdx][menuItemIdx] = new JMenuItem(menuItem[menuIdx][menuItemIdx]);
			  menuObj[menuIdx].add(menuItemObj[menuIdx][menuItemIdx]);
				/*Adding Action Listener to MenuItem*/
				menuItemObj[menuIdx][menuItemIdx].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
           processMenuSelectionRequest(ae); 									
					}/*actionPerformed*/
				});
			}
	    menuBarObj.add(menuObj[menuIdx]);	
		}	
	}/*MenuWidget*/

	public void processMenuSelectionRequest(ActionEvent ae) {
	 Widget.getDisplayWindow().append(ae.getActionCommand() + "\n");
	 processSelectedOption(ae.getActionCommand());
	 //Widget.getDisplayWindow().append(ae.getSource() + "\n"); 
	}/*processMenuSelectionRequest*/

	public void processSelectedOption(String optionSelected) {
	  
    int row=0,col=2,hGap=3,vGap=5;

    JFrame window = new JFrame(optionSelected);

		//JPanel panel  = new JPanel(new GridLayout(row,col,hGap,vGap));
		JPanel panel  = new JPanel(new BorderLayout());

		String[] columnName = {"First Name",
						               "Last Name",
													 "Sports"
		                      };

		JLabel label1 = new JLabel("LABEL1:");
		JButton jBtn  = new JButton("OK");
		JTextField txtField = new JTextField(30);

		panel.add(label1, BorderLayout.LINE_START);
		panel.add(jBtn, BorderLayout.LINE_START);
		panel.add(txtField, BorderLayout.LINE_START);
		window.add(panel);
		Object[][] data = {{"Naushad","Ahmed","Cricket"},
						           {"",new Boolean(true), label1}
		                  };
		JTable jTable = new JTable(data, columnName);
		TableColumn tc1 = jTable.getColumnModel().getColumn(0);
		//panel.add(jTable.getTableHeader(), BorderLayout.PAGE_START);
		//panel.add(jTable, BorderLayout.CENTER);
		window.pack();
		window.setSize(400,300);
		window.setVisible(true);

		window.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent we) {
			  //System.exit(0);
			}
		});
	}/*processSelectedOption*/
	public void processSelectedOptionEx(String optionSelected) {
    
    JFrame window = new JFrame(optionSelected);
		JPanel panel  = new JPanel(new BorderLayout());

		String[] columnName = {"First Name",
						               "Last Name",
													 "Sports"
		                      };

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets  = new Insets(0,0,0,0);
    //gbc.ipadx   = 10;
		//gbc.ipady   = 10;
		//gbc.gridy   = 10;
		JLabel label1 = new JLabel("LABEL1");
		JButton jBtn  = new JButton("OK");
		JTextField txtField = new JTextField(30);

		panel.add(label1);
		panel.add(jBtn);
		panel.add(txtField);
		window.add(panel);
		Object[][] data = {{"Naushad","Ahmed","Cricket"},
						           {"",new Boolean(true), label1}
		                  };
		JTable jTable = new JTable(data, columnName);
		TableColumn tc1 = jTable.getColumnModel().getColumn(0);
		tc1.setCellEditor(new DefaultCellEditor(txtField));
		tc1.setIdentifier("Hello World");
		panel.add(jTable.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(jTable, BorderLayout.CENTER);
		window.pack();
		window.setSize(400,300);
		window.setVisible(true);

		window.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent we) {
			  //System.exit(0);
			}
		});
	}/*processSelectedOption*/


	public JMenuBar getMenuBar() {
	  return menuBarObj;
	}/*getMenuBar*/
}
