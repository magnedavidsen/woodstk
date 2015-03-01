package no.woodstk

import dispatch.{Http, Req, host}
import org.json4s.DefaultFormats
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.{BeforeExample, Before, Fragments, Step}
import org.json4s.native.Serialization.{read, write}
import dispatch._, Defaults._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.slick.driver.JdbcDriver.simple._

object databaseSetupVote extends InMemDBEnvironment with DBTestData  {

  implicit val session = Database.forDataSource(dataSource).createSession()

  lazy val createDb = {
    createAllTables
    println("creating the database")
  }

  lazy val createTestData = {
    artistRepo.artists += Artist(Some(1), "artist-1", "YO")
    artistRepo.artists += Artist(Some(2), "artist-2", "YO")
    artistRepo.artists += Artist(Some(3), "artist-3", "YO")
  }

  def cleanVoteTable = {
    voteRepo.votes.ddl.drop
    voteRepo.votes.ddl.create
  }

  lazy val closeSession = {
    session.close
    println("closing session")
  }
}

@RunWith(classOf[JUnitRunner])
class VoteRepoIntSpec extends Specification with InMemDBEnvironment with BeforeExample {

  val server = unfiltered.jetty.Server.anylocal.plan(votePlan).start()
  lazy val myHost = host("localhost", server.portBindings.head.port)
  implicit val formats = DefaultFormats
  implicit val session = dbSetup.session

  sequential

  lazy val dbSetup = databaseSetupVote
  override def map(fs: => Fragments) = Step(dbSetup.createDb) ^ Step(dbSetup.createTestData) ^ fs ^ Step(dbSetup.closeSession)

  override def before = {
    println("cleaning artist table")
    dbSetup.cleanVoteTable
  }

  "post to vote" should {
    "return created" in {
      val json = write(Vote(None, 1, 1, "ip", "session"))
      val req = TestHelper.setJsonHeaders(myHost / "api" / "vote").setBody(json).POST

      Await.result(Http(req), Duration(3, "sec")).getStatusCode must beEqualTo(201)
    }

    "create votes in the database" in {
      val testVote = Vote(None, 1, 1, "ip", "session")
      val json = write(testVote)
      val req = TestHelper.setJsonHeaders(myHost / "api" / "vote").setBody(json).POST

      Await.result(Http(req), Duration(3, "sec")).getStatusCode must beEqualTo(201)
      Database.forDataSource(dataSource) withDynSession {

        voteRepo.votes.list.length must beEqualTo(1)
        voteRepo.votes.list.head.artistId must beEqualTo(testVote.artistId)
        voteRepo.votes.list.head.points must beEqualTo(testVote.points)
        voteRepo.votes.list.head.ip must beEqualTo(testVote.ip)
        voteRepo.votes.list.head.session must beEqualTo(testVote.session)
      }
    }
  }
}


object TestHelper {
  def setJsonHeaders(endpoint: Req): Req = {
    endpoint.setHeader("Content-Type", "application/json;charset=UTF-8")
      .setHeader("Accept", "application/json")
  }
}
