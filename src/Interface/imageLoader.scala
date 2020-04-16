package Interface

import processing.core._
import Main._

object loader {
  private val names = Map[String, String] (
      "wall" -> "wall.png",
      "Pig" -> "Pig.png",
      "Skeleton" -> "Skeleton.png",
      "Undying" -> "Undying.png",
      "Zombie" -> "Zombie.png",
      "wall2" -> "wall2.png",
      "projectile" -> "projectile.png",
      "buttonBlur" -> "button_blur.png",
      "towerButton" -> "towerButton.png",
      "playButton" -> "play.png",
      "rightClick" -> "rightClick.png",
      "leftClick" -> "leftClick.png",
      "money" -> "money.png",
  )
  
  private val Path = "resources/imageFiles/"
   
  def loadImage(path: String) : PImage = {
    main.loadImage(s"${Path}${path}")
  }
	
	private val images = scala.collection.mutable.Map[String, PImage]()
	
	for(id <- names) {
	  images(id._1) = loadImage(id._2)
	}
	
	def get(id: String) : PImage = {
	  var i = images.get(id)
	  
	  i.get
	}
	
}