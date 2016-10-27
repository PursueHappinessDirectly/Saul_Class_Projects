package LiangTan

import edu.illinois.cs.cogcomp.saul.datamodel.DataModel
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.breakOut
import Readers.LiangTanReader.Match

/**
  * Created by LiangTan on 10/24/16.
  */
object MatchDataModel extends DataModel{
  val Matches = node[Match]

  val matchFeature = property(Matches) {
    x: Match => {
      x.getMatch.asScala.map(_.doubleValue)(breakOut).toList
    }
  }

  val result = property(Matches) {
    x: Match => {
      x.getLabel
    }
  }
}
