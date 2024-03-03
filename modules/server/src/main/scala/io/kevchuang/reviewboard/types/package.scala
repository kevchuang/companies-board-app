package io.kevchuang.reviewboard

import io.github.iltotore.iron.constraint.all.*
import zio.http.URL
import zio.json.*

package object types:
  type OnlyLetters = ForAll[Letter] DescribedAs "Should contain only letters"
  type NotEmpty    = Not[Empty] DescribedAs "Should not be empty"

  given JsonDecoder[URL] = JsonDecoder[String].mapOrFail(url =>
    URL.decode(url).fold(e => Left(e.getMessage), value => Right(value))
  )
  given JsonEncoder[URL] = JsonEncoder[String].contramap(_.encode)
end types
