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
class MatchersLesson5 extends SpecificationWithJUnit with ResultMatchers with Mockito {

  trait ctx extends Scope {
    val service = mock[TestedInterface]

    val testedUnit = new SomeTestedClass(service)


    val result = new Result(id = randomGuid[Result], dateTime = new DateTime)
    val anotherResult = new Result(id = randomGuid[Result], dateTime = new DateTime)

    val content = randomStr

    val times = randomInt(from = 5, to = 10)
  }

  "Mockito" should {

    "validate execution" in new ctx {
      testedUnit.triggerApi(result)
    }

    "validate multiple executions" in new ctx {
      testedUnit.triggerMoreThanOnce(result, times)
    }

    "validate execution of random content" in new ctx {
      testedUnit.triggerApiWithRandomContent(result)
    }

    "validate execution of random and modified content" in new ctx {
      testedUnit.triggerApiWithModifiedContent(result)
    }

    "multiple executions" in new ctx {
      testedUnit.triggerMultipleApis(result)
    }

  }
}
