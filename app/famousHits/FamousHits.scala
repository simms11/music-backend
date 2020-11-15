package famousHits

import models.Hit
import scala.collection.mutable

object FamousHits {

  def getHits: Seq[Hit] = hits.toList

  def getHit(id: Int): Option[Hit] = {
    val myHit = hits.filter { hit =>
      hit.id == id
    }
    myHit.headOption
  }

  def addHit(hit: Hit): Seq[Hit] = {
    hits addOne hit
    hits.toList
  }

  private val hits = mutable.ListBuffer(
    Hit(1, "Jesus Walks", 2004, "The College Dropout", 11, "This song talks about society, racism, and war with ourselves."),
    Hit(2, "All falls down", 2003, "The College Dropout", 7, "The music video was shot at Ontario International Airport in Ontario, California."),
    Hit(3, "All of the Lights", 2010, "My Beautiful Dark Twisted Fantasy", 18, "Drake also recorded vocals for this track but was dropped from the final version."),
    Hit(4, "Diamonds From Sierra Leone", 2005, "Late Registration", 43, "This is based on the song `Diamonds Are Forever` by Shirley Bassey."),
    Hit(5, "Good Life", 2007, "Graduation", 7, "Rapper and songwriter T-Pain is featured on this track."),
    Hit(6, "Through the wire", 2003, "The College Dropout", 15, "At first, many of us didn't know how to pronounce his name, thinking maybe it was a Brett Favre situation where his first name was really pronounced `Kane.`")

  )


}
