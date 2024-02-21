import sbt.*

object Dependencies {
  val flywayVersion            = "9.16.0"
  val ironVersion              = "2.4.0"
  val javaJwtVersion           = "4.4.0"
  val javaMailVersion          = "1.6.2"
  val logbackVersion           = "1.4.7"
  val postgresqlVersion        = "42.5.4"
  val quillVersion             = "4.8.1"
  val stripeVersion            = "24.3.0"
  val sttpVersion              = "3.9.3"
  val tapirVersion             = "1.9.9"
  val zioConfigVersion         = "4.0.1"
  val zioJsonVersion           = "0.6.2"
  val zioMockVersion           = "1.0.0-RC12"
  val zioPreludeVersion        = "1.0.0-RC23"
  val zioLoggingVersion        = "2.2.2"
  val zioTestContainersVersion = "0.10.0"
  val zioVersion               = "2.0.21"

  val flyway: List[ModuleID] = List(
    "org.flywaydb" % "flyway-core" % flywayVersion
  )

  val iron: List[ModuleID] = List(
    "io.github.iltotore" %% "iron-zio"      % ironVersion,
    "io.github.iltotore" %% "iron-zio-json" % ironVersion
  )

  val `java-jwt`: List[ModuleID] = List(
    "com.auth0" % "java-jwt" % javaJwtVersion
  )

  val javaMail: List[ModuleID] = List(
    "com.sun.mail" % "javax.mail" % javaMailVersion
  )

  val logback: List[ModuleID] = List(
    "ch.qos.logback" % "logback-classic" % logbackVersion
  )

  val postgresql: List[ModuleID] = List(
    "org.postgresql" % "postgresql" % postgresqlVersion
  )

  val quill: List[ModuleID] = List(
    "io.getquill" %% "quill-jdbc-zio" % quillVersion
  )

  val stripe: List[ModuleID] = List(
    "com.stripe" % "stripe-java" % stripeVersion
  )

  val sttp: List[ModuleID] = List(
    "com.softwaremill.sttp.client3" %% "zio" % sttpVersion
  )

  val tapir: List[ModuleID] = List(
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client"       % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-iron"              % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-json-zio"          % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-zio"               % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server"   % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server"  % tapirVersion % Test
  )

  val `zio-config`: List[ModuleID] = List(
    "dev.zio" %% "zio-config"          % zioConfigVersion,
    "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,
    "dev.zio" %% "zio-config-typesafe" % zioConfigVersion
  )

  val `zio-json`: List[ModuleID] = List(
    "dev.zio" %% "zio-json" % zioJsonVersion
  )

  val `zio-logging`: List[ModuleID] = List(
    "dev.zio" %% "zio-logging"       % zioLoggingVersion,
    "dev.zio" %% "zio-logging-slf4j" % zioLoggingVersion
  )

  val `zio-test`: List[ModuleID] = List(
    "dev.zio"               %% "zio-test"                          % zioVersion,
    "dev.zio"               %% "zio-test-junit"                    % zioVersion     % Test,
    "dev.zio"               %% "zio-test-sbt"                      % zioVersion     % Test,
    "dev.zio"               %% "zio-test-magnolia"                 % zioVersion     % Test,
    "dev.zio"               %% "zio-mock"                          % zioMockVersion % Test,
    "io.github.scottweaver" %% "zio-2-0-testcontainers-postgresql" % zioTestContainersVersion
  )

  val zio: List[ModuleID] = List(
    "dev.zio" %% "zio"         % zioVersion,
    "dev.zio" %% "zio-prelude" % zioPreludeVersion
  )

  val dependencies: List[ModuleID] =
    flyway ++
      iron ++
      `java-jwt` ++
      javaMail ++
      logback ++
      postgresql ++
      quill ++
      stripe ++
      sttp ++
      tapir ++
  `zio-config` ++
    `zio-json` ++
    `zio-logging` ++
    `zio-test` ++
    zio

}
