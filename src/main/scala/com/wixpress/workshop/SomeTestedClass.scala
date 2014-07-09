package com.wixpress.workshop

import com.fasterxml.jackson.databind.JsonNode
import com.wixpress.framework.time.SystemTimeSource
import com.wixpress.hoopoe.ids._
import com.wixpress.hoopoe.json._
import com.wixpress.hoopoe.json.JsonMapper.Implicits.global
import org.joda.time.DateTime

/**
 * @author noamal
 * @since 6/24/14
 */
class SomeTestedClass(service: TestedInterface) {

  val timeSource = new SystemTimeSource

  def identityOf(t: Result): Result = t

  def modifiedClassFrom(t: Result): Result = t.copy(dateTime = timeSource.dateTimeInUTC())

  def wrapperOf(t: Result) = ResultWrapper(t)

  def modifiedWrapperOf(t: Result) = wrapperOf(modifiedClassFrom(t))

  // json matchers
  def jsonOf(t: Result): JsonNode = t.asJson
  def modifiedJsonOf(t: Result): JsonNode = modifiedClassFrom(t).asJson
  def jsonStrOf(t: Result): String = t.asJsonStr
  def modifiedJsonStrOf(t: Result): String = modifiedClassFrom(t).asJsonStr


  // option matchers
  def optionOf(s: String) = Option(s)

  def modifiedOptionOf(t: Result) = Option(t).map(_.id)
  
  def wrapperOfOption(t: Result) = ResultWrapper(Option(t))

  def wrapperOfOptionModifiedResult(t: Result) = wrapperOfOption(modifiedClassFrom(t))


  // collections
  def someModifiedSeqOf(seq: Seq[Result]): Seq[Guid[Result]] = seq.map(_.id)

  def mapOf(seq: Seq[Result]): Map[Guid[Result], Result] = seq.map(t => t.id -> t).toMap

  def mapWithRandomStuff(t: Result): Map[Guid[Result], Result] = Map(t.id -> t, randomTestClassPair, randomTestClassPair, randomTestClassPair)

  private def randomTestClassPair = {
    val id = randomGuid[Result]
    id -> Result(id, timeSource.dateTimeInUTC)
  }


  // exceptions
  def throwExceptionWith(msg: String) = throw new RuntimeException(s"There is an error with your messge: [$msg]", new IllegalArgumentException)


  // mockito
  def triggerApi(r: Result): Unit = service.triggerApi(r)

  def triggerMoreThanOnce(r: Result, times: Int): Unit = (1 to times).foreach(_ -> triggerApi(r))

  def triggerApiWithRandomContent(r: Result): Unit = service.triggerApiWithRandomContent(r, timeSource.dateTimeInUTC)

  def triggerApiWithModifiedContent(r: Result): Unit = triggerApiWithRandomContent(modifiedClassFrom(r))

  def triggerMultipleApis(r: Result) {
    triggerApi(r)
    triggerApiWithRandomContent(r)
  }

}

trait TestedInterface {

  def triggerApi(r: Result): Unit

  def triggerApiWithRandomContent(r: Result, dateTime: DateTime): Unit


}


case class Result(id: Guid[Result], dateTime: DateTime)


case class ResultWrapper[T](result: T)
