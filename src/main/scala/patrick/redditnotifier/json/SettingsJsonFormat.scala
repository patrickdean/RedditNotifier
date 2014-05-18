package patrick.redditnotifier.json

import scala.io.Source
import spray.json.{JsonParser, JsonReader, DefaultJsonProtocol}

case class Settings(maxPosts: Int, sender: String, recipients: List[String], outputDirectory: String, emailFileName: String, recentPostsFileName: String)

object SettingsJsonFormat extends DefaultJsonProtocol {
  implicit val settingsFormat = jsonFormat(Settings, "maxPosts", "sender", "recipients", "outputDirectory", "emailFileName", "recentPostsFileName")

  def parse[T](s: String)(implicit reader: JsonReader[T]): T = JsonParser(s).convertTo[T]

  def jsonResponse(fileName: String) = Source.fromInputStream(getClass.getResourceAsStream(fileName)).getLines().mkString

  def parseSettings(fileName: String) = parse[Settings](jsonResponse(fileName))
}
