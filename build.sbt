import BuildHelper.*

lazy val foundations = (project in file("modules/foundations"))
  .settings(libraryDependencies ++= Dependencies.dependencies)

lazy val root = (project in file("."))
  .settings(standardSettings)
  .settings(nameSettings)
  .aggregate(foundations)
  .dependsOn(foundations)
