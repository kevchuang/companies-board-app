package io.kevchuang.reviewboard.http.endpoints

import sttp.tapir.server.ServerEndpoint
import zio.*

trait HttpEndpoint[I, E, O]:
  def endpointDescription: ApiEndpoint[I, E, O]
  def endpointLogic: I => Task[O]
  def serverEndpoint: ServerEndpoint[Any, Task] =
    endpointDescription.serverLogicSuccess[Task](endpointLogic)
end HttpEndpoint
