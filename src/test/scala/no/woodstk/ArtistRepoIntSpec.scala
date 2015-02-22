package no.woodstk

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class ArtistRepoIntSpec extends Specification {
  val repo = new ArtistRepo
  val artist = Artist(None, "Artist", "ImgUrl")

  "ArtistRepo" should {

    "be able to write first event" in {
      val response = Await.result( repo.createArtist(artist), Duration(10, "sec"))
      response.getStatusCode should beEqualTo(201)
    }
  }
}