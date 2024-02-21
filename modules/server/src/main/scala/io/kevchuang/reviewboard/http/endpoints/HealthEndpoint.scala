package io.kevchuang.reviewboard.http.endpoints

import io.kevchuang.reviewboard.domain.health.*
import io.kevchuang.reviewboard.domain.health.HealthCheckResponse.given
import sttp.tapir.*
import sttp.tapir.codec.iron.{*, given}
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.{*, given}

trait HealthEndpoint:
  val healthEndpoint: ApiEndpoint[Unit, Unit, HealthCheckResponse] =
    endpoint
      .tag("health")
      .name("health")
      .description("health check")
      .get
      .in("health")
      .out(jsonBody[HealthCheckResponse])
end HealthEndpoint
