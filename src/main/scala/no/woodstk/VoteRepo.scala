package no.woodstk

import scala.slick.driver.JdbcDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession

trait VoteRepoComponent {this: DataSourceComponent =>

  def voteRepo: VoteRepo

  class VoteRepo {

    class Votes(tag: Tag) extends Table[Vote](tag, "votes") {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
      def artistId = column[Int]("artist_id")
      def points = column[Int]("points")
      def ip = column[String]("ip")
      def session = column[String]("session")
      def * = (id.?, artistId, points, ip, session) <> (Vote.tupled, Vote.unapply)
    }

    val votes = TableQuery[Votes]

    def registerVote(vote: Vote) = {
      Database.forDataSource(dataSource) withDynSession  {
        votes += vote
      }
    }
//TODO register vote to Event Store too
//    def createArtist(artist: Artist) = {
//      val uuid: String = UUID.randomUUID().toString
//      val headers: Map[String, Seq[String]] = Map("Content-Type" -> Seq("application/json"), "ES-EventType" -> Seq("ArtistCreated"), "ES-EventId" -> Seq(uuid))
//      val request = url("http://192.168.59.103:2113/streams/artists").setHeaders(headers)
//      def requestWithBody = request << artistToJson(artist)
//      Http(requestWithBody)
//    }
  }
}
