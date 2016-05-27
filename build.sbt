name := """scalazSample"""

version := "1.0"

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0-M15" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.3"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
