package towers
import main._


class projectile(start: Pos, target: Pos, DMG: Int, Time: Long){
  
  var position: Pos = start
  //var img
  var speed: Int = 1
  var dmg = DMG
  
  def isExpired = timePassed > 6 
  
  def timePassed = (System.nanoTime() - Time) 
  
  def hasHit(enemyPos: Pos) = this.position.distanceToAnother(enemyPos) < 30
  
  //def moveToTarget = this.position += 
  
  
  
  
  
  
}