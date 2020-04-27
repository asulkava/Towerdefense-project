package Interface

import Main._

// class for the normal UI texts
class Info(val location: Pos,val time: Long, val text: String, val r: Int, val g: Int, val b: Int, val size: Int) {
  
  
  var font = main.createFont("Arial", size)
  
  def timePassed = {
    (System.nanoTime() - time)/1e9
  }
  
  def isExpired = timePassed > 7
  
  def draw = {
    main.pushStyle()
    main.fill(r, g, b, (255-timePassed*75).toInt)
    main.textAlign(3,3)
    main.textFont(font)
    main.text(this.text, location.x, location.y)
    main.popStyle()
  }
  
}

//class for the hover info on buttons
class HoverInfo(var location: Pos, var text: String, var numLines: Int) {
  
  var font = main.createFont("Arial", 16, true)

  
  def draw {
    main.fill(0,0,200,150)
    main.rect(location.x - main.offset.x, location.y - main.offset.y, 125 + 2*text.length(), 30*numLines, 7 )  
    main.textFont(font)
    main.textAlign(1,1)
    main.fill(255,255,255)
    main.text(text, location.x, location.y)
  }
  
}