package patrick.redditnotifier

import com.github.nscala_time.time.Imports._
import java.io.File
import org.joda.time.DateTime
import patrick.redditnotifier.io.FileWriter
import patrick.redditnotifier.json.{SubredditParser, Details, SettingsJsonFormat}
import scala.io.Source

object Notifier {
  val settings = SettingsJsonFormat.parseSettings("/settings.json")

  def recentPostIds: List[String] =
    if (new File(settings.recentPostsFileName).exists)
      Source.fromFile(settings.recentPostsFileName).getLines().take(25).toList
    else Nil

  def main(args: Array[String]) {
    args.toList match {
      case subreddit :: option :: Nil => {
        SubredditParser(subreddit, option).getNewPosts(recentPostIds).take(settings.maxPosts) match {
          case Nil => println("No new posts!")
          case posts: List[Details] => {
            println(posts.size + " new posts found!")
            posts.foreach(sendEmailForPost)
            FileWriter.writeToFile(settings.outputDirectory, settings.recentPostsFileName, posts.map(_.id) ::: recentPostIds)
          }
        }
      }
      case _ => println("Usage: notifier [subreddit] [option]")
    }
  }

  def sendEmailForPost(post: Details) {
    FileWriter.writeToFile(settings.outputDirectory, settings.emailFileName, buildLinesToSend(settings.recipients, settings.sender, post.title, post.url, post.created_utc));
    new Email(settings.recipients).send(settings.outputDirectory + settings.emailFileName)
  }

  def buildLinesToSend(to: List[String], from: String, subject: String, url: String, created: Int): List[String] = {
    val recipients = to.mkString(",")
    val secondsPassed = (new DateTime(0).plusSeconds(created) to DateTime.now).toDuration.seconds
    List(s"To:$recipients ", s"From:$from ", s"Subject:$subject \n", s"$url ", s"\nPost was created: $secondsPassed seconds ago!")
  }
}
