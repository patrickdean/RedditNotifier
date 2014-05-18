import AssemblyKeys._ // put this at the top of the file

name := "RedditNotifier"

version := "0.1"

scalaVersion := "2.10.2"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "0.4.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "io.spray" %%  "spray-json" % "1.2.5"

resolvers += "spray" at "http://repo.spray.io/"

org.scalastyle.sbt.ScalastylePlugin.Settings

assemblySettings