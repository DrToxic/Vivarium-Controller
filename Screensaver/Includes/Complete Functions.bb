Function saveGfxConfig()
	If FileType("Graphics.old.cfg") = 1 Then DeleteFile("Graphics.old.cfg")
	If FileType("Graphics.cfg") = 1 Then CopyFile("Graphics.cfg", "Graphics.old.cfg")
	fileOut = WriteFile("Graphics.cfg")
		WriteLine(fileOut,"FIXEDWIDTH  CONFIGURATION---")
		WriteLine(fileOut,""                            )
		WriteLine(fileOut,"Fullscreen= "+fullScr		)
		WriteLine(fileOut,"Width     = "+width			)
		WriteLine(fileOut,"Height    = "+height			)
		WriteLine(fileOut,""							)
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"Fonts ----------------------")
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"TitleFont = "+tFont$         )
		WriteLine(fileOut,"SubFont   = "+sFont$         )
		WriteLine(fileOut,"NumFont   = "+nFont$         )
		WriteLine(fileOut,""                            )
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"RGB Values -----------------")
		WriteLine(fileOut,"----------------------------")
		WriteLine(fileOut,"HOTRGB    = "+RSet(hotR%,3)	+","+	RSet(hotG%,3)	+","+	RSet(hotB%,3))
		WriteLine(fileOut,"COLDRGB   = "+RSet(coldR%,3)	+","+	RSet(coldG%,3)	+","+	RSet(coldB%,3))
		WriteLine(fileOut,"WETRGB    = "+RSet(wetR%,3)	+","+	RSet(wetG%,3)	+","+	RSet(wetB%,3))
		WriteLine(fileOut,"DRYRGB    = "+RSet(dryR%,3)	+","+	RSet(dryG%,3)	+","+	RSet(dryB%,3))
	CloseFile(fileOut)
End Function


Function loadGFXConfig();if we're debugging, print the output.
If debug = True Print "LOADING CONFIG"
;Load options from a fixed Width config file.
;Defaults, if the config file fails.
Global mainWidth	= 640
Global mainHeight	= 360
fullScr = 0
;----------------------------------
config = ReadFile("Config.cfg")
If config = 0 Then
	If debug = True Then Print "General config not found, using defaults."
Else
	While Not Eof(config)
	temp$ = ReadLine(config)
	Select Upper(Left(temp$,10));First 10 characters are the OPTION Code.
		;Config Filters -- try to Load these from the file.
		Case "WIDTH     " mainWidth = Mid$(temp$,13) : If debug = True Then Print "LOADING CONFIG: using line: WIDTH     "+mainWidth
		Case "HEIGHT    " mainHeight = Mid$(temp$,13) : If debug = True Then Print "LOADING CONFIG: using line: HEIGHT    "+mainHeight
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

End Function
