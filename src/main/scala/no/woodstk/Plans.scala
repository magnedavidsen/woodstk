package no.woodstk

import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.slf4j.{Logger, LoggerFactory}
import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response.{ResponseString, _}

trait PlanComponent{this: ArtistRepoComponent  =>
  val artistPlan: ArtistPlan

  class ArtistPlan extends Plan {
    implicit val formats = DefaultFormats
    val logger : Logger = LoggerFactory.getLogger("nbrno.StatsPlan")

    def intent = {
      case GET(Path("/api/artists")) => {
        JsonContent ~> ResponseString(write(artistRepo.getArtists()))
      }
    }
  }
}
