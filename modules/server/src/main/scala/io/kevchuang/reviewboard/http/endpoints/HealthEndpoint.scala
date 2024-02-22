package io.kevchuang.reviewboard.http.endpoints

import io.kevchuang.reviewboard.domain.health.*
import io.kevchuang.reviewboard.domain.health.HealthCheckResponse.given
import io.kevchuang.reviewboard.http.controllers.HealthController
import sttp.tapir.*
import sttp.tapir.codec.iron.{*, given}
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.{*, given}
import zio.{Task, UIO}

private class HealthEndpoint(healthController: HealthController)
    extends HttpEndpoint[Unit, Unit, HealthCheckResponse]:
  def endpointDescription: ApiEndpoint[Unit, Unit, HealthCheckResponse] =
    endpoint
      .tag("health")
      .name("health")
      .description("health check")
      .get
      .in("health")
      .out(jsonBody[HealthCheckResponse])

  def endpointLogic: Unit => Task[HealthCheckResponse] = _ =>
    healthController.allGood
end HealthEndpoint

object HealthEndpoint:
  def make: UIO[HealthEndpoint] =
    for healthController <- HealthController.make
    yield new HealthEndpoint(healthController)

end HealthEndpoint
