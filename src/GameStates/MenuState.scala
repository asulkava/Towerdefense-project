package GameStates

import Main._
import Interface._

object MenuState extends GameState {
  
  var font = main.createFont("Arial", 24, true)
  var settings = false
  var Instructions = false
  var img = loader.get("farmback").get
  var img2 = loader.get("rightClick").get
  var img3 = loader.get("leftClick").get
  var moneyimg = loader.get("money").get
  var playimg = loader.get("playButton").get
  var buttons = Map[String, Button](
                  "start" -> new Button(400, 250, 250, 35, "Start"),
                  "settings" -> new Button(400, 300, 250, 35, "Settings"),
                  "instructions" -> new Button(400, 350, 250, 35, "Instructions"),
                  "exit" -> new Button(400, 400, 250, 35, "Exit"))
                  
 
                           
  var backButton = Map[String, Button]("back" -> new Button(400, 500, 250, 35, "Back"))
  
  def init = {
    main.background(175,175,175)
  }
  
  def terminate = {
    
  }
  
  def update(dt: Double) = {
  
      buttons.values.foreach(_.update())
    
  }
  

	
  def draw(dt: Double) = {
    main.image(img, 0, 0, 800, 670)
    
    if (settings) {
      
    }
    else if (Instructions) {
      backButton.values.foreach(_.draw())
      main.image(img2, 30, 20)
      main.image(img3, 30, 100)
      main.image(moneyimg, 30, 200)
      main.textAlign(1, 3)
      main.fill(main.fontColor._1, main.fontColor._2, main.fontColor._3)
      main.textFont(font)
      main.text("Left click to select and build", 80, 70)
      main.text("Right click to clear selection", 80, 150)
      main.text("Use money to buy towers and use abilities", 180, 220)
    }
    else {
      buttons.values.foreach(_.draw())
    }
  }
  
  def mousePress(key: Int) = {
    
    if(key == 37) {
      
      if(settings) {
        
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