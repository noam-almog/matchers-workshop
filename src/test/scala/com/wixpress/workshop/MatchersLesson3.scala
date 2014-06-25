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
    val anotherResult = new Result(id = randomGuid[Result], dateTime = new DateTime)

    val content = randomStr
  }

  // matchers should
  // 1. be readable in a sentence
  // 2. not interfere with the tested object
  // 3. composable
  // 4. reusable
  // 5. Never be on test scope, class context only


  "Option" should {

    "create a matcher for option" in new ctx {
      testedUnit.optionOf(content)
    }

    "create a matcher for None" in new ctx {
      testedUnit.optionOf(null)
    }

    "create a matcher for modified option" in new ctx {
      testedUnit.modifiedOptionOf(result)
    }

    "create a matcher for modified result" in new ctx {
      testedUnit.wrapperOfOption(result)
    }

    "create a matcher for wrapper of modified result" in new ctx {
      testedUnit.wrapperOfOptionModifiedResult(result)
    }

  }
}