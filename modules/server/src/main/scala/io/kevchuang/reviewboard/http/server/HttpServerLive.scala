package io.kevchuang.reviewboard.http.server

import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import zio.*
import zio.http.Server

object HttpServerLive:
  def live: ZLayer[Any, Nothing, HttpServer] =
    ZLayer.succeed(
      new HttpServer:
        override def start(
            endpoints: List[ServerEndpoint[Any, Task]]
        ): ZIO[Server, Throwable, Unit] =
          Server
            .serve(
              ZioHttpInterpreter(
                ZioHttpServerOptions.default
              ).toHttp(endpoints)
            )
    )
end HttpServerLive
