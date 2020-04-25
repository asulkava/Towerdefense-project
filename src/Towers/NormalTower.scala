package Towers

import GameStates.PlayState
import Main.Pos
import Main.main

class NormalTower(position: Pos) extends Towers(position: Pos) {
  
  var range = 300
  var cost = 50
  var dmg = 40
  cd = 0
  image_id = "NormalTower"
    
  def shoot = {
    if(cd <= 0) {
      PlayState.projectiles += new Projectile(this.position, this.target.get.position + main.offset, this.dmg,System.nanoTime())
      cd = 100 
    }
    else {
      cd -= 1
    }
  }
  
  
  
  
}