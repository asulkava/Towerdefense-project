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
  var complete = 0l
  var rounds = Map[Int,Round]() // add rounds
  
  
  
  def isComplete() = (System.nanoTime() - complete)*1e9 < 3
  
  def roundText() = if(round) "Round" + roundCount else "Next round in" + timeToNext
  
  def roundCompleteText() = {"Round" + roundCount + "complete"}
  
  def init() = {
    roundCount = 0
    timeToNext = System.nanoTime() + timeToNext*1e9
    round = false
  }
}