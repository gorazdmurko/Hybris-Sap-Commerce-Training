# Hybris-Sap-Commerce-Training

# INSTALATION

	
	1. before proceed, setup HYBRIS_HOME_DIR variable in System Variables

	2. GO TO ..\hybris\bin\platform folder & run the following commands
	
		- setantenv
		- ant (when it prompts to select, select 'development' option)

	
	3. GO TO {HYBRIS_HOME}\installer\ folder & run
	
		- install.bat -r b2c_acc_plus -A initAdminPassword=nimda 			( to install b2c_acc_plus accelerator; password is set to nimda in this case )
		- install.bat -r b2c_acc_plus -A initAdminPassword=nimda initialize		( to initialize b2c_acc_plus accelerator )


	4. OPEN THE hosts file in c:\Windows\System32\drivers\etc\hosts & add the following entry to the file
		
		- 127.0.0.1 localhost apparel-uk.local apparel-de.local electronics.local


	5. GO TO ..\hybris\bin\platform folder & run

		- .\hybrisserver debug

		- in browser open the http://electronics.local:9001/yacceleratorstorefront/


     #########################################################################################################################################

	7. to use existing extensions, copy them into {HYBRIS_HOME}\hybris\bin\custom folder & add extensions to localextensions.xml

	then

	8. GO TO IntellijIdea, press ctrl + shift + a, type in 'import from existing source' & follow the wizard

		- paste the extensions to localextensions.xml
		- run 'ant clean all'


     #########################################################################################################################################

	8. 30 days licence
	- https://stackoverflow.com/questions/54659462/hybris-30-days-license-expired
