package analysis

class CategoryTree() {	
	var root = new MultiBranch("Root")
	override def toString(): String = {
		var description = "Root"
		description += root.namesOfChildren.mkString("\n")
		//Algorithm isn't so easy...
		description
	}
}

sealed abstract class Node(val value: String) {
  /** Replaces this node or its children according to the given function */
  def replace(fn: Node => Node): Node

  /** Helper to replace nodes that have a given value */
  def replace(value: String, node: Node): Node =
    replace(n => if (n.value == value) node else n)
}


import scala.collection.mutable.Map
class MultiBranch(override val value: String)
     extends Node(value) {
	
	var children = Map[String, MultiBranch]()
	
	def replaceChild(name :String, newChild: MultiBranch)
	{
		children(name) = newChild
	}
	
	def namesOfChildren() = children.keys
	
	def hasChildren(): Boolean = !children.isEmpty
	
	def addChild(child: MultiBranch){
		if (children.keys.exists(x => x == child.value))
				replaceChild(child.value, child)
		children += (child.value -> child)
	}
	
	def addChild(childName: String){
		if (children.keys.exists(x => x == childName))
				replaceChild(childName, new MultiBranch(childName))
		children += (childName -> new MultiBranch(childName))
	}
	
	def addChildren(children: Iterable[String]) {
		for(child <- children) {
			addChild(child)
		}
	}
	
	def apply(childName: String) = children(childName)
  def replace(fn: Node => Node): Node = {
    val newSelf = fn(this)
    if (this eq newSelf) {
        this
      
    } else {
      newSelf
    }
  }
}