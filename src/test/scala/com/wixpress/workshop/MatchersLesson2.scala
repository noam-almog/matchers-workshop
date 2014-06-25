package com.wixpress.workshop

import com.wixpress.hoopoe.test._
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

/**
 * @author noamal
 * @since 6/25/14
 */
class MatchersLesson2 extends SpecificationWithJUnit with ResultMatchers with Mockito {


  trait ctx extends Scope {
    val service = mock[TestedInterface]

    val testedUnit = new SomeTestedClass(service)


    val content = randomStr
  }

  // matchers should
  // 1. be readable in a sentence
  // 2. not interfere with the tested object
  // 3. composable
  // 4. reusable
  // 5. Never be on test scope, class context only


  "Equality" should {


    "Exceptions" should {

      "create a matcher for an exception" in new ctx {
        testedUnit.throwExceptionWith(content)
      }

      "create a matcher for an exception with cause" in new ctx {
        testedUnit.throwExceptionWith(content)
      }

    }
  }
}

