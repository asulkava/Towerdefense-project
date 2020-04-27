package Main

object Settings {
  
  var difficulty = 1
  var mapNum = 0
  var availableMaps = GameMap.maps.keys.toArray
  var selectedMap = availableMaps(mapNum)
  
  def incDifficulty() = {
    if(difficulty > 2)  difficulty = 1
    else difficulty += 1
  }
  
  def difficultyText = {
    difficulty match{
      case 1 => "Easy"
      case 2 => "Medium"
      case _ => "Hard"      
    }
  }
  
  def changeMap = {
    if (mapNum >= availableMaps.size - 1) mapNum = 0
    else mapNum += 1
    
    GameMap.mapTiles = GameMap.maps(availableMaps(mapNum))
    Path.points = GameMap.paths(availableMaps(mapNum))
  }
  
  def mapText = "Current map: " + availableMaps(mapNum).split('.').head
  
  //difficulty multiplier
  def diffMultiplier = 0.75 + 0.25 * difficulty
}