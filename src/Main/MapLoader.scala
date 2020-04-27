package Main

import scala.collection.mutable.Buffer 
import processing.core._
import scala.io._
import java.io._
import Interface.loader
import Main._


object Path { // Object for the path
  
  var points = scala.collection.mutable.Buffer[Pos]()
  
}

// class for the different types of tiles
class MapTile(image: String, var x: Int, var y: Int, var solid: Boolean) {
  
  var img = loader.get(image).get
  var location = Pos(x*32, y*32)
  
  def draw() = {
    main.image(img, x*32, y*32, 32, 32)
  }
 
  
}
// Object for the game map
object GameMap {
  val width = 25
  val height = 19
  val grid = Array.ofDim[MapTile](width, height)
  val filename = new File("./resources/mapFiles")
  val fileList = filename.list()
  
  var generatedMap : PImage = _
  
  var maps: Map[String, Buffer[String]] = Map() 
  
  var paths: Map[String, Buffer[Pos]] = Map()
  
  var mapTiles = Buffer[String]() 
  var spawnerPos = Buffer[Pos]()
  var endPos = Buffer[Pos]()
  
  // load the maps from files
  def loadMaps() = {
    try {
      for (file <- fileList) {
        var tempMap: Buffer[String] = Buffer()
        var tempPath: Buffer[Pos] = Buffer()
        
        for(line <- scala.io.Source.fromFile("resources/mapFiles/" + file).getLines()) {
          // get path
          if (line(0) == '#') {
            var pathPos = line.drop(1).split(",")
            tempPath += Pos(pathPos(0).toDouble, pathPos(1).toDouble)
          }
          else {
            tempMap += line
          }
        }
        maps = maps + (file -> tempMap)            
        paths = paths + (file -> tempPath)
      }
    } 
    // check for errors in file loading
    catch {
      case ex: Exception => println("Error with map file.")
    }
    
    mapTiles = maps.values.head
    Path.points = paths.values.head
  }
  
  // draw the ground and walls
  def drawGround() = {
    for(x <- 0 until width; y <- 0 until height) { if(!grid(x)(y).solid) grid(x)(y).draw }
  }

  
  def drawWalls() = {
    for(x <- 0 until width; y <- 0 until height) { if(grid(x)(y).solid) grid(x)(y).draw}
  }
  
 // get one tile from x and y 
  def getTile(x: Double, y: Double)= {
    var x_pos = (x/32).toInt
    var y_pos = (y/32).toInt
    
    grid(x_pos)(y_pos)
  }

  
  def getTile(pos: Pos) : MapTile = {
    getTile(pos.x, pos.y)
  }
 
  
  // generate the map
  def generateMap() = {
    for(x <- 0 until width; y <- 0 until height) {
      var id = "ground"
      var isWall = false
    
      if(mapTiles(y)(x) == '*'){
        id = "wall"
        isWall = true
      }
      else if (mapTiles(y)(x) == 'S'){
        spawnerPos += Pos(x,y)
      }
      
      
    grid(x)(y) = new MapTile(id, x, y, isWall)
  }
  }

}