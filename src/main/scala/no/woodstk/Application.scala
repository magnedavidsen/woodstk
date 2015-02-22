package no.woodstk

import java.net.URL

import unfiltered.jetty.Http

import scala.util.Properties

object Application extends App {

  val server = Http(Properties.envOrElse("PORT", "8081").toInt).resources(new URL(getClass().getResource("/www/"), "."))
  server.run()
}
