package io.kevchuang.reviewboard.http

import sttp.tapir.PublicEndpoint

package object endpoints:
  type ApiEndpoint[I, E, O] = PublicEndpoint[I, E, O, Any]
end endpoints
