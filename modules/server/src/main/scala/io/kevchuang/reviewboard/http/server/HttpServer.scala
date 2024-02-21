package io.kevchuang.reviewboard.http.server

import sttp.tapir.server.ServerEndpoint
import zio.*
import zio.http.Server

trait HttpServer:
  def start(
      endpoints: List[ServerEndpoint[Any, Task]]
  ): ZIO[Server, Throwable, Unit]
end HttpServer

object HttpServer:
  def start(
      endpoints: List[ServerEndpoint[Any, Task]]
  ): ZIO[HttpServer & Server, Throwable, Unit] =
    ZIO.serviceWithZIO[HttpServer](_.start(endpoints))
end HttpServer