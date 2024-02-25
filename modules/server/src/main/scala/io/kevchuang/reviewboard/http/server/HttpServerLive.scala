package io.kevchuang.reviewboard.http.server

import zio.*
import zio.http.{HttpApp, Server}

object HttpServerLive:
  lazy val live: ZLayer[Any, Nothing, HttpServer] =
    ZLayer.succeed(
      new HttpServer:
        override def start[R](
            routes: HttpApp[R]
        ): ZIO[R & Server, Throwable, Unit] = Server.serve(routes)
    )
end HttpServerLive
