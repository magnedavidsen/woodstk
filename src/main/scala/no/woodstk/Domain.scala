package no.woodstk

case class Artist(id: Option[Int], name: String, imgUrl: String)
case class Vote(id: Option[Int], artistId: Int, points: Int, ip: String, session: String)
