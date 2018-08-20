;Debug code
If debug = True Then
	Print "Creating main window: "+Width+", "+Height
	Print "Using desktop width and height: "+ClientWidth(Desktop())+", "+ClientHeight(Desktop())
EndIf


;Create the window
mainWindow = CreateWindow ("Vivarium Controller", ClientWidth(Desktop())/2-Width/2, ClientHeight(Desktop())/2-Width/2, Width+6, Height+29, 0, 1)

mainCanvas = CreateCanvas (0,0,Width,Height,mainWindow)
	SetBuffer(CanvasBuffer(mainCanvas))


;If debug = True Print "Creating menus"



;menu = WindowMenu (mainWindow)
;	main =	CreateMenu ("Main",0,menu)
;			CreateMenu ("Menu Item",1,main)

;UpdateWindowMenu(mainWindow)