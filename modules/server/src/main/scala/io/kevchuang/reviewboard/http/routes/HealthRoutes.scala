package io.kevchuang.reviewboard.http.routes

import io.kevchuang.reviewboard.http.endpoints.HealthEndpoint
import io.kevchuang.reviewboard.services.health.HealthService
import sttp.tapir.ztapir.*
import zio.*

private case class HealthRoutes(healthEndpoint: HealthEndpoint)
    extends HttpRoute[HealthService]:
  def endpoints: List[ZServerEndpoint[HealthService, Any]] =
    List(healthEndpoint.serverEndpoint)
end HealthRoutes

object HealthRoutes:
  def make: RIO[HealthService, HealthRoutes] =
    for healthEndpoint <- HealthEndpoint.make
    yield HealthRoutes(healthEndpoint)
end HealthRoutes
