# SIM900A
AT Command Interface for SIM900A

# Building SIM900A GUI
1. Java folder contails files which implements UI for SIM900A GSM/GPRS AT Command interface
2. Inside java folder, there is meakefile, to compile SIM900A module, go to java directory and issue make to build classes which will be placed in classes folder.
3. issue make clean to remove/delete class files.
4. in bin folder execute run.sh which will launch the SIM900A GUI.

# Configuration
1. Once SIM900A GUI is launched by bin/run.sh, 
2. go to "AT Provisining Interface" tab and provide/enter IP Address of machine on which SIM900A GUI is running and Port number
3. Provide IP Address "Remote IP Address" on which AT driver is running. AT Driver will be running on a machine which is connected to SIM900A via Serial (Comp) or via USB port.
4. provide port on which AT driver is expecting AT Command from GUI over UDP Interface.

# Precondition
1. AT Driver must be running on machine which is connected to SIM900A via Serial interface.
