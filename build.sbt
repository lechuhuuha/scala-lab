name := "scala-lab"

version := "1.0"

scalaVersion := s"3.3.7"

val akkaRepoUrl = {
  val envUrl = sys.env.get("AKKA_REPO_URL").map(_.trim).filter(_.nonEmpty)
  val fileUrl = {
    val localConfig = file(".akka-repo-url")
    if (localConfig.exists()) Option(IO.read(localConfig).trim).filter(_.nonEmpty) else None
  }

  val url = envUrl.orElse(fileUrl).getOrElse(
    throw new MessageOnlyException(
      "Missing Akka resolver URL. Set AKKA_REPO_URL or create .akka-repo-url with your tokenized URL."
    )
  )

  val looksLikePlaceholder =
    url.contains("<") || url.contains(">") ||
      url.contains("{") || url.contains("}") ||
      url.contains("your-token") || url.contains("your-tokenized-path")

  if (looksLikePlaceholder) {
    throw new MessageOnlyException(
      "AKKA_REPO_URL looks like a placeholder. Use the exact tokenized URL from https://account.akka.io/token."
    )
  }

  url
}

resolvers += "Akka library repository".at(akkaRepoUrl)

lazy val akkaVersion = "2.10.15"

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.18",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test
)
