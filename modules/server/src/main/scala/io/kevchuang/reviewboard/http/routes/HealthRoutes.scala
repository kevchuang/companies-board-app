package io.kevchuang.reviewboard.http.routes

import io.kevchuang.reviewboard.http.endpoints.HealthEndpoint
import sttp.tapir.server.ServerEndpoint
import zio.*

private case class HealthRoutes(healthEndpoint: HealthEndpoint)
    extends HttpRoute:
  def routes: List[ServerEndpoint[Any, Task]] =
    List(healthEndpoint.serverEndpoint)
end HealthRoutes

object HealthRoutes:
  def make: UIO[HealthRoutes] =
    for healthEndpoint <- HealthEndpoint.make
    yield HealthRoutes(healthEndpoint)
end HealthRoutes
