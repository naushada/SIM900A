import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import javax.swing.SpringLayout;



class Csim {
  private String[][] claStr;
  private String[][] insStr;
	/*Dedicated/Elementry File*/
  private String[][] fileId;
	public Csim() {
		String tmpInsStr[][] = {
						      {"SELECT",        "A4"},
						      {"STATUS",        "F2"},
							    {"READ BINARY",   "B0"},
							    {"UPDATE BINART", "D6"},
							    {"READ RECORD",   "B2"},
							    {"UPDATE RECORD", "DC"},
							    {"SEEK",          "A2"},
							    {"INCREASE",      "32"},
							    {"VERIFY CHV",    "20"},
							    {"CHANGE CHV",    "24"},
							    {"ENABLE CHV",    "28"},
							    {"DISABLE CHV",   "26"},
							    {"UNBLOCK CHV",   "2C"},
							    {"INVALIDATE",    "04"},
							    {"REHABILITATE",  "44"},
							    {"RUN GSM ALGO",  "88"},
							    {"SLEEP",         "FA"},
							    {"GET RESPONSE",  "C0"},
							    /*Last row must be null*/
							    {null,            null}
		};
    insStr = new String[tmpInsStr.length][8];
		insStr = tmpInsStr;

		String tmpClaStr[][] = {
						   {"GSM(ICC)", "A0"},
						   {"3G/4G",    "00"},
							 /*Last row must be null*/
							 {null,       null}
		};
    claStr = new String[tmpClaStr.length][8];
		claStr = tmpClaStr;

		String tmpFileId[][] = {
						   {"MF",                             "3F00"},
		           {"ICCID",                          "2FE2"},
							 /*DF GSM*/
							 {"DFgsm",                          "7F20"},
							 /*Below EFs Belongs to DF-GSM*/
							 {"Language Preference(LP)",        "6F05"},
							 {"IMSI",                           "6F07"},
							 {"Kc",                             "6F20"},
							 {"PLMN Selector",                  "6F30"},
							 {"HPLMN",                          "6F31"},
							 {"ACM Max",                        "6F37"},
							 {"SST  (Sim Service Table)",       "6F38"},
							 {"ACM  (Accumulated Call Meter)",  "6F39"},
							 {"GID1 (Group Identifier Level1)", "6F3E"},
							 {"GID2 (Group Identifier Level2)", "6F3F"},
							 {"SPN  (Service Provider Name)",   "6F46"},
							 {"PUCT",                           "6F41"},
							 {"CBMI",                           "6F45"},
							 {"BCCH",                           "6F74"},
							 {"ACC",                            "6F78"},
							 {"FPLMN (Forbidden PLMN)",         "6F7B"},
               {"LOCI",                           "6F7E"},
							 {"AD (Administrative Data)",       "6FAD"},
							 {"Phase",                          "6FAE"},
							 /*Telecom DF*/
							 {"DFtelecom",                      "7F10"},
							 /*EF belonging to DF Telecom*/
							 {"ADN (Abbreviated Dialling Number)","6F3A"},
							 {"FDN (Forbidden Dialling Number)",  "6F3B"},
							 {"SMS",                              "6F3C"},
							 {"CCP",                              "6F3D"},
							 {"MSISDN",                           "6F40"},
							 {"SMSP",                             "6F42"},
							 {"SMSS(STATUS)",                     "6F43"},
							 {"LND (Last Number Dialled",         "6F44"},
							 {"EXT1",                             "6F4A"},
							 {"EXT2",                             "6F4B"},
							 {null,                                null}

		};
		fileId = new String[tmpFileId.length][8];
		fileId = tmpFileId;

	}/*Csim*/

  public String getFileId(String efDf) {
	  int idx;
		String tmpFileId = null;

		for (idx = 0; fileId[idx][0] != null; idx++) {
		 if (efDf == fileId[idx][0]) {
		   tmpFileId = fileId[idx][1];
		 } 
		}

		return tmpFileId;
	}/*getFileId*/

	public String getClaValue(String cla) {
    int idx;
		String claValue = null;

    for (idx = 0; claStr[idx][0] != null; idx++) {
		  if (cla == claStr[idx][0]) {
			  claValue = claStr[idx][1];
			}
		}	
	  return claValue;	
	}/*getClaValue*/

	public String getInsValue(String ins) {
    int idx;
		String insValue = null;

    for (idx = 0; insStr[idx][0] != null; idx++) {
		  if (ins == insStr[idx][0]) {
			  insValue = insStr[idx][1];
			}
		}	
	  return insValue;	
	}/*getInsValue*/

}/*Csim*/



public class Widget {
  private JTextField[]     textWidgetObj;
	/*widgetType - textBox, comboBox etc*/
	private String[]         widgetType;
	private String[]         widgetName;
	/*comboBox widget*/
	private JComboBox[]      comboBoxWidgetObj;
	private JCheckBox[]      checkBoxWidgetObj;
  private JLabel[]         labelWidgetObj;
  private int[]            widgetIdx;
  private static JTextArea displayWindow;
  private static UdpClient udp;

	public Widget() {

	  widgetName        = new String[3];
		textWidgetObj     = new JTextField[3];
		labelWidgetObj    = new JLabel[3];
		widgetIdx         = new int[3];
		widgetType        = new String[3];
		comboBoxWidgetObj = new JComboBox[3];

	}/*TextField*/
  
	public Widget(int widgetCount) {
	  
	  widgetName        = new String[widgetCount];
		textWidgetObj     = new JTextField[widgetCount];
		widgetIdx         = new int[widgetCount];
		labelWidgetObj    = new JLabel[widgetCount];
		widgetType        = new String[widgetCount];
		comboBoxWidgetObj = new JComboBox[widgetCount];
		checkBoxWidgetObj = new JCheckBox[widgetCount];
	}
	public Widget(String[] labelName) {
	  
	  widgetName        = new String[labelName.length];
		widgetType        = new String[labelName.length];
		textWidgetObj     = new JTextField[labelName.length];
		comboBoxWidgetObj = new JComboBox[labelName.length];
		widgetIdx         = new int[labelName.length];
		labelWidgetObj    = new JLabel[labelName.length];
		checkBoxWidgetObj = new JCheckBox[labelName.length];

		for(int idx = 0; idx < labelName.length; idx++) {
		  widgetName[idx] = labelName[idx];
		}
	}

	public static UdpClient getUdpObject() {
	  return udp;
	}/*getUdpObject*/

	public static void setUdpObject(UdpClient obj) {
	  udp = obj;
	}/*setUdpObj*/

	public static void setDisplayWindow(JTextArea jta) {
	  displayWindow = jta;
	}/*setDisplayWindow*/

	public static JTextArea getDisplayWindow() {
	  return displayWindow;
	}/*getDisplayWindow*/


	public void textFieldWidget(int idx, String name) {
	  
    widgetName[idx]      = name; 
	  textWidgetObj[idx]   = new JTextField(30);
    widgetIdx[idx]       = idx;
	}/*textFieldWidget*/
 
  public JTextField getTextWidgetObject(int index) {
	  return textWidgetObj[index];
	}/*getTextWidgetObject*/

  public JComboBox getComboBoxWidgetObject(int index) {
	  return comboBoxWidgetObj[index];
	}/*getTextWidgetObject*/

	public JCheckBox getCheckBoxWidgetObject(int index) {
	  return checkBoxWidgetObj[index];
	}
	public void labelWidget(int idx, String name) {
	  
	  labelWidgetObj[idx]   = new JLabel(name);
	}/*textFieldWidget*/
	
	public void comboBoxWidget(int idx, String wName, String[] optionList) {
	  
	  comboBoxWidgetObj[idx] = new JComboBox(optionList);
    widgetName[idx]          = wName;
		comboBoxWidgetObj[idx].setSelectedIndex(0);

	}/*comboBoxWidget*/

	public void checkBoxWidget(int idx, String wName) {
	  checkBoxWidgetObj[idx]  = new JCheckBox();
	  widgetName[idx]         = wName;
		checkBoxWidgetObj[idx].setSelected(true);
	}
	public Component buildWidget(int widgetCount, String[] widgetName,
									String[] widgetType, String[][] optionList) {
					
	  int idx                = 0;
		int comboIdx           = 0;
		int checkBoxIdx        = 0;
		int textBoxIdx         = 0;

    JPanel paneWidget      = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets             = new Insets(0,0,5,5);

    for(; idx < widgetCount; idx++) {

      if(widgetType[idx] == "textBox"){
							
		    textFieldWidget(textBoxIdx, widgetName[textBoxIdx]);
        paneWidget.add(textWidgetObj[textBoxIdx], gbc);
				textBoxIdx++;

			} else if(widgetType[idx] == "comboBox"){
				/*Handling of comboBox Widget*/			
			  comboBoxWidget(comboIdx, widgetName[idx], optionList[comboIdx]);
			  gbc.fill = GridBagConstraints.HORIZONTAL;
        paneWidget.add(comboBoxWidgetObj[comboIdx], gbc);
				comboIdx++;

			} else if(widgetType[idx] == "checkBox") {
			  /*Handling of check Box Widget*/
				checkBoxWidget(checkBoxIdx, widgetName[checkBoxIdx]);			
			  gbc.fill = GridBagConstraints.HORIZONTAL;
        paneWidget.add(checkBoxWidgetObj[checkBoxIdx], gbc);
				checkBoxIdx++;
			} else {
        							
			}
			gbc.fill = GridBagConstraints.EAST;
			gbc.ipady = 10;
			gbc.ipadx = 10;
			/*colmun*/
			gbc.gridx = 0;
			gbc.weighty = 0.5;
			/*Row*/
			gbc.gridy = idx + 10;
		}
		return paneWidget;
	}/*buildWidget*/

	public Component buildTextWidget(int textWidgetCount, String[] name) {
	  int idx                = 0;
    JPanel paneWidget      = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets             = new Insets(0,0,5,5);

    for(; idx < textWidgetCount; idx++) {

		  textFieldWidget(idx, name[idx]);
			gbc.fill = GridBagConstraints.NONE;
			gbc.ipady = 10;
			gbc.ipadx = 20;
			gbc.gridx = 0;
			gbc.weighty = 0.5;
			gbc.gridy = idx;
      paneWidget.add(textWidgetObj[idx], gbc);
		}
		return paneWidget;
	}/*buildTextWidget*/
  
	public Component buildLabelWidget(int labelWidgetCount, String[] labelName) {
	  int idx = 0;

    JPanel paneWidget      = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets             = new Insets(0,0,10,5);

    for(; idx < labelWidgetCount; idx++) {
						
		  labelWidget(idx, labelName[idx]);
			gbc.anchor = GridBagConstraints.WEST;
			/*Columns*/
			gbc.gridx = 0;
			/*Rows*/
			gbc.gridy = idx;
			gbc.weightx = 0.15;
			gbc.weighty = 0.5;
			/*Internal Padding B/W labels*/
			gbc.ipady = 10;
			gbc.ipadx = 20;
		  paneWidget.add(labelWidgetObj[idx], gbc);
		}
		return paneWidget;
	}/*buildLabelWidget*/	 

	public int handleButtonEvent(ActionEvent ae) {
	 processEvent(Tabbed.getActiveTabbedIndex(), ae.getActionCommand());
	 return 0;
	}

	public int processEvent(int whichTabbedIsClicked, 
									String whichButtonIsClicked) {
    String nl = "\n";

	  switch(whichTabbedIsClicked) {

			case 0 :
			  /*AT_DRIVER_CONFIGURATION*/
				if(whichButtonIsClicked == "OK") {
          int port;
          String ipAddr           = getTextWidgetObject(0).getText();
          String portStr          = getTextWidgetObject(1).getText();
          String remoteIpAddr     = getTextWidgetObject(2).getText();
          String remotePortStr    = getTextWidgetObject(3).getText();

					try {
					  port = Integer.parseInt(portStr);
          }catch(NumberFormatException nfe) {
            System.out.println("Number Format Exception" + nfe);
						port = 8080;
					}

					if((null != ipAddr) && (null != portStr)) {
           getUdpObject().setIpAddress(ipAddr);
					 getUdpObject().setPort(port);
           getUdpObject().setRemoteIpAddress(remoteIpAddr);
					// getUdpObject().setRemotePort(port);
					 getUdpObject().createSocket();
					 getUdpObject().spawnThread("udpThread");
					}
				} else if(whichButtonIsClicked == "CANCEL") {
								
				} else {
				  /*Unknown Button is clocked*/
				}
        break;

			case 1:
        /*AT_TEST_COMMAND*/
				if(whichButtonIsClicked == "OK") {

          String atTestCommand    = getComboBoxWidgetObject(0).getSelectedItem().toString();
          String otherTestCommand = getTextWidgetObject(0).getText();
          
					if (null != otherTestCommand) {
					  				
					  String ATCommand = otherTestCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					} else if(null != atTestCommand) {
					  String ATCommand = atTestCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					}
				} else if(whichButtonIsClicked == "CANCEL") {
          getTextWidgetObject(0).setText("");
				} else {
				  /*Unknown Button is clocked*/
				}
		    break;

			case 2:
        /*AT_READ_COMMAND*/
				if(whichButtonIsClicked == "OK") {
          
          String atReadCommand    = getComboBoxWidgetObject(0).getSelectedItem().toString();
          String otherReadCommand = getTextWidgetObject(0).getText();
          
					if (null != otherReadCommand) {
					  				
					  String ATCommand = otherReadCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					} else if(null != otherReadCommand) {
					  String ATCommand = atReadCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					}
				} else if(whichButtonIsClicked == "CANCEL") {
								
				} else {
				  /*Unknown Button is clocked*/
				}
		    break;

			case 3:
        /*AT_WRITE_COMMAND*/
				if(whichButtonIsClicked == "OK") {
          
          String atWriteCommand    = getComboBoxWidgetObject(0).getSelectedItem().toString();
          String otherWriteCommand = getTextWidgetObject(0).getText();
          
					if (null != otherWriteCommand) {
					  				
					  String ATCommand = otherWriteCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					} else if(null != atWriteCommand) {
					  String ATCommand = atWriteCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					}
				} else if(whichButtonIsClicked == "CANCEL") {
								
				} else {
				  /*Unknown Button is clocked*/
				}
		    break;

			case 4:
        /*AT_EXECUTION_COMMAND*/
				if(whichButtonIsClicked == "OK") {
          
          String atExecutionCommand    = getComboBoxWidgetObject(0).getSelectedItem().toString();
          String otherExecutionCommand = getTextWidgetObject(0).getText();
          
					if (null != otherExecutionCommand) {
					  				
					  String ATCommand = otherExecutionCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					} else if(null != atExecutionCommand) {
					  String ATCommand = atExecutionCommand + "\r";
					  getDisplayWindow().append((ATCommand + nl));
						getUdpObject().sendTo(ATCommand);

					}
				} else if(whichButtonIsClicked == "CANCEL") {
								
				} else {
				  /*Unknown Button is clocked*/
				}
	      break;

			case 5:

        /*AT_SEND_SMS*/
				if(whichButtonIsClicked == "SEND") {
          /*Constructing entire command for SMS*/
          String sms_command = getComboBoxWidgetObject(0).getSelectedItem().toString();
          String to_number   = getTextWidgetObject(0).getText();
          String sms_text    = getTextWidgetObject(1).getText();
          
					/*Text/PDU Mode*/
					if(getCheckBoxWidgetObject(0).isSelected()) {
					  String modeSetting = getComboBoxWidgetObject(1).getSelectedItem().toString();
						getUdpObject().sendTo(modeSetting + "\r\r\n");
					  getDisplayWindow().append((modeSetting + nl));
			      
						try {
              Thread.sleep(1000);										
						} catch(InterruptedException ie) {
						  System.out.println("Sleep Exception " + ie);
						}
					}

					if((null != sms_command) &&
						 (null != to_number)   &&
					   (null != sms_text))	 {
            /*26 is equivalent to CTRL+Z character*/
					  String ATCommand = sms_command + 
										           "="         + 
															 "\""        +
															 to_number   +
															 "\""        +
															 "\r\r\n";
						
						getUdpObject().sendTo(ATCommand);
					  getDisplayWindow().append((ATCommand + nl));
			      
						try {
              Thread.sleep(1000);										
						} catch(InterruptedException ie) {
						  System.out.println("Sleep Exception " + ie);
						}
			      ATCommand = "";			
						ATCommand = sms_text    +
												"\r\r\n"    +
												Character.toString((char)26);

						getUdpObject().sendTo(ATCommand);
					  getDisplayWindow().append((ATCommand + nl));
					}
         	
				} else if(whichButtonIsClicked == "CLEAR") {
								
          /*Clearing out the Text Box Contents except command*/
          getTextWidgetObject(0).setText("");
          getTextWidgetObject(1).setText("");
				} else {
				  /*Unknown Button is clocked*/
				}
			break;

	  case 6:
		{
			String csim_request;
      Csim   iso7816_command = new Csim();
      /*AT_CSIM COMMAND*/
		  if(whichButtonIsClicked == "SEND") {
							
        String at_command       = getComboBoxWidgetObject(0).getSelectedItem().toString();
        String cla              = getComboBoxWidgetObject(1).getSelectedItem().toString();
        String ins              = getComboBoxWidgetObject(2).getSelectedItem().toString();
        String command_data     = getComboBoxWidgetObject(3).getSelectedItem().toString();
       
        String sim_request      = getTextWidgetObject(0).getText();
        String p1               = getTextWidgetObject(1).getText();
        String p2               = getTextWidgetObject(2).getText();
        String p3               = getTextWidgetObject(3).getText();
        String sim_request_len  = getTextWidgetObject(4).getText();

        int command_len         = Integer.parseInt(sim_request_len);

			  if(!sim_request.isEmpty()) {
				  				
					String ATCommand = at_command          + 
										           "="               + 
															 (command_len * 2) +
															 ","               +
															 "\""              +
															 sim_request       +
															 "\""              +
															 "\r\r\n";

			    getDisplayWindow().append((ATCommand + nl));
				  getUdpObject().sendTo(ATCommand);
				}	else if ((ins == "READ RECORD") ||
									 (ins == "READ BINARY") ||
									 (ins == "GET RESPONSE") ||
									 (ins == "STATUS"))	{

					String ATCommand = at_command                         + 
										           "="                              + 
															 (command_len * 2)                +
															 ","                              +
															 "\""                             +
															 iso7816_command.getClaValue(cla) +
															 iso7816_command.getInsValue(ins) +
															 p1                               +
															 p2                               +
															 p3                               +
															 "\""                             +
															 "\r\r\n"                         ;

			    getDisplayWindow().append((ATCommand + nl));
				  getUdpObject().sendTo(ATCommand);
			  } else {
				  
					String ATCommand = at_command                         + 
										           "="                              + 
															 (command_len * 2)                +
															 ","                              +
															 "\""                             +
															 iso7816_command.getClaValue(cla) +
															 iso7816_command.getInsValue(ins) +
															 p1                               +
															 p2                               +
															 p3                               +
															 iso7816_command.getFileId(command_data) +
															 "\""                             +
															 "\r\r\n"                         ;
			    getDisplayWindow().append((ATCommand + nl));
				  getUdpObject().sendTo(ATCommand);

				}
         	
				} else if(whichButtonIsClicked == "CLEAR") {
								
          /*Clearing out the Text Box Contents except command*/
          getTextWidgetObject(0).setText("AT+CSIM");
          getTextWidgetObject(1).setText("");
          getTextWidgetObject(2).setText("");
				} else {
				  /*Unknown Button is clocked*/
				}
	      break;
		}
	  case 7:
		{
      Csim   iso7816_command = new Csim();
      /*AT_CRSM COMMAND*/
		  if(whichButtonIsClicked == "SEND") {
        /*Constructing entire command for SMS*/
        String at_command        = getComboBoxWidgetObject(0).getSelectedItem().toString();
        String crsm_command      = getComboBoxWidgetObject(1).getSelectedItem().toString();

        String crsm_file_id      = getTextWidgetObject(0).getText();
        String crsm_p1           = getTextWidgetObject(1).getText();
        String crsm_p2           = getTextWidgetObject(2).getText();
        String crsm_p3           = getTextWidgetObject(3).getText();
        String crsm_command_data = getTextWidgetObject(4).getText();
        String crsm_path_id      = getTextWidgetObject(5).getText();
         
			  if((null != at_command)  &&
					 (null != crsm_command)) {
					String ATCommand = at_command        + 
										         "="               + 
														 Integer.parseInt(iso7816_command.getInsValue(crsm_command), 16) +
														 ","               +
														 Integer.parseInt(crsm_file_id, 16)      +
														 ","               +
														 Integer.parseInt(crsm_p1, 16)           +   
														 ","               +
														 Integer.parseInt(crsm_p2, 16)           +
														 ","               +
														 Integer.parseInt(crsm_p3, 16)   +
														 ","               +
														 crsm_path_id +
														 "\r\r\n";
					                   /*        +
														 
														 ","               +
														 crsm_command_data +
														 ","               +
														 crsm_path_id */     ;

			    getDisplayWindow().append((ATCommand + nl));
					getUdpObject().sendTo(ATCommand);
			  }
         	
				} else if(whichButtonIsClicked == "CLEAR") {
								
          /*Clearing out the Text Box Contents except command*/
          getTextWidgetObject(1).setText("");
          getTextWidgetObject(2).setText("");
          getTextWidgetObject(3).setText("");
          getTextWidgetObject(4).setText("");
          getTextWidgetObject(5).setText("");
          getTextWidgetObject(6).setText("");
          getTextWidgetObject(7).setText("");
				} else {
				  /*Unknown Button is clocked*/
				}
		  }
	      break;
	    default:
				/*To be updated*/
				break;
		}/**/
    return 0;
	}/*processEvent*/


	public Component buildButtonWidget(String ok, String cancel) {
		/*Default will FLowLayout*/
	  JPanel  jpanel = new JPanel();
    JButton OK     = new JButton(ok);
	  JButton CAN    = new JButton(cancel);
		/*Adding ActionListener*/
		OK.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent ae) {
			  handleButtonEvent(ae);
			}
		});

		CAN.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent ae) {
			  handleButtonEvent(ae);
			}
		});

	  jpanel.add(OK);
		jpanel.add(CAN);
		return jpanel;
	}/*buildButtonWidget*/

	public Component glueButtonComponent(Component form,
									Component button) {
	  
	  /*Layout for this Panel is BorderLayout*/ 
		JPanel jpanel = new JPanel(new GridBagLayout());
    jpanel.add(form);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.50;
    jpanel.add(button, gbc);
    return jpanel;
	}/*glueButtonComponent*/


	public Component glueComponent(Component labelComponent,
								Component textComponent) {
	  /*Layout for this Panel is BorderLayout*/ 
    JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
	  gbc.gridy = 0;	
		
		jpanel.add(labelComponent, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
    jpanel.add(textComponent);
    return jpanel;
	}/*buildLabelWidget*/

	public Component buildATConfigurationUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildATConfigurationUI*/


	public Component buildTestCommandUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildTestCommandUI*/
	
	public Component buildReadCommandUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildReadCommandUI*/

	public Component buildWriteCommandUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildWriteCommandUI*/

	public Component buildExecutionCommandUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildExecutionCommandUI*/
	
	public Component buildSMSUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildSMSUI*/

	public Component buildCSIMUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildCSIMUI*/

	public Component buildCSIMUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName, widgetType, optionList);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildCSIMUI*/

	public Component buildCRSMUI(String[] labelName) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildTextWidget(labelName.length, labelName);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildCRSMUI*/

	public Component buildCRSMUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName, widgetType, optionList);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildCRSMUI*/

	public Component buildSMSUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName, widgetType, optionList);
		Component c3 = buildButtonWidget("SEND", "CLEAR");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildSMSUI*/

	public Component buildTestCommandUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName,
										widgetType, optionList);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildTestCommandUI*/

	public Component buildReadCommandUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName,
										widgetType, optionList);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildReadCommandUI*/
	
	public Component buildExecutionCommandUI(String[] labelName, String[] widgetType,
									String[][] optionList) {
	  Component c1 = buildLabelWidget(labelName.length, labelName);
		Component c2 = buildWidget(labelName.length, labelName, widgetType,
										optionList);
		Component c3 = buildButtonWidget("OK", "CANCEL");
		Component c  = glueComponent(c1, c2);
		Component d  = glueButtonComponent(c, c3);
		return d;
	}/*buildExecutionCommandUI*/
}


