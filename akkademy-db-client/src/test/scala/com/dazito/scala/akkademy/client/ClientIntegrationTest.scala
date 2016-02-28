package com.dazito.scala.akkademy.client

import com.dazito.scala.dakkabase.messages.KeyNotFoundException
import org.scalatest.{FunSpecLike, Matchers}
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Created by daz on 27/02/2016.
 */
class ClientIntegrationTest extends FunSpecLike with Matchers {
    val client = new Client("127.0.0.1:2552")

    describe("Dakkabase Client Test") {
        import scala.concurrent.ExecutionContext.Implicits.global
        it("should set a value") {
            client.set("123", new Integer(123))
            val futureResult = client.get("123")
            val result = Await.result(futureResult, 5 seconds)

            result should equal(123)
        }

        it("should delete a value") {
            client.delete("123")
            val futureResult = client.get("123").recover({
                case t: KeyNotFoundException => "Delete works!"
            })
            val result = Await.result(futureResult, 5 seconds)

            result should equal("Delete works!")
        }

        it("should set if not exist") {
            client.setIfNotExist("new value", "non existent value")
            val futureResult = client.get("new value")
            val result = Await.result(futureResult, 5 seconds)

            result should equal("non existent value")
        }

        it("should not find the key") {
            val future = client.get("2104832").recover( {
                case t: KeyNotFoundException => "default"
            })

            val result = Await.result(future, 5 second)

            result should equal("default")
        }
    }

}
