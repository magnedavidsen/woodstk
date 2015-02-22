package no.woodstk

import java.util.UUID

import dispatch.{Http, url}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

class ArtistRepo {

  def createArtist(artist: Artist) = {
    val uuid : String = UUID.randomUUID().toString
    val headers : Map[String, Seq[String]] = Map("Content-Type" -> Seq("application/json"), "ES-EventType" -> Seq("ArtistCreated"), "ES-EventId" -> Seq(uuid))
    val request = url("http://192.168.59.103:2113/streams/artists").setHeaders(headers)
    def requestWithBody = request << artistToJson(artist)
    Http(requestWithBody)
  }

  def artistToJson(artist: Artist) = {
    val json = ("artist" -> ("id" -> artist.id) ~ ("name" -> artist.name) ~ ("imgUrl" -> artist.imgUrl))
    compact(render(json))
  }
}
