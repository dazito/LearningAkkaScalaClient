package com.dazito.scala.akkademy.client

import akka.actor.FSM
import com.dazito.scala.akkademy.client.StateContainerTypes.RequestQueue
import com.dazito.scala.dakkabase.messages.{Connected, GetRequest, Disconnected}

/**
 * Created by daz on 15/03/2016.
 */

sealed trait State
case object Disconnected extends State
case object Connected extends State
case object ConnectedAndPending extends State

case object Flush
case object ConnectedMsg

object StateContainerTypes {
    type RequestQueue = List[GetRequest]
}
class FSMActor(address: String) extends FSM[State, RequestQueue] {
    val remoteDb = context.system.actorSelection(address)


    startWith(Disconnected, null)

    when(Disconnected) {
        case (_: Connected, container: RequestQueue) =>
            if(container.headOption.isEmpty)
                goto(Connected)
            else
                goto(ConnectedAndPending)
        case (x: GetRequest, container: RequestQueue) =>
            stay using(container :+ x)
    }

    when(Connected) {
        case (x: GetRequest, container: RequestQueue) =>
            goto(ConnectedAndPending) using(container :+ x)
    }

    when(ConnectedAndPending) {
        case (Flush, container) =>
            remoteDb ! container
            goto(Connected) using Nil
        case (x: GetRequest, container: RequestQueue) =>
            stay using(container :+ x)
    }

    initialize()
}
