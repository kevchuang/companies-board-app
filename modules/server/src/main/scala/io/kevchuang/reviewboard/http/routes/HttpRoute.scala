package io.kevchuang.reviewboard.http.routes

import sttp.tapir.server.ServerEndpoint
import zio.*

trait HttpRoute:
  def routes: List[ServerEndpoint[Any, Task]]
end HttpRoute
