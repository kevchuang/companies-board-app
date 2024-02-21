package io.kevchuang.reviewboard.http.controllers

import io.kevchuang.reviewboard.domain.health.HealthCheckResponse
import io.kevchuang.reviewboard.http.endpoints.HealthEndpoint
import zio.*
import io.github.iltotore.iron.*
import sttp.tapir.server.ServerEndpoint

class HealthController private extends HealthEndpoint:
  def healthCheck: ServerEndpoint[Any, Task] =
    healthEndpoint.serverLogicSuccess[Task](_ =>
      ZIO.succeed(HealthCheckResponse("All good !"))
    )
end HealthController

object HealthController:
  def make: UIO[HealthController] = ZIO.succeed(new HealthController)
end HealthController
