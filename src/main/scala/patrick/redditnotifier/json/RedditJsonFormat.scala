package patrick.redditnotifier.json

import spray.json._

case class Details(domain: String, subReddit: String, id: String, title: String, selfText: String, url: String, num_comments: Int, author: String, created_utc: Int)

case class DetailedRedditJson(kind: String, data: Details)

case class OuterData(modHash: String, children: List[DetailedRedditJson])

case class BaseRedditJson(kind: String, data: OuterData)

object RedditJsonFormat extends DefaultJsonProtocol {
  implicit val detailsFormat = jsonFormat(Details, "domain", "subreddit", "id", "title", "selftext", "url", "num_comments", "author", "created_utc")
  implicit val detailedRedditFormat = jsonFormat2(DetailedRedditJson)
  implicit val outerData = jsonFormat(OuterData, "modhash", "children")
  implicit val baseRedditFormat = jsonFormat2(BaseRedditJson)
}
