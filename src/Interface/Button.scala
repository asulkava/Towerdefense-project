package Interface

import Main._

//menu buttons
class Button(var x: Int, var y: Int, var w: Int, var h: Int, var text: String) {
  var hover = false
  
  
  var font = main.createFont("Arial", 16, true)
  
  // check if mouse is on button
  def isOn() = {
    var mx = main.mouseX
    var my = main.mouseY
    
    var bigw = if(hover) w+25 else w
    
    (mx >= x-bigw/2 && mx <= x+bigw/2 && my >= y-h/2 && my <= y+h/2)
  }
  
  // draw the button
  def draw() = {
    main.pushStyle()
    main.pushMatrix()
    main.translate(x, y)
    
    // make wider if hovering
    var bigw = if(hover) w+25 else w

    main.stroke(0)
    
    var col = Array(255f, 255f, 255f)
     
    main.fill(col(0), col(1), col(2))
    main.translate(-bigw/2, -h/2)
    main.rect(0, 0, bigw, h)
    main.fill(0)
    main.textAlign(3, 3)
    main.textFont(font)
    main.text(text, bigw/2, h/2-2)    
    main.popMatrix()
    main.popStyle()
  }
  
  def update() = {
    hover = isOn
  }
}

// gamebuttons
class GameButton(var x: Int, var y: Int, var w: Int, var h: Int, var number: Int, var image: String) {
  var selected = false
  var hover = false
  val normalImage = loader.get(image)
  var onFor = 0
  
  // timer for the infotext
  def timer = onFor > 20
  // check mouse pos
  def isHovering() = {
    var mx = main.mouseX
    var my = main.mouseY    
    (mx >= x && mx <= x+w && my >= y && my <= y+h)
  }
  
  def draw() = {
    main.pushStyle()
    main.pushMatrix()
          
    // show selected with a rectangle around it if hovering over button
    if(selected || isHovering) {
      main.pushMatrix()
      main.fill(0,0,255,75)
      main.image(normalImage, x, y)
      main.rect(x,y,64,64)
      main.popMatrix()
    }
    // otherwise just draw normally
    else main.image(normalImage, x, y)
      
    main.popMatrix()
    main.popStyle()
  }
  
  // update
  def update() = {
    if(isHovering) {
      onFor += 1
    }
    else {onFor = 0}
  }
}