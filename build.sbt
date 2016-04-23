name := """BasicJpaApp"""

version := "1.0-SNAPSHOT"

EclipseKeys.preTasks := Seq(compile in Compile)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java  // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Use .class files instead of generated .scala files for views and routes

PlayKeys.externalizeResources := false

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,	
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final" // replace by your jpa implementation
)

 