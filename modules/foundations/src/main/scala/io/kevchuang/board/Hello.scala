package io.kevchuang.board

object Hello extends Greeting with App:
  println(greeting)
end Hello

trait Greeting:
  lazy val greeting: String = "hello"
end Greeting
