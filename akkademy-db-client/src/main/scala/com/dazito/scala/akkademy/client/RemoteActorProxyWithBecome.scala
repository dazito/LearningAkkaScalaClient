package com.dazito.scala.akkademy.client

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, Stash}
import com.dazito.scala.dakkabase.messages.{Disconnected, Connected, GetRequest}

/**
 * Created by daz on 09/03/2016.
 */
class RemoteActorProxyWithBecome extends Actor with Stash {

    override def receive: Receive = {
        case x: GetRequest =>
            stash()
        case _: Connected =>
            context.become(online)
            unstashAll()
    }

    def online: Receive = {
        case x: GetRequest =>
            processMessage(x)
        case _: Disconnected =>
            context.unbecome()
    }

    def processMessage(message: AnyRef) = {
        // Do something
    }
}
