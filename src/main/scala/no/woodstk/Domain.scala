package no.woodstk

case class Artist(id: Option[Int], name: String, imgUrl: String)
case class Vote(artistId: String, points: Int, ip: String)
