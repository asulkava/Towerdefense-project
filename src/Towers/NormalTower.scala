package Towers

import GameStates.PlayState
import Main.Pos
import Main.main

class NormalTower(position: Pos) extends Towers(position: Pos) {
  
  var range = 100
  var cost = 50
  var dmg = 50
  cd = 0
  image_id = "NormalTower"
    
  def shoot = {
    if(cd <= 0) {
      PlayState.projectiles += new Projectile(this.position, this.target.get.position + main.offset, this.dmg,System.nanoTime())
      cd = 70 
    }
    else {
      cd -= 1
    }
  }
  
  
  
  
}