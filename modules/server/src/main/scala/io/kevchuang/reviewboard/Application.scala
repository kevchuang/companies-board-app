package io.kevchuang.reviewboard

import io.kevchuang.reviewboard.http.routes.*
import io.kevchuang.reviewboard.http.server.{HttpServer, HttpServerLive}
import io.kevchuang.reviewboard.services.Services
import io.kevchuang.reviewboard.services.company.CompanyServiceLive
import io.kevchuang.reviewboard.services.db.{DatabaseService, InMemoryDatabase}
import io.kevchuang.reviewboard.services.health.*
import zio.*
import zio.http.Server

object Application extends ZIOAppDefault:
  private val program
      : ZIO[HttpServer & Server & DatabaseService & Services, Throwable, Unit] =
    for
      companiesRoutes <- CompaniesRoutes.make
      healthRoutes    <- HealthRoutes.make
      routes           = companiesRoutes.routes ++ healthRoutes.routes
      _               <- HttpServer.start(routes)
    yield ()

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    program
      .provide(
        Server.default,
        HttpServerLive.live,
        CompanyServiceLive.live,
        InMemoryDatabase.live,
        HealthServiceLive.live
      )

end Application
