package io.kevchuang.reviewboard.services.health

import io.kevchuang.reviewboard.domain.health.HealthCheckResponse
import zio.*
import io.github.iltotore.iron.*

object HealthServiceLive:
  lazy val live: ULayer[HealthService] =
    ZLayer.succeed(
      new HealthService:
        def checkHealth: UIO[HealthCheckResponse] =
          ZIO.succeed(HealthCheckResponse("All good !"))
    )
end HealthServiceLive
