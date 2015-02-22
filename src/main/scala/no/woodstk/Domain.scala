package no.woodstk

case class Artist(id: Option[String], name: String, imgUrl: String)
case class Vote(artistId: String, points: Int)
