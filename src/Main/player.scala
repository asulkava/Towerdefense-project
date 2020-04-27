package Main

object Player {
  var money = 120
  var spent = 0
  var points = 0
  var killed = 0
  var lives = 6
  
  def isAlive = (Player.lives > 0)

  def init = {
    money = 100
    spent = 0
    points = 0
    killed = 0
    lives = 5
  }
}