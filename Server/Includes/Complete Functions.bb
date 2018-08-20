Function saveConfig()
	If FileType("Server.old.cfg") = 1 Then DeleteFile("Server.old.cfg")
	If FileType("Server.cfg") = 1 Then CopyFile("Server.cfg", "Server.old.cfg")
	DeleteFile("Server.cfg")
	fileOut = WriteFile("Server.cfg")
		WriteLine(fileOut,"FIXEDWIDTH  CONFIGURATION---"		)
		WriteLine(fileOut,""                            		)
		WriteLine(fileOut,"----------------------------"		)
		WriteLine(fileOut,"Main Window ----------------"		)
		WriteLine(fileOut,"----------------------------"		)
		WriteLine(fileOut,"mainX     = "+GadgetX(mainWindow)	)
		WriteLine(fileOut,"mainY     = "+GadgetY(mainWindow)	)
		WriteLine(fileOut,"mainWidth = "+GadgetWidth(mainWindow))
		WriteLine(fileOut,"mainHeight= "+GadgetHeight(mainWindow))
		WriteLine(fileOut,"----------------------------"		)
		WriteLine(fileOut,"General Settings -----------"		)
		WriteLine(fileOut,"----------------------------"		)
		WriteLine(fileOut,"AutoStart = "+autoStart      		)
		WriteLine(fileOut,"AutoStartC= "+autoStartC				)
	CloseFile(fileOut)
End Function
Function saveVivConfig()
	If FileType("Vivarium.old.cfg") = 1 Then DeleteFile("Vivarium.old.cfg")
	If FileType("Vivarium.cfg") = 1 Then CopyFile("Vivarium.cfg", "Vivarium.old.cfg")
	fileOut = WriteFile("Vivarium.cfg")
		WriteLine(fileOut,"FIXEDWIDTH  CONFIGURATION---")
		WriteLine(fileOut,""							)
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"Temperatures----------------")
		WriteLine(fileout,"----------------------------")
		WriteLine(fileOut,"Hot Side -------------------")
		WriteLine(fileOut,"HMinimum  = "+hotMin			)
		WriteLine(fileOut,"HMaximum  = "+hotMax			)
		WriteLine(fileOut,"Cold side ------------------")
		WriteLine(fileOut,"CMinimum  = "+coldMin		)
		WriteLine(fileOut,"CMaximum  = "+coldMax		)
		WriteLine(fileOut,""							)
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"Humidity -------------------")
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"WMinimum  = "+humidMin		)
		WriteLine(fileOut,"WMaximum  = "+humidMax		)
		WriteLine(fileOut,"----------------------------")
	CloseFile(fileOut)
End Function

Function loadConfig()
;if we're debugging, print the output.
If debug = True Print "LOADING VIVARIUM CONFIGURATION"
;Load options from a fixed Width config file.

;Defaults, if the config file fails.
;----------------------------------
gfx = ReadFile("Server.cfg")
If gfx = 0 Then
	If debug = True Then Print "Vivarium config not found, using defaults."
Else
	While Not Eof(gfx)
	temp$ = ReadLine(gfx)
	Select Upper(Left(temp$,10));First 10 characters are the OPTION Code.
		;Config Filters -- try to Load these from the file.
		Case "MAINX     " mainX = Mid$(temp$,13)		: If debug = True Then Print "LOADING CONFIG: using line: MAINX      = "+mainX
		Case "MAINY     " mainY = Mid$(temp$,13)		: If debug = True Then Print "LOADING CONFIG: using line: MAINY      = "+mainY
		Case "MAINWIDTH " mainWidth = Mid$(temp$,13)		: If debug = True Then Print "LOADING CONFIG: using line: MAINWIDTH  = "+mainWidth
		Case "MAINHEIGHT" mainHeight = Mid$(temp$,13)		: If debug = True Then Print "LOADING CONFIG: using line: MAINHEIGHT = "+mainHeight
		Case "AUTOSTART " autoStart= Mid$(temp$,13)		: If debug = True Then Print "LOADING CONFIG: using line: AUTOSTART  = "+autoStart
		Case "AUTOSTARTC" autoStartC = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: AUTOSTARTC = "+autoStartC
		;---------------------------------------------
		;If we find anything we can't use... ignore it.
		Default If debug = True Print "LOADING CONFIG: trash line: "+temp$
		;Yes, that's how we deal with human-readable headers.
	End Select
	Wend	
	If debug = True Then
		Print "VIVARIUM CONFIG LOADED!"
	EndIf
;	If debug = True Then Stop
EndIf

End Function

Function loadVivConfig()
;if we're debugging, print the output.
If debug = True Print "LOADING VIVARIUM CONFIGURATION"
;Load options from a fixed Width config file.

;Defaults, if the config file fails.
;----------------------------------
gfx = ReadFile("Vivarium.cfg.cfg")
If gfx = 0 Then
	If debug = True Then Print "Vivarium config not found, using defaults."
Else
	While Not Eof(gfx)
	temp$ = ReadLine(gfx)
	Select Upper(Left(temp$,10));First 10 characters are the OPTION Code.
		;Config Filters -- try to Load these from the file.
		Case "HMINIMUM  " hotMin = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: HMINIMUM   = "+hotMin
		Case "HMAXIMUM  " hotMax = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: HMAXIMUM   = "+hotMax
		Case "CMINIMUM  " coldMin = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: CMINIMUM   = "+coldMin
		Case "CMAXIMUM  " coldMax = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: CMAXIMUM   = "+coldMax
		Case "WMINIMUM  " humidMin = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: WMINIMUM   = "+humidMin
		Case "WMAXIMUM  " humidMax = Mid$(temp$,13)	: If debug = True Then Print "LOADING CONFIG: using line: WMAXIMUM   = "+humidMax
		;---------------------------------------------
		;If we find anything we can't use... ignore it.
		Default If debug = True Print "LOADING CONFIG: trash line: "+temp$
		;Yes, that's how we deal with human-readable headers.
	End Select
	Wend	
	If debug = True Then
		Print "VIVARIUM CONFIG LOADED!"
	EndIf
;	If debug = True Then Stop
EndIf
End Function

Function updateTemps(temp1%,temp2%,temp3%,temp4%) ; cold side low limit, cold side high limit, hot side low limit, hot side high limit
	If Int(temp4%) > 100 Then
		Notify "Temperature cannot be higher than 100*C"
	Else
		If Int(temp1%) < 0 Then
			Notify "Temperature cannot be lower than 0*C"
		Else
			If Int(temp3%) < Int(temp2%) Then
				Notify "It's not a good idea to set the hot side low limit to lower than the cold side low limit"
			Else
				If Int(temp1%) > Int(Temp2%) Or Int(Temp3%) > Int(Temp4%) Then
					Notify "Lower limit cannot be higher than upper limit"
				Else ; Pass all filter checks. . .
					hotMin% = temp3% : 	SetGadgetText(tempCurHMin,hotMin%)
					hotMax% = temp4% :	SetGadgetText(tempCurHMax,hotMax%)
					coldMin%= temp1% :	SetGadgetText(tempCurCMin,coldMin%)
					coldMax%= temp2% : 	SetGadgetText(tempCurCMax,coldMax%)
				EndIf
			EndIf
		EndIf
	EndIf
End Function

Function updateHumid(Humid1%,humid2%) ; humid min, humid max
	If Int(Humid1%) > Int(Humid2%) Then
		Notify "Lower limit cannot be higher than upper limit"
	Else
		If Int(humid1%) < 0 Then
			Notify "Humidity cannot be lower than 0%!"
		Else
			If Int(humid2%) > 100 Then
				Notify "Humidity ("+humid2%+") cannot be higher than 100%!"
			Else
				humidMin% = Int(humid1%) :	SetGadgetText(humidCurMin,humidMin%+"%")
				humidMax% = Int(humid2%) :	SetGadgetText(humidCurMax,humidMax%+"%")
			EndIf
		EndIf
	EndIf			
End Function
