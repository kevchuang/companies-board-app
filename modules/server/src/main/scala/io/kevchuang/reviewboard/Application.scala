package io.kevchuang.reviewboard

import io.kevchuang.reviewboard.http.endpoints.HealthEndpoint
import io.kevchuang.reviewboard.http.server.{HttpServer, HttpServerLive}
import zio.*
import zio.http.Server

object Application extends ZIOAppDefault:

  private val program: ZIO[HttpServer & Server, Throwable, Unit] =
    for
      healthEndpoint <- HealthEndpoint.make
      endpoints       = List(healthEndpoint.serverEndpoint)
      _              <- HttpServer.start(endpoints)
    yield ()

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    program
      .provide(Server.default, HttpServerLive.live)

end Application
