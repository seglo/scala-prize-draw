import org.specs2._
import meetup._
import dispatch._
import net.liftweb.json._

class MeetupClientSpec extends org.specs2.mutable.Specification {

  "Organizers should contain Sean" >> {
    val conf = MeetupConfig.get
    val cli = rest.Client(Key(conf.apiKey))
    val org = for {
      JObject(org) <- cli.groups
        .urlname("scalator")
        .only("organizer")
        .request(as.lift.Json)() \\ "organizer"
      JField("name", JString(name)) <- org
    } yield name

    org must contain("Sean Glover")
  }
}
