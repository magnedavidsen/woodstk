package no.woodstk

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.{BeforeExample, Before, Fragments, Step}

import scala.slick.driver.JdbcDriver.simple._

object databaseSetup extends InMemDBEnvironment with DBTestData  {

  implicit val session = Database.forDataSource(dataSource).createSession()

  lazy val createDb = {
    createAllTables
    println("creating the database")
  }

  def cleanArtistTable = {
    artistRepo.artists.ddl.drop
    artistRepo.artists.ddl.create
  }

  lazy val closeSession = {
    session.close
    println("closing session")
  }
}

@RunWith(classOf[JUnitRunner])
class ArtistRepoIntSpec extends Specification with InMemDBEnvironment with BeforeExample {

  sequential

  lazy val dbSetup = databaseSetup
  override def map(fs: =>Fragments) = Step(dbSetup.createDb)  ^ fs ^ Step(dbSetup.closeSession)

  override def before = {
    println("cleaning artist table")
    dbSetup.cleanArtistTable
  }

  "getArtists" should {
    "return 0 artist, if 0 is created" in {
      artistRepo.getArtists().length must beEqualTo(0)
    }
    "returns the same artist that is created" in {
      val artist = Artist(Some(1), "name-1", "img-1")
      artistRepo.createArtist(artist)
      artistRepo.getArtists().head must beEqualTo(artist)
    }
    "return 1 artist, if 1 is created" in {
      artistRepo.createArtist(Artist(Some(1), "name-1", "img-1"))
      artistRepo.getArtists().length must beEqualTo(1)
    }
    "return 3 artist, if 3 is created" in {
      artistRepo.createArtist(Artist(Some(1), "name-1", "img-1"))
      artistRepo.createArtist(Artist(Some(2), "name-2", "img-2"))
      artistRepo.createArtist(Artist(Some(3), "name-3", "img-3"))
      artistRepo.getArtists().length must beEqualTo(3)
    }
  }



}
