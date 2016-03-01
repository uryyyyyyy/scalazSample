name := """scalazSample"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.0"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
