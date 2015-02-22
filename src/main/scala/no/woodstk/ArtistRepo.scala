package no.woodstk

import java.util.UUID

import dispatch.{Http, url}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class ArtistRepo {


  def createArtist() = {

    val uuid : String = UUID.randomUUID().toString
    val headers : Map[String, Seq[String]] = Map("Content-Type" -> Seq("application/json"), "ES-EventType" -> Seq("ArtistCreated"), "ES-EventId" -> Seq(uuid))

    val request = url("http://192.168.59.103:2113/streams/artists").setHeaders(headers)

    def myPostWithBody = request << """{"key": "value"}"""

    val response = Http(myPostWithBody)

    val actualResponse = Await.result(response, Duration(3, "sec"))

    actualResponse
  }

}
