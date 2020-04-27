package Towers

import GameStates.PlayState
import Main.Pos
import Main.main

class RangeTower(position: Pos) extends Towers(position: Pos) {
  
  var range = 300
  var cost = 150
  var dmg = 60
  cd = 0
  image_id = "RangeTower"
    
  def shoot = {
    if(cd <= 0) {
      PlayState.projectiles += new Projectile(this.position, this.target.get.position + main.offset, this.dmg, System.nanoTime())
      cd = 70 
    }
    else {
      cd -= 1
    }
  }
  
  
  
  
}