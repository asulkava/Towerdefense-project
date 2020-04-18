package Main

import processing.core._


class Round(var enemyList: Map[String,Int], var reward: Int, var interval: Double)

object Round {
  def apply(r: Round){
    var enemiesNumber = Map[String,Int]()
    enemiesNumber ++= r.enemyList
    new Round(enemiesNumber, r.reward,r.interval)
  }
  
}



object EnemySpawner{
    
  var roundInterval = 10
  var roundCount = 0
  var timeToNext = System.nanoTime() - (roundInterval*1e9)
  var round = false
  
  
  var waves = Map[Int,Round]() // add rounds
  
  
}