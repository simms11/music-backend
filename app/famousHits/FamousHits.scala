package famousHits
import models.Hits

class FamousHits {
  def getHits : List[Hits] = hits
  def getHit(id:Int) :Option[Hits] = {
    val myHit = hits.filter { hit =>
      hit.id == id
    }
    myHit.headOption
  }
  def addHit(hit:Hits) = {
    hits :+= hit
    hits
  }

  var hits:List[Hits] = List[Hits] (
    Hits(1, "Jesus Walks",2004,"The College Dropout",11,"This song talks about society, racism, and war with ourselves."),
    Hits(2, "All falls down", 2003, "The College Dropout", 7, "The music video was shot at Ontario International Airport in Ontario, California."),
    Hits(3, "All of the Lights", 2010, "My Beautiful Dark Twisted Fantasy", 18, "Drake also recorded vocals for this track but was dropped from the final version."),
    Hits(4, "Diamonds From Sierra Leone", 2005, "Late Registration", 43, "This is based on the song `Diamonds Are Forever` by Shirley Bassey."),
    Hits(5, "Good Life", 2007, "Graduation", 7, "Rapper and songwriter T-Pain is featured on this track."),
    Hits(6, "Through the wire", 2003, "The College Dropout", 15,"At first, many of us didn't know how to pronounce his name, thinking maybe it was a Brett Favre situation where his first name was really pronounced `Kane.`")

  )


}
