package com.dazito.scala.akkademy.client

import akka.actor.Actor.Receive
import akka.actor.{Actor, Stash}
import com.dazito.scala.dakkabase.messages.{Disconnected, Connected, GetRequest}

/**
 * Created by daz on 08/03/2016.
 */
class RemoteActorProxy extends Actor with Stash {

    var isOnline = false;

    override def receive: Receive = {
        case message: GetRequest =>
            if(isOnline) {
                processMessage(message)
            }
            else {
                stash()
            }
        case _: Connected =>
            isOnline = true
            unstashAll()
        case _: Disconnected =>
            isOnline = false;
    }

    def processMessage(message: AnyRef) = {
        // Do something
    }
}
