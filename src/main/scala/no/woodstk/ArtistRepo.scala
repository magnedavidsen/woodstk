package no.woodstk

import scala.slick.driver.JdbcDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession

trait ArtistRepoComponent {this: DataSourceComponent =>

  val artistRepo: ArtistRepo

  class ArtistRepo {

    class Artists(tag: Tag) extends Table[Artist](tag, "artists") {
      def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
      def name = column[String]("name")
      def imgUrl = column[String]("img_url")
      def * = (id.?, name, imgUrl) <> (Artist.tupled, Artist.unapply)
    }
    val artists = TableQuery[Artists]

    def createArtist(artist: Artist) = {
      Database.forDataSource(dataSource) withDynSession  {
        artists += artist
      }
    }

    def getArtists() : List[Artist] = {
      Database.forDataSource(dataSource) withDynSession  {
        artists.list
      }
    }
  }
}