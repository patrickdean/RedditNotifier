RedditNotifier
==============

RedditNotifier is a Scala application that fetches posts from a particular subreddit and sends an email for each post found.

configure the fields "sender" and "recipients" of  [settings.json](https://github.com/patrickdean/RedditNotifier/blob/master/src/main/resources/settings.json) with email addresses for sending/receiving.

Usage(fetches new posts from /r/funny):
```bash
$ java -jar redditNotifier.jar funny new
