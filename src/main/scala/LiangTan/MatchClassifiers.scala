package LiangTan
import Readers.LiangTanReader.Match
import edu.illinois.cs.cogcomp.saul.classifier.Learnable

import LiangTan.MatchDataModel._
import edu.illinois.cs.cogcomp.lbjava.learn.SparseNetworkLearner
import edu.illinois.cs.cogcomp.lbjava.learn.SupportVectorMachine

/**
  * Created by LiangTan on 10/24/16.
  */
object MatchClassifiers extends Learnable[Match](Matches) {
  def label = result
  override lazy val classifier = new SupportVectorMachine()
  override def feature = using(matchFeature)
}

