;if we're debugging, print the output.
If debug = True Print "LOADING CONFIG"

;Load options from a fixed Width config file.

;Defaults, if the config file fails.
Width	= 1024
Height	= 768
fullScr = 0
;----------------------------------

config = ReadFile("Config.cfg")
If config = 0 Then
	If debug = True Then Print "config not found, using defaults."

Else
	While Not Eof(config)
	temp$ = ReadLine(config)



	Select Upper(Left(temp$,10));First 10 characters are the OPTION Code.
		;Config Filters -- try to Load these from the file.
		Case "WIDTH     " Width = Mid$(temp$,13) : If debug = True Then Print "LOADING CONFIG: using line: WIDTH     "+Width
		Case "HEIGHT    " Height = Mid$(temp$,13) : If debug = True Then Print "LOADING CONFIG: using line: HEIGHT    "+Height
		Case "FULLSCR   " fullScr = Mid$(temp$,13,1):If debug = True Then Print "LOADING CONFIG: using line: FULLSCR   "+fullScr
		;---------------------------------------------
		
		;If we find anything we can't use... ignore it.
		Default If debug = True Print "LOADING CONFIG: trash line: "+temp$
		;Yes, that's how we deal with human-readable headers.
	End Select
	Wend
	
	If debug = True Then
		Print "CONFIG LOADED!"
	EndIf
;	If debug = True Then Stop
EndIf