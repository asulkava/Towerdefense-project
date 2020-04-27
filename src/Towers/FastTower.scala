package Towers

import GameStates.PlayState
import Main.Pos
import Main.main

class FastTower(position: Pos) extends Towers(position: Pos) {
  
  var range = 90
  var cost = 150
  var dmg = 50
  cd = 0
  image_id = "FastTower"
    
  
  //shorter cooldown so shoots faster
  def shoot = {
    if(cd <= 0) {
      PlayState.projectiles += new Projectile(this.position, this.target.get.position + main.offset, this.dmg, System.nanoTime())
      cd = 20 
    }
    else {
      cd -= 1
    }
  }
  
  
  
  
}