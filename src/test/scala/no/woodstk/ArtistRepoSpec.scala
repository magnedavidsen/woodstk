package no.woodstk

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification

@RunWith(classOf[JUnitRunner])
class ArtistRepoSpec extends Specification {
  val repo = new ArtistRepo

  "ArtistRepo" should {

    "be able to write first event" in {
      repo.createArtist().getStatusCode should beEqualTo(201)
    }
  }

}