package GameStates


abstract class GameState {

	def init
	def terminate
	
	def update(t: Double)
	def draw(t: Double)
	def mousePress(key: Int)
	def keyPress
	
	
}