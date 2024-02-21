package io.kevchuang.reviewboard

import io.kevchuang.reviewboard.http.controllers.HealthController
import io.kevchuang.reviewboard.http.server.{HttpServer, HttpServerLive}
import zio.*
import zio.http.Server

object Application extends ZIOAppDefault:

  private val program: ZIO[HttpServer & Server, Throwable, Unit] =
    for
      health   <- HealthController.make
      endpoints = List(health.healthCheck)
      _        <- HttpServer.start(endpoints)
    yield ()

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    program
      .provide(Server.default, HttpServerLive.live)

end Application
