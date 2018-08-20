;create the window, choose a mode, update the window.

Global	mainWindow	= CreateWindow	("main Window", mainX, mainY, mainWidth, mainHeight, 0, 15)
		mainMenu = WindowMenu(mainWindow)
		Config = CreateMenu("Configuration Options",0,mainMenu)
				 CreateMenu("Load app configuration",11,Config)
				 CreateMenu("Save app configuration",12,Config)
				 CreateMenu("",0,Config)
				 CreateMenu("Load Vivarium Configuration",13,Config)
				 CreateMenu("Save Vivarium Configuration",14,Config)

UpdateWindowMenu(mainWindow)

tPanel = CreatePanel(5,5,200,225,mainWindow,1)

CreateLabel("Temperature Settings",48,-1,110,20,tPanel,1)

currentTemp = CreatePanel(5,25,185,80,tPanel,1)
CreateLabel("Current Settings",185/2-52,-1,110,20,currentTemp,1)
CreateLabel("Lower Limit",55,25,72,20,currentTemp,3)
CreateLabel("Upper Limit",55,50,72,20,currentTemp,3)

cHot = CreatePanel(3,25,50,50,currentTemp,1)
   SetPanelColor(cHot,255,0,0)
Global tempCurHMin	 =	CreateLabel(hotMin,3,3,37,16,cHot,1)
Global tempCurHMax	 =	CreateLabel(hotMax,3,23,37,16,cHot,1)

cCold = CreatePanel(130,25,50,50,currentTemp,1)
		SetPanelColor(cCold,0,0,255)
Global tempCurCMin	 =	CreateLabel(coldMin,3,3,37,16,cCold,1)
Global tempCurCMax	 =	CreateLabel(coldMax,3,23,37,16,cCold,1)


newTemp =   CreatePanel(5,120,185,100,tPanel,1)
	CreateLabel("New Settings",185/2-52,-1,110,20,newTemp,1)
	CreateLabel("Lower Limit",55,25,72,20,newTemp,3)
	CreateLabel("Upper Limit",55,50,72,20,newTemp,3)


nHot =	CreatePanel(3,25,50,50,newTemp,1)
	SetPanelColor(nHot,255,0,0)
tempNewHMin =	CreateTextField(3,3,37,16,nHot)
tempNewHMax=	CreateTextField(3,23,37,16,nHot)

nCold = CreatePanel(130,25,50,50,newTemp,1)
	SetPanelColor(nCold,0,0,255)
tempNewCMin =	CreateTextField(3,3,37,16,nCold)
tempNewCMax=	CreateTextField(3,23,37,16,nCold)
tempSet=	CreateButton("Set Temperatures",40,75,100,20,newTemp)


hPanel = CreatePanel(210,5,150,225,mainWindow,1)

CreateLabel("Humidity Settings",23,-1,110,20,hPanel,1)

currentHumid = CreatePanel(5,25,135,80,hPanel,1)
CreateLabel("Current Settings",135/2-52,-1,110,20,currentHumid,1)
CreateLabel("Lower Limit",55,25,72,20,currentHumid,3)
CreateLabel("Upper Limit",55,50,72,20,currentHumid,3)

cHumid = CreatePanel(3,25,50,50,currentHumid,1)
		SetPanelColor(cHumid,75,75,255)
Global humidCurMin	 =	CreateLabel(humidMin,3,3,37,16,cHumid,1)
Global humidCurMax	 =	CreateLabel(humidMax,3,23,37,16,cHumid,1)


newHumid =   CreatePanel(5,120,135,100,hPanel,1)
CreateLabel("New Settings",135/2-52,-1,110,20,newHumid,1)
humidNewMin%	=	CreateLabel("Lower Limit",55,25,72,20,newHumid,3)
humidNewMax%	=	CreateLabel("Upper Limit",55,50,72,20,newHumid,3)

nHumid = CreatePanel(3,25,50,50,newHumid,1)
		SetPanelColor(nHumid,75,75,255)
humidNewMin% =CreateTextField(3,3,37,16,nHumid)
humidNewMax%	=CreateTextField(3,23,37,16,nHumid)
humidSet=	CreateButton("Set Humidity",20,75,100,20,newHumid)