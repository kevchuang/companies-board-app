package io.kevchuang.reviewboard.http.server

import zio.*
import zio.http.{HttpApp, Server}

trait HttpServer:
  def start[R](
      routes: HttpApp[R]
  ): ZIO[R & Server, Throwable, Unit]
end HttpServer

object HttpServer:
  def start[R](
      routes: HttpApp[R]
  ): ZIO[R & HttpServer & Server, Throwable, Unit] =
    ZIO.serviceWithZIO[HttpServer](_.start(routes))
end HttpServer
