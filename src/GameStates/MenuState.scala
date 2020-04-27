package GameStates

import Main._
import Interface._

object MenuState extends GameState {
  
  var font = main.createFont("Arial", 24, true)
  var settings = false
  var Instructions = false
  var img = loader.get("farmback").get
  var playimg = loader.get("playButton").get
  
  // all the different buttons
  var buttons = Map[String, Button](
                  "start" -> new Button(400, 250, 250, 35, "Start"),
                  "settings" -> new Button(400, 300, 250, 35, "Settings"),
                  "instructions" -> new Button(400, 350, 250, 35, "Instructions"),
                  "exit" -> new Button(400, 400, 250, 35, "Exit"))
                  
 
                           
  var backButton = Map[String, Button]("back" -> new Button(400, 500, 250, 35, "Back"))
  
  var settingsButtons = Map[String, Button](
                           "map" -> new Button(400, 250, 250, 35, Settings.mapText),
                           "difficulty" -> new Button(400,300, 250,35, Settings.difficultyText),
                           "back" -> new Button(400,350, 250, 35, "Back"))
  
  def init = {
    main.background(175,175,175)
  }
  
  def terminate = {
    
  }
  // update the state
  def update(dt: Double) = {
  if (settings) settingsButtons.values.foreach(_.update())
  else buttons.values.foreach(_.update())
  }
  

	// draw the menu
  def draw(dt: Double) = {
    main.image(img, 0, 0, 800, 670)
    
    if (settings) {
      settingsButtons.values.foreach(_.draw())
    }
    else if (Instructions) {
      backButton.values.foreach(_.draw())
      main.textAlign(1, 3)
      main.fill(main.fontColor._1, main.fontColor._2, main.fontColor._3)
      main.textFont(font)
      main.text("1. Left click mouse to select and build.", 80, 70)
      main.text("2. Right click to reset selections.", 80, 150)
      main.text("3. Use your earned money to buy more towers.", 80, 220)
    }
    else {
      buttons.values.foreach(_.draw())
    }
  }
  
  // check for mousePresses
  def mousePress(key: Int) = {
    
    if(key == 37) {
      
      if(settings) {
        if (settingsButtons("map").isOn()) {
          Settings.changeMap
          settingsButtons("map").text = Settings.mapText
        }
        else if(settingsButtons("difficulty").isOn()) {
          Settings.incDifficulty
          settingsButtons("difficulty").text = Settings.difficultyText
        }
        else if(settingsButtons("back").isOn()) {
          settings = false
        }
      }
      else if (Instructions) {
        if (backButton("back").isOn()) {
          Instructions = false
        }  
      }
      else {
        if(buttons("start").isOn()) {
          MenuState.terminate
          PlayState.init
          main.mode = PlayState
        }
        else if(buttons("settings").isOn()) {
          settings = true
        }
        else if (buttons("instructions").isOn()) {
          Instructions = true
        }
        else if (buttons("exit").isOn()) {
          main.exit()
        }
        
      }
    }
  }
  
  def keyPress = {}
  
}