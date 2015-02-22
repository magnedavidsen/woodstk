package no.woodstk

import java.util.UUID

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ArtistRepoSpec extends Specification {

  val uuid = "9450e9f2-9c22-4b11-812b-74c396f9e05b"
  val repo = new ArtistRepo
  val artistWithId = Artist(Some(uuid), "ArtistName", "ImgUrl")
  val artistWithoutId = Artist(None, "ArtistName", "ImgUrl")


  //TODO write real tests for json production
  "ArtistToJson" should {
    "be able to handle artist with id" in {
      repo.artistToJson(artistWithId) must contain("ArtistName")
    }
  }

}
