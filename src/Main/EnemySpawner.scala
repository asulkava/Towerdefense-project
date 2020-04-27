package Main

import processing.core._
import GameStates._
import Enemies._
import Main._
import scala.collection.mutable.Map



class Round(var enemyList: Map[String,Int], var reward: Int, var interval: Double)

object Round {
  def apply(r: Round) = {
    var enemiesNumber = Map[String,Int]()
    enemiesNumber ++= r.enemyList
    new Round(enemiesNumber, r.reward,r.interval)
  }
  
}



object EnemySpawner{
    
  var roundInterval = 10.0
  var roundCount = 0
  var timeToNext = System.nanoTime() + roundInterval*1e9
  var round = false
  var complete = 0l
  var setup: Round = _ 
  var latestEnemy = System.nanoTime()
  
  // all the rounds will loop the the last round, difficulty increases spawn interval
  var rounds = Map[Int, Round](
      1 -> new Round(
        Map("pig" -> 1),
        50,               
        2.5 - (1 - Settings.diffMultiplier)               
      ),
      2 -> new Round(
        Map("skeleton" -> 4),
        50 ,
        2.5 - (1 - Settings.diffMultiplier)
      ),
      3 -> new Round(
        Map("pig" -> 6,
            "zombie" -> 2),
        50,  
        2.5 - (1 - Settings.diffMultiplier)
      
      ),
      4 -> new Round(
        Map("pig" -> 8,
            "zombie" -> 6),
        75,  
        2.5 - (1 - Settings.diffMultiplier)
      ),
      5 -> new Round(
        Map("pig" -> 7,
            "zombie" -> 5,
            "skeleton" -> 5),
        80,  
        2.5 - (1 - Settings.diffMultiplier)
      ),
      6 -> new Round(
        Map("skeleton" -> 20,
            "zombie" -> 10),
        90,  
        1.5 - (1 - Settings.diffMultiplier)
      ),
      7 -> new Round(
        Map("normal" -> 16,
            "zombie" -> 10,
            "skeleton" -> 10),
        100,  
        1.0 - (1 - Settings.diffMultiplier)
      ),
      8 -> new Round(
        Map("zombie" -> 18,
            "skeleton" -> 15,
            "pig" -> 5),
        100,  
        1.0 - (1 - Settings.diffMultiplier)
      ),
      9 -> new Round(
        Map("zombie" -> 30,
            "skeleton" -> 15,
            "pig" -> 12),
        100,  
        1.0 - (1 - Settings.diffMultiplier)
      )
  )
  
  
  // shows if rounds complete
  def isComplete() = (System.nanoTime() - complete)*1e9 < 3
  
  // enemiesLeft
  def enemiesLeft() = setup.enemyList.values.sum + PlayState.enemies.size 
  
  // info text for the round
  def roundText() = if(roundCount == 0) "Press the playbutton to start" else if(round) "Round " + roundCount else "New wave starting in "+ timeUntilNextRound.toInt + " seconds!"
  
  // info text for completed round
  def roundCompleteText() = {"Round" + roundCount + "complete"}
  
  def init() = {
    roundCount = 0
    timeToNext = System.nanoTime() + timeToNext*1e9
    round = false
  }
  
  //setup for the rounds
   def changeSetup() = {
    if(rounds.get(roundCount).isDefined) setup = Round(rounds(roundCount))    
    else setup =  Round(rounds(rounds.keys.max))    
  }
   
   // time to next round
   def timeUntilNextRound = {
    if(round) {0}
    else {var curTime = System.nanoTime() 
          (timeToNext - curTime)/1e9}

  }
   
  // generate the enemies
  def generateEnemy(name: String): Enemy = {
    name match{
      case "pig" => new PigEnemy()
      case "skeleton" => new SkeletonEnemy()
      case _ => new ZombieEnemy()
    }
   
  }
  
  // update the round
   def updateRound() = {
    if(!round){
      if(timeUntilNextRound <= 0) { 
        roundCount += 1
        round = true
        changeSetup()
      }
    } 
    else{
      
      (timeToNext - System.nanoTime())/1e9
      
      if((System.nanoTime() - latestEnemy)/1e9 > setup.interval){
       
        var poss = setup.enemyList.filter(_._2 > 0).keys.toArray
        
        if(poss.size > 0){
          var enemy_type = poss(main.random(0, poss.size).toInt)
          var e = generateEnemy(enemy_type)
          GameStates.PlayState.enemies += e
          setup.enemyList(enemy_type) -= 1
          latestEnemy = System.nanoTime()
        }
        
      }
      
      if(enemiesLeft == 0){ // Round over
        round = false
        complete = System.nanoTime()
        timeToNext = System.nanoTime() + roundInterval*1e9
        Player.points += (setup.reward) * Settings.diffMultiplier.toInt
      }
    }  
  }
  

}