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

public class Tabbed {
	private String[]       tabbedText;
	private int            tabCount;
	private Component[]    component;
	private ChangeListener changeListener;
	private static int     activeTabbedIndex;

	/**
	 * Constructor with no argument
	 * */
  	public Tabbed() {
    	tabbedText    = new String[8];
    	tabbedText[0] = "AT Interface Provisioning";
    	tabbedText[1] = "AT Test Command";
    	tabbedText[2] = "AT Read Command";
    	tabbedText[3]	= "AT Write Command";
	  	tabbedText[4] = "AT Execution Command";
	  	tabbedText[5] = "SMS(Short Message Services)";
	  	tabbedText[6] = "CSIM(Generic SIM Access)";
	  	tabbedText[7] = "CRSM(Restricted SIM Access)";
	  	/*Default tab count*/	
		tabCount      = tabbedText.length;
		component     = new Component[tabbedText.length];
		
		/*Adding changeListener to TabbedPanel*/
    	changeListener = new ChangeListener() {
      		public void stateChanged(ChangeEvent changeEvent) {
        		JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        		int index = sourceTabbedPane.getSelectedIndex();
				setActiveTabbedIndex(index);
        		//System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
      		}
    	};
	}/*Tabbed*/

	public static int getActiveTabbedIndex() {
		return activeTabbedIndex;
	}/*getActiveTabbedIndex*/


	public static void setActiveTabbedIndex(int tabbedIndex) {
		activeTabbedIndex = tabbedIndex;
	}/*setActiveTabbedIndex*/

  	public ChangeListener getChangeListener() {
		return changeListener;
	}

  	public int Tabbed(int maxTabbed, String[] tabbedTitle) {
    	int idx = 0;

    	if(maxTabbed <= 0) {
      		return -1;						
		}

		tabCount = maxTabbed;
		tabbedText = new String[maxTabbed];
    	component  = new Component[5];

		for(idx = 0; idx < tabbedText.length; idx++) {
      		tabbedText[idx] = tabbedTitle[idx];	
		}
		/*ChangeListener for Tabbed Panel*/
    	changeListener = new ChangeListener() {
      		public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        		int index = sourceTabbedPane.getSelectedIndex();
				setActiveTabbedIndex(index);
        		//System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
			}
    	};

		return 0;
	}/*Tabbed*/

  	public String getTabTitle(int idx) {

    	if((idx > tabCount) || (idx < 0)) {
      		return null;						
		}
    	return tabbedText[idx];
	}/*getTabTitle*/


	public int setTabTitle(int idx, String tabTitle) {
		if(idx < 0) {
    		return -1; 						
		}

    	tabbedText[idx] = tabTitle;
    	tabCount += idx;
		return 0;
	}/*setTabTitle*/


  public Component createComponent(int idx) {
    JPanel   jpanel    = new JPanel();

	switch(idx) {
    	case 0:
		{  							
			String[] AT_Configuration = {"Local IP Address", 
										 "Local Port",
										 "Remote IP Address", 
										 "Remote Port"};

		    Widget AT_w = new Widget(AT_Configuration);
			component[idx] = AT_w.buildATConfigurationUI(AT_Configuration);
		}
	    break;

		case 1:
		{ 
		    String[] labelName = {"AT Test Command", 
								  "Other AT Command"
								 };
			String[] widgetType = {"comboBox", 
								   "textBox"
								  };
        	/*If Widget Type is ComboBox then this is the list*/
			String[][] optionList  = {
				{"AT+GMI=?",
				 "AT+GMM=?",
				 "AT+GMR=?",
				 "AT+GOI=?",
				 "AT+GSN=?",
				 "AT+ICF=?",
				 "AT+IFC=?",
				 "AT+IPR=?",
				 "AT+CACM=?",
				 "AT+CMGS=?"
				}
			};

		    Widget tw = new Widget(labelName);
			component[idx] = tw.buildTestCommandUI(labelName, widgetType, optionList);
		}
       	break;

		case 2:
		{
		    String[] labelName = 
				{"AT Read Command", 
				 "Other AT Read Command"
				};
				
			String[] widgetType = 
				{"comboBox", 
				 "textBox"
				};

        	/*If Widget Type ix ComboBox then this is the list*/
			String[][] optionList  = 
				{
					{"AT+GMI?",
				     "AT+GMM?",
					 "AT+GMR?",
					 "AT+GOI?",
					 "AT+GSN?",
					 "AT+ICF?",
					 "AT+IFC?",
					 "AT+IPR?",
					 "AT+CACM?",
				     "AT+CMGS?"
				    }
				};
		    Widget tw = new Widget(labelName);
			component[idx] = tw.buildReadCommandUI(labelName, widgetType, optionList);
		}
		break;

		case 3:
		{
		    String[] labelName = {"AT Write Command"};
		    Widget tw = new Widget(labelName);
				component[idx] = tw.buildWriteCommandUI(labelName);
		}
		break;

		case 4:
		{
		    String[] labelName = 
				{"AT Execution Command", 
				 "Other AT Execution Command"
				};
				
			String[] widgetType = 
				{"comboBox", 
				 "textBox"
				};

        	/*If Widget Type ix ComboBox then this is the list*/
			String[][] optionList  = 
				{
					{"AT+GMI",
				     "AT+GMM",
					 "AT+GMR",
					 "AT+GOI",
					 "AT+GSN",
					 "AT+ICF",
					 "AT+IFC",
					 "AT+IPR",
					 "AT+CACM",
				     "AT+CMGS"
				    }
				};

		    Widget tw = new Widget(labelName);
			component[idx] = tw.buildExecutionCommandUI(labelName, widgetType, optionList);
		}
		break;
			
		case 5:
      	{
			String[] SMS_label = 
				{"AT Command",
				 "To Cell No", 
				 "SMS Text",
				 "Text/PDU Mode",
				 "Is Mode Change?"
				};

			String[] widgetType = 
				{"comboBox", 
				 "textBox", 
				 "textBox",
				 "comboBox",
				 "checkBox"
				};
        	/*If Widget Type ix ComboBox then this is the list*/
			String[][] optionList  = 
				{
					{"AT+CMGS",
				     "AT+CMGS=?"
					},
					{"AT+CMGF=1",
					 "AT+CMGF=0"
					}
				};

		    Widget SMS_w = new Widget(SMS_label);
			component[idx] = SMS_w.buildSMSUI(SMS_label, widgetType, optionList);
		}
		break;

		case 6:
      	{
			String[] CSIM_label = 
				{"AT Command",
				 "SIM Request(Hex)", 
				 "CLA",
				 "INS",
				 "P1",
				 "P2",
				 "P3(Lc)",
				 "Command Data(Optional)",
				 "SIM Request Length(Integer)"};

			String[] widgetType = 
				{"comboBox", 
				 "textBox", 
				 "comboBox",
				 "comboBox",
				 "textBox",
				 "textBox",
				 "textBox",
				 "comboBox",
				 "textBox"
				};
        	/*If Widget Type ix ComboBox then this is the list*/
			String[][] optionList  = 
				{
					{"AT+CSIM",
				     "AT+CSIM=?"
					},
					/*CLA - Class Byte*/
					{"GSM(ICC)",
					 "3G/4G(UICC)"
					},
					/*INS - Instruction Byte*/
					{"SELECT",
					 "STATUS",
					 "READ BINARY",
					 "UPDATE BINARY",
					 "READ RECORD",
					 "UPDATE RECORD",
					 "SEEK",
					 "INCREASE",
					 "VERIFY CHV",
					 "CHANGE CHV",
					 "DISABLE CHV",
					 "ENABLE CHV",
					 "UNBLOCK CHV",
					 "INVALIDATE",
					 "REHABILITATE",
					 "RUN GSM ALGO",
					 "SLEEP",
					 "GET RESPONSE"
					},
					{"MF",
					 "ICCID",
					 "DFgsm",
					 "Language Preference(LP)",
					 "IMSI",
					 "Kc",
					 "PLMN Selector",
					 "HPLMN",
					 "ACM Max",
					 "SST  (Sim Service Table)",
					 "ACM  (Accumulated Call Meter)",
					 "GID1 (Group Identifier Level1)",
					 "GID2 (Group Identifier Level2)",
					 "SPN  (Service Provider Name)",
					 "PUCT",
					 "CBMI",
					 "BCCH",
                     "ACC",
					 "FPLMN (Forbidden PLMN)",
					 "LOCI",
					 "AD (Administrative Data)",
					 "Phase",
					 "DFtelecom",
					 "ADN (Abbreviated Dialling Number)",
					 "FDN (Forbidden Dialling Number)",
					 "SMS",
					 "CCP",
					 "MSISDN",
					 "SMSP",
					 "SMSS(STATUS)",
					 "LND (Last Number Dialled)",
					 "EXT1",
					 "EXT2"
					}
				};

		    Widget CSIM_w = new Widget(CSIM_label);
			component[idx] = CSIM_w.buildCSIMUI(CSIM_label, widgetType, optionList);
		}
		break;

		case 7:
      	{
		    String[] CRSM_label = 
				{
					"AT Command",
					"Command", 
					"File Id",
				    "P1", 
					"P2", 
					"P3",
				    "Command Data", 
					"Path Id"
				};

				String[] widgetType = 
					{
						"comboBox", 
						"comboBox", 
						"textBox", 
				        "textBox", 
						"textBox", 
						"textBox",
				        "textBox", 
						"textBox"
					};

				String[][] optionList  = 
					{
						{"AT+CRSM"},
						{"READ BINARY",
				         "READ RECORD",
						 "GET RESPONSE",
						 "UPDATE BINARY",
						 "UPDATE RECORD",
						 "STATUS",
						 "RETRIEVE DATA",
						 "SET DATA"
						}
				    };

		    Widget CRSM_w = new Widget(CRSM_label);
			component[idx] = CRSM_w.buildCRSMUI(CRSM_label, widgetType, optionList);
		}
		break;

		default:

		    String[] labelName5 = {"Text1", "Text2", "Text3"};
		    Widget tw5 = new Widget(labelName5);
			component[idx] = tw5.buildTestCommandUI(labelName5);
			break;
	}
	jpanel.setBorder(BorderFactory.createLineBorder(Color.black));
	jpanel.setBorder(BorderFactory.createLoweredBevelBorder());
	return component[idx];
}/*createComponent*/	


public JTabbedPane createTabbedPane() {
    int idx = 0;					
	JTabbedPane tabbedPane = new JTabbedPane();

	for(idx = 0; idx < tabbedText.length; idx++) {

		component[idx] = createComponent(idx);			 
		/*Adding jpanel to tabbedPane*/
	    tabbedPane.addTab(getTabTitle(idx), component[idx]);	 
	}
	/*Make Default Tab Selected*/
    tabbedPane.setSelectedIndex(0);	
	return tabbedPane;
}/*createTabbedPane*/


public JTabbedPane createTabbedPane(int idx, String[] tabbedTitle) {
    if(-1 == Tabbed(idx, tabbedTitle)) {
    	return null;						
	}
    return createTabbedPane(); 		
}/*createTabbedPane*/
	

/*
 *Garbage collector - deallocating the created abject
 * */
public void finalize() {
	int idx = 0;

    for(idx = 0; idx < tabCount; idx++) {
    	tabbedText[idx] = null;  						
	}
	
    System.gc();

  }/*finalize*/

}
