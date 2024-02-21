package io.kevchuang.reviewboard.domain

import zio.json.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.zioJson.given
import io.kevchuang.reviewboard.types.NotEmpty

object health:
  final case class HealthCheckResponse(response: String :| NotEmpty)
  object HealthCheckResponse:
    given JsonCodec[HealthCheckResponse] = DeriveJsonCodec.gen
  end HealthCheckResponse
end health
