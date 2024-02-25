package io.kevchuang.reviewboard.services.health

import io.kevchuang.reviewboard.domain.health.HealthCheckResponse
import zio.*

trait HealthService:
  def checkHealth: ZIO[Any, Nothing, HealthCheckResponse]
end HealthService

object HealthService:
  def checkHealth: ZIO[HealthService, Nothing, HealthCheckResponse] =
    ZIO.serviceWithZIO[HealthService](_.checkHealth)
end HealthService
