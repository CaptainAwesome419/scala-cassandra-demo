import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "EmployeeSystem",
    idePackagePrefix := Some("com.babatunde"),
    libraryDependencies+= ("org.projectlombok" % "lombok" % "1.18.24"),
    libraryDependencies+= "com.datastax.cassandra" % "cassandra-driver-core" % "4.0.0",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.13",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.13" % "test",
  )
