package com.wixpress.workshop

import org.joda.time.DateTime
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope
import com.wixpress.hoopoe.ids._
import com.wixpress.hoopoe.test._

/**
 * @author noamal
 * @since 6/24/14
 */
class MatchersLesson1 extends SpecificationWithJUnit with ResultMatchers with Mockito {

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


  "Equality" should {

    "create matcher for identity according to id only" in new ctx {
      testedUnit.identityOf(result)
    }

    "create matcher for identity with non comparable property" in new ctx {
      testedUnit.modifiedClassFrom(result)
    }

    "create matcher for wrapper class" in new ctx {
      testedUnit.wrapperOf(result)
    }

    "crate matcher for modified wrapper class" in new ctx {
      testedUnit.modifiedWrapperOf(result)
    }
  }


}
