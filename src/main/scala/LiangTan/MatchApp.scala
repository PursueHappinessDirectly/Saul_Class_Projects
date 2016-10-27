package LiangTan

import LiangTan.MatchClassifiers._
import LiangTan.MatchDataModel.Matches
import Readers.LiangTanReader.DataReader
import scala.collection.JavaConversions._

/**
  * Created by LiangTan on 10/24/16.
  */
object MatchApp extends App {

  val Train = new DataReader()
  Train.WinRateReader("data/LiangTanData/HeroWinRate.df")
  Train.MatchReader("data/LiangTanData/MatchTrain.csv", 300)
  val allTrain = Train.matches.toList

  val Test = new DataReader()
  Test.WinRateReader("data/LiangTanData/HeroWinRate.df")
  Test.MatchReader("data/LiangTanData/MatchTest.csv", 89)
  val allTest =  Test.matches.toList

  Matches.populate(allTrain)
  Matches.populate(allTest,false)

  MatchClassifiers.learn(10)
  MatchClassifiers.test()

}
