package Main 

import scala.math._

class Pos(var x: Double, var y: Double){
  
  override def toString() = "Pos(" + this.x + ", " + this.y + ")"
  
  def add(that: Pos)      = new Pos(this.x + that.x, this.y + that.y)
  def subtract(that: Pos) = new Pos(this.x - that.x, this.y - that.y)
  def scale(n: Double)    = new Pos(this.x * n, this.y * n)
  def length = sqrt(x*x + y*y)

  
  
  def distanceToAnother(other: Pos) = sqrt(pow(other.x - this.x, 2) + pow(other.y - this.y,2))
  
  def changePosTo(x: Double, y: Double) = new Pos(x,y)
  
  
  
  
}

