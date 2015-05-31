import scala.util.Random
import meetup._
import dispatch._
import net.liftweb.json._

/*
  Select random participants for a Meetup event
 */
object ScalaPrizeDraw {
  val conf = MeetupConfig.get
  val random = new Random
  var winners = Set[String]()

  def main (args: Array[String]) {
    1 to conf.raffleCount foreach { _ => nextWinner }
  }

  def reset = winners = Set[String]()

  /*
    Randomly select a new winner from the set of RSVP's
   */
  def nextWinner = {
    val rsvpSet = rsvps.toSet
    if (rsvpSet.size - winners.size == 0) println("No more winners!")
    else {
      val winner = (rsvpSet &~ winners).toList(random.nextInt(rsvpSet.size - winners.size))
      winners += winner
      println(s"Winner #${winners.size}: $winner")
    }
  }

  /*
    All "yes" RSVP's
   */
  lazy val rsvps: List[String] = {
    val cli = rest.Client(Key(conf.apiKey))
    for {
      JObject(rsvps) <- cli.rsvps
        .event(conf.eventId)
        .request(as.lift.Json) ()
      JField("response", JString(response)) <- rsvps
      JField("member", JObject(member)) <- rsvps if response == "yes"
      JField("name", JString(name)) <- member
    } yield name
  }
}