package GameStates

import Interface._
import Main._

object EndState extends GameState {
  
  var font = main.createFont("Arial", 16, true)
  var img = loader.get("farmback").get
  var btns = Map[String, Button]("menu" -> new Button(400, 400, 250, 35, "Menu"))
  
  def init = {}
  
  def terminate = {}
  
  def update(dt: Double) = {
    btns.values.foreach(_.update())
  }
  
  // Display the game ending text once you lost.
  def draw(dt: Double) = {
    main.pushMatrix()
    main.image(img, 0, 0, 800, 670)
    main.popMatrix()
    btns.values.foreach(_.draw())
    main.textAlign(3, 3)
    main.textFont(font,35)
    main.fill(204, 153, 0)
    main.text("Game has ended. You lost", 400, 150)
    main.textFont(font,30)
    main.text("Score: " + Player.points, 400, 200)
    main.text("Money Spent: $ " + Player.spent, 400, 250)
    main.text("Enemies killed: " + Player.killed, 400, 300)

  }
  
  // check if player want to go to menu
  def mousePress(key: Int) = {
    if (key == 37) {
      if(btns("menu").isOn()) {
        EndState.terminate
        MenuState.init
        main.mode = MenuState
      }
    }
  }
  
  def keyPress = {}
  
}