package patrick.redditnotifier.json

import RedditJsonFormat._
import scala.io.Source
import spray.json._

case class SubredditParser(subreddit: String, option: String) {

  val url = s"http://www.reddit.com/r/$subreddit/$option.json?sort=new"

  def parse[T](s: String)(implicit reader: JsonReader[T]): T = JsonParser(s).convertTo[T]

  def jsonResponse(url: String) = Source.fromURL(url).mkString

  def parseReddit = parse[BaseRedditJson](jsonResponse(url))

  def getNewPosts(recentPostIds: List[String]): List[Details] = {
    val newPosts = parseReddit.data.children.takeWhile(post => !recentPostIds.contains(post.data.id))
    for (child <- newPosts) yield child.data
  }
}
