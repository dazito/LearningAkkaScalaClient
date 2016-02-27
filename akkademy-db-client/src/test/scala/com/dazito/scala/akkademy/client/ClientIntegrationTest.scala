package com.dazito.scala.akkademy.client

import org.scalatest.{FunSpecLike, Matchers}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Created by daz on 27/02/2016.
 */
class ClientIntegrationTest extends FunSpecLike with Matchers {
    val client = new Client("127.0.0.1:2552")

    describe("Dakkabase Client Test") {
        it("should set a value") {
            client.set("123", new Integer(123))
            val futureResult = client.get("123")
            val result = Await.result(futureResult, 10 seconds)

            result should equal(123)
        }
    }

}
