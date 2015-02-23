package no.woodstk

import javax.sql.DataSource
import org.h2.jdbcx.JdbcDataSource;
import scala.slick.driver.JdbcDriver.simple._

trait InMemDBEnvironment extends ArtistRepoComponent with DataSourceComponent {

  val dataSource: DataSource = {
    val ds = new JdbcDataSource
    ds.setURL("jdbc:h2:mem:test1")
    ds
  }

  val artistRepo = new ArtistRepo
}

trait DBTestData{this: InMemDBEnvironment =>

  def createAllTables(implicit session: Session) = {
    artistRepo.artists.ddl.create
  }
}
