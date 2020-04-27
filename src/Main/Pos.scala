package Main

import scala.math._

class Pos(var x: Float, var y: Float) {
  // methods for basic operations between positions
	def +(other: Pos) = new Pos(x+other.x, y+other.y)
  def -(other: Pos) = new Pos(x-other.x, y-other.y)
	def *(scale: Float) = new Pos(x*scale, y*scale)
	def *(scale: Double) = new Pos(x*scale.toFloat, y*scale.toFloat)
	def /(scale: Float) = new Pos(x/scale, y/scale)
	def /(scale: Double) = new Pos(x/scale.toFloat, y/scale.toFloat)
	def length = sqrt(x*x+y*y)
	

  // distance to other pos
	def distanceToAnother(other: Pos): Float = {
		sqrt(pow(other.x - this.x, 2) + pow(other.y - this.y , 2)).toFloat
	}
	

	def normalize() : Unit = {
	  if(length > 0) {
	    var l = length.toFloat
  	  this.x = this.x/l
  	  this.y = this.y/l
	  }
	}

 // normalized pos
	def normalized() : Pos = {
	  var x = Pos(this)
	  x.normalize()
	  x
	}
	

}

// apply methods for making new positions easily with different number types
object Pos {
  def apply(x: Int, y: Int) = {
    new Pos(x, y)
  }
  
  def apply(x: Float, y: Float) = {
    new Pos(x, y)
  }
  
  def apply(x: Double, y: Double) = {
    new Pos(x.toFloat, y.toFloat)
  }
  
  def apply(v: Pos) = {
    new Pos(v.x, v.y)
  }
}