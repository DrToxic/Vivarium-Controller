;Enable debugging to console window.
If CommandLine$() = "/debug" Then debug = True
Include "includes\config loader.bb"
loadGFXConfig()
Include "includes\Windows menus.bb"
AppTitle "Vivarium Display Unit"
Global alert = False
Global timer = CreateTimer(60)
Global tAnim
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
drawBackground()
drawtitlebar
drawPower(dPowerX,dPowerY,dPowerW,dPowerH)
drawtempscale(tScaleX,tScaleY,tScaleW,tScaleH)
drawHumidity(hScaleX,hScaleY,hScaleW,hScaleH)
FlipCanvas(mainCanvas)
Forever
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;Draw the background;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function drawBackground()
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;Draw the Title Bar;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function drawtitlebar()
	If TimerTicks(timer) Then tAnim = tAnim+10
	If tAnim > Width-20 Then tAnim = 2
	Color 255,255,255
	Rect(0,0,Width,50,0)
	If alert = True Then
		Color 255,0,0
	Else
		Color 0,0,255
	EndIf
	Rect(1,1,Width-2,48,1)
	Color(127,127,127)
	Rect(tAnim,1,20,48,1)
	SetFont titleFont
	Color 0,0,0
	Text Width/2,25,"Vivarium Controller",True,True
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;Draw the temperature scale;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function drawtempscale(tempX,tempY,tempW,tempH)
	Color 255,255,255
	Rect tempX,tempY,tempW,tempH,0
	SetFont subFont
	   Text tempX+10,tempY+10,"Outside:"
	SetFont numFont
	   Text tempX+165,tempY+10,"20"
	SetFont subFont
	   Text tempX+215,tempY+10,"°C"
	Text tempX+tempW/2,tempY+15,"Temperatures",True,True
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;Current Temperatures;;;;;;;;;;;;;;;;;;;;
	   Rect tempX+10,       tempY+100,tempW-20,50,0
	  Color 0,255,0
	   Rect tempX+12,       tempY+102,tempW-24,46,1
	  Color 0,0,255
	   Rect tempX+12,       tempY+102,200,46,1
	  Color 0,0,0
	SetFont numFont
	   Text tempX+15,       tempY+125,"16",False,True
	SetFont subFont
	   Text tempX+15+40,    tempY+125,"°C",False,True
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	  Color 255,0,0
	   Rect tempX+tempW-212,tempY+102,200, 46,1
	  Color 0,0,0
	SetFont(numFont)
	   Text tempX+tempW-100,tempY+125,"28",False,True
	SetFont(subFont)
	   Text tempX+tempW-60, tempY+125,"°C",False,True
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;Target Temperatures;;;;;;;;;;;;;;;;;;
Color coldR,coldG,coldB
	Rect tempX+10,tempY+55,250,40,0
	SetFont (numFont)
	   Text tempX+15,tempY+57,coldMin
	SetFont (subFont)
	   Text tempX+55,tempY+57,"°C ~"
	SetFont (numFont)
	   Text tempX+130,tempY+57,coldMax
	SetFont (subFont)
	   Text tempX+170,tempY+57,"°C"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	Color hotR,hotG,hotB
	   Rect tempX+tempW-245,tempY+55,235,40,0
	SetFont (numFont)
	   Text tempX+tempW-220,tempY+57,hotMin
	SetFont (subFont)
	   Text tempX+tempW-180,tempY+57,"°C ~"
	SetFont (numFont)
	   Text tempX+tempW-100,tempY+57,hotMax
	SetFont (subFont)
	   Text tempX+tempW-55,tempY+57,"°C"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;Status Indicators;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function drawPower(tempX,tempY,tempW,tempH)
	Color 255,255,255
	SetFont (subFont)
	Rect tempX,tempY,tempW,tempH,0
	Text (tempX+tempW/2,tempY+15,"Power and Status",True,True)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Color 75,75,75
	Rect tempX+5,tempY+28,290,110,0
	Text (tempX+10,tempY+30,"Heat Mat 1")		: DrawImage(offimg,tempX+220,tempY+32)
	Text (tempX+10,tempY+60,"Heat Mat 2")		: DrawImage(onimg,tempX+220,tempY+62)
	Text (tempX+10,tempY+90,"Heat Bulb")		: DrawImage(offimg,tempX+220,tempY+92)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	Text (tempx+300,tempY+30,"Day light")		: DrawImage(offimg,tempX+560,tempY+32)
	Text (tempx+300,tempY+60,"Night Light")		: DrawImage(offimg,tempX+560,tempY+62)
	Text (tempx+300,tempY+90,"Water Feature")	: DrawImage(offimg,tempX+560,tempY+92)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	Text (tempx+620,tempY+30,"Left Door Open")	: DrawImage(offimg,tempX+920,tempY+32)
	Text (tempx+620,tempY+60,"Right Door Open")	: DrawImage(badimg,tempX+920,tempY+62)
	Text (tempx+620,tempY+90,"Mains Power In")	: DrawImage(offimg,tempX+920,tempY+92)
End Function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;Draw Humidity Meter;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function drawHumidity(tempX,tempY,tempW,tempH)
	Color 255,255,255
	Rect tempX,tempY,tempW,tempH,0
	SetFont subFont
	   Text tempX+10,tempY+10,"Outside:"
	SetFont numFont
	   Text tempX+165,tempY+10,"45"
	SetFont subFont
	   Text tempX+215,tempY+10,"%"
	Text tempX+tempW/2,tempY+15,"Humidity",True,True
	Rect tempX+10,tempY+100,tempW-20,30,0
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	Color 100,100,255
	Rect tempX+12,tempY+102,tempW-24,26,1	
	Color 200,200,255
	Rect tempX+12,tempY+102,200,26,1
	Color 0,0,255
	Rect tempX+tempW-212,tempY+102,200,26,1
;;;;;;;;;;;;;;Target Humidity;;;;;;;;;;;;;;;;;;;;;;
Color dryR,dryG,dryB
	Rect tempX+10,tempY+55,100,40,0
	SetFont (numFont)
	   Text tempX+15,tempY+57,humidMin
	SetFont (subFont)
	   Text tempX+55,tempY+57,"%"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	Color wetR,wetG,wetB
	   Rect tempX+tempW-110,tempY+55,100,45,0
	SetFont (numFont)
	   Text tempX+tempW-100,tempY+57,humidMax
	SetFont (subFont)
	   Text tempX+tempW-55,tempY+57,"%"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
End Function
;Function createBTN(label$,x,y,w,h,mode) ;MODE= 0: disabled, 1: normal, 2: highlight, 3: pressed
;
;	Color 255,255,255
;	Rect x,y,w,h
;	Select mode
;
;	End Select
;
;
;End Function
Include "includes/complete functions.bb"