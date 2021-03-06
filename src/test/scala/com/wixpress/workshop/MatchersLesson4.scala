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
class MatchersLesson4 extends SpecificationWithJUnit with ResultMatchers with Mockito {

  trait ctx extends Scope {
    val service = mock[TestedInterface]

    val testedUnit = new SomeTestedClass(service)


    val result = new Result(id = randomGuid[Result], dateTime = new DateTime)
    val anotherResult = new Result(id = randomGuid[Result], dateTime = new DateTime)

    val content = randomStr
  }

  // matchers should
  // 1. be readable in a sentence
  // 2. not interfere with the tested object
  // 3. composable
  // 4. reusable
  // 5. Never be on test scope, class context only


  "Collections" should {

    "create matcher for seq of modified objects" in new ctx {
      testedUnit.someModifiedSeqOf(Seq(result, anotherResult))
    }

    "create matcher for full map" in new ctx {
      testedUnit.mapOf(Seq(result, anotherResult))
    }

    "create matcher for just one element" in new ctx {
      testedUnit.mapWithRandomStuff(result)
    }

  }
}
