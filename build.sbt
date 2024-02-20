import BuildHelper.*

lazy val foundations = (project in file("modules/foundations"))
  .settings(standardSettings)
  .settings(libraryDependencies ++= Dependencies.dependencies)

lazy val root = (project in file("."))
  .settings(nameSettings)
  .aggregate(foundations)
  .dependsOn(foundations)
