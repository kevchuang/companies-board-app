package io.kevchuang.reviewboard.http.routes

import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import sttp.tapir.ztapir.ZServerEndpoint
import zio.*
import zio.http.HttpApp

trait HttpRoute[R]:
  def endpoints: List[ZServerEndpoint[R, Any]]
  def routes: HttpApp[R] = ZioHttpInterpreter().toHttp(endpoints)
end HttpRoute
