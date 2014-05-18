package patrick.redditnotifier

import java.io.File
import scala.sys.process._

class Email(to: List[String]) {
  val recipients = to.mkString(",")

  def send(fileName: String) = {
    val strings = ("/usr/sbin/ssmtp" :: to).mkString(" ")
    strings.#<(new File(fileName)).!
  }
}