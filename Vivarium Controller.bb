;Enable debugging to console window.
If CommandLine$() = "/debug" Then debug = True
Include "includes\config loader.bb"
Include "includes\windows menus.bb"




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
				Default
			End Select
		Case $803 ; window close button
			Select EventSource()
				Default Exit
			End Select
;		Case $1001 ; menu items from includes\windows.bb
;			Select EventData()
;				Case 1
;					Notify "Menu item code"
;					Exit
;				Default
;			End Select
	End Select

Cls

Flip

Forever