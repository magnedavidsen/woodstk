package no.woodstk

import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, write}
import org.slf4j.{Logger, LoggerFactory}
import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response.{ResponseString, _}

trait PlanComponent{this: ArtistRepoComponent with VoteRepoComponent  =>
  def artistPlan: ArtistPlan
  def votePlan: VotePlan

  class ArtistPlan extends Plan {
    implicit val formats = DefaultFormats
    val logger : Logger = LoggerFactory.getLogger("nbrno.ArtistPlan")

    def intent = {
      case GET(Path("/api/artists")) => {
        JsonContent ~> ResponseString(write(artistRepo.getArtists()))
      }
    }
  }

  class VotePlan extends Plan {
    implicit val formats = DefaultFormats
    val logger: Logger = LoggerFactory.getLogger("nbrno.VotePlan")

    def intent = {
      case req @ POST(Path("/api/vote")) => {
        val vote = read[Vote](Body.string(req))
        voteRepo.registerVote(vote)
        Created
      }
    }
  }
}
