package com.wixpress.workshop

import com.wixpress.hoopoe.ids._
import com.wixpress.hoopoe.test._
import org.joda.time.DateTime
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

/**
 * @author noamal
 * @since 6/25/14
 */
class MatchersLesson3 extends SpecificationWithJUnit with ResultMatchers with Mockito {

  trait ctx extends Scope {
    val service = mock[TestedInterface]

    val testedUnit = new SomeTestedClass(service)


    val result = new Result(id = randomGuid[Result], dateTime = new DateTime)
    val content = randomStr
  }

  // matchers should
  // 1. be readable in a sentence
  // 2. not interfere with the tested object
  // 3. composable
  // 4. reusable
  // 5. Never be on test scope, class context only


  "JsonMatchers" should {

    "match object json representation" in new ctx {
      testedUnit.jsonOf(result)
    }

    "match object json representation" in new ctx {
      testedUnit.modifiedJsonOf(result)
    }

    "match object json string representation" in new ctx {
      testedUnit.jsonStrOf(result)
    }

    "match object json string representation" in new ctx {
      testedUnit.modifiedJsonStrOf(result)
    }
  }
}