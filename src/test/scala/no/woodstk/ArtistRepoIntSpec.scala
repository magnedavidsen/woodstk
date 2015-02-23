package no.woodstk

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.{Fragments, Step}

import scala.slick.driver.JdbcDriver.simple._

object databaseSetup extends InMemDBEnvironment with DBTestData  {

  implicit val session = Database.forDataSource(dataSource).createSession()

  lazy val createDb = {
    createAllTables
    println("creating the database")
  }

  lazy val insertData = {
    insertTestData
    println("inserting test data")
  }

  lazy val closeSession = {
    session.close
    println("closing session")
  }
}

@RunWith(classOf[JUnitRunner])
class ArtistRepoIntSpec extends Specification with InMemDBEnvironment with DBTestData {

  lazy val dbSetup = databaseSetup
  override def map(fs: =>Fragments) = Step(dbSetup.createDb)  ^ Step(dbSetup.insertData) ^ fs ^ Step(dbSetup.closeSession)

  "ArtistRepo" should {
    "be able to create an artist" in {
      artistRepo.createArtist(Artist(Some(2), "Name", "img"))
      artistRepo.getArtists().length must beEqualTo(2)

    }
  }
}
