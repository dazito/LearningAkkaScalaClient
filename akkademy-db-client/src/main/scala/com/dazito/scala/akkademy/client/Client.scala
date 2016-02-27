package com.dazito.scala.akkademy.client

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import akka.pattern.ask
import com.dazito.scala.dakkabase.messages.{GetRequest, SetRequest}

import scala.concurrent.Future
import scala.language.postfixOps

/**
 * Created by daz on 27/02/2016.
 */
class Client(remoteAddress: String) {
    private implicit val timeout = Timeout(2, TimeUnit.SECONDS)
    private implicit val system = ActorSystem("LocalSystem-Scala")
    private val remoteDb = system.actorSelection("akka.tcp://dakkabase-scala@" + remoteAddress + "/user/dakkabase-db")

    def set(key: String, value: Object): Unit = {
        remoteDb ? SetRequest(key, value)
    }

    def get(key: String) = {
        remoteDb ? GetRequest(key)
    }



}
