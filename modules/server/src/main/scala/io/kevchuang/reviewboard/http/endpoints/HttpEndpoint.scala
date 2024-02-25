package io.kevchuang.reviewboard.http.endpoints

import sttp.tapir.ztapir.*
import zio.*

trait HttpEndpoint[R, I, E, O]:
  def endpointDescription: ApiEndpoint[I, E, O]
  def endpointLogic: I => ZIO[R, E, O]
  def serverEndpoint: ZServerEndpoint[R, Any] =
    endpointDescription.zServerLogic(endpointLogic)
end HttpEndpoint
