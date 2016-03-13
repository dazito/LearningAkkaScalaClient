package com.dazito.scala.akkademy.client.state

/**
 * Created by daz on 13/03/2016.
 */
sealed trait State {
    case object Disconnected extends State // Not online and no messages are queued
    case object Connected extends State // Online and no messages are queued
    case object ConnectedAndPending extends State // Online and messages are pending
}
