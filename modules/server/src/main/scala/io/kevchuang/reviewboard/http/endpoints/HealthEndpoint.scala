package io.kevchuang.reviewboard.http.endpoints

import io.kevchuang.reviewboard.domain.health.*
import io.kevchuang.reviewboard.domain.health.HealthCheckResponse.given
import io.kevchuang.reviewboard.services.health.HealthService
import sttp.tapir.*
import sttp.tapir.codec.iron.{*, given}
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.{*, given}
import zio.*

final case class HealthEndpoint(health: HealthService)
    extends HttpEndpoint[HealthService, Unit, Unit, HealthCheckResponse]:
  def endpointDescription: ApiEndpoint[Unit, Unit, HealthCheckResponse] =
    endpoint
      .tag("health")
      .name("health")
      .description("health check")
      .get
      .in("health")
      .out(jsonBody[HealthCheckResponse])

  def endpointLogic: Unit => UIO[HealthCheckResponse] = _ => health.checkHealth
end HealthEndpoint

object HealthEndpoint:
  def make: RIO[HealthService, HealthEndpoint] =
    for health <- ZIO.service[HealthService]
    yield HealthEndpoint(health)

end HealthEndpoint
