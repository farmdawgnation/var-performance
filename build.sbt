name := "var-performance"

organization := "me.frmr.lift"

scalaVersion := "2.11.4"

libraryDependencies += "net.liftweb" %% "lift-util" % "3.0-SNAPSHOT"

libraryDependencies += "net.liftweb" %% "lift-webkit" % "3.0-SNAPSHOT"

libraryDependencies += "net.liftweb" %% "lift-testkit" % "3.0-SNAPSHOT"

libraryDependencies += "javax.servlet"     %  "servlet-api"        % "2.5" % "provided"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp"        % "9.2.3.v20140905"  % "compile"

mainClass in Compile := Some("me.frmr.lift.PerformanceTester")
