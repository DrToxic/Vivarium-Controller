Global mainWidth = 640	: Global mainHeight = 320
Global mainX = ClientWidth(Desktop())/2-mainWidth/2		: Global mainY = ClientHeight(Desktop())-400
Global hotMin = 26 		: Global hotMax = 28
Global coldMin = 16		: Global coldMax = 18
Global humidMin = 40	: Global humidMax = 50
Global autoStart = 0	: Global autoStartC = 0
loadVivConfig()
loadConfig
AppTitle "Vivarium Controller"
Include "includes/mainWindow.bb"
Repeat
	Select WaitEvent()
		Case $101 ; keydown
			Select EventData()
				Case 59 ;F1
					Notify "help"
				Default
			End Select
		Case $401
			Select EventSource() ;buttons
				Case tempSet ; write input filters here.
				updateTemps(TextFieldText(tempNewCMin),TextFieldText(tempNewCMax),TextFieldText(tempNewHMin),TextFieldText(tempNewHMax))
				
				Case humidSet ; write input filters here.
				updateHumid(TextFieldText(humidNewMin),TextFieldText(humidNewMax))
				Default
			End Select
		Case $803 ; window close button
			Select EventSource()
				Default If Confirm("Are you sure you want to quit?"+Chr$(13)+"This will turn the vivarium off!",1) = 1 Then
					saveConfig
			;		shutDown
					Exit
				EndIf
			End Select
		Case $1001 ; menu items from includes\windows.bb
			Select EventData()
				Case 11 ; load app configuration
				Case 12 ; save app configuration
					saveConfig()
				Case 13 ; load vivarium configuration
				Case 14 ; save vivarium configuration
					saveVivConfig()
				Default
			End Select
	End Select
Forever
Include "includes/complete functions.bb"

