import com.typesafe.config.ConfigFactory

/**
 * Created by seglo on 30/05/15.
 */
case class MeetupConfig(apiKey: String, eventId: String, raffleCount: Int)

object MeetupConfig {
  val conf = ConfigFactory.load()
  def get = MeetupConfig(
    conf.getString("meetup-app-key"),
    conf.getString("event-id"),
    conf.getInt("raffle-count"))
}
