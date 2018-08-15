package org.midityping.poc.midi

import javax.sound.midi.{MidiSystem, ShortMessage}
import org.midityping.poc.common.Note
import org.midityping.poc.events.EventType.EventType
import org.midityping.poc.events.{Event, EventHandler, EventListener, EventType}

class MidiEventListener extends EventListener {
  var handlers: Seq[EventHandler] = Seq.empty

  def subscribe(eventHandler: EventHandler) = {
    handlers = handlers :+ eventHandler
  }

  def eventTypeFrom(command: Int): EventType = {
    command match {
      case ShortMessage.NOTE_ON => EventType.MidiNoteOn
      case ShortMessage.NOTE_OFF => EventType.MidiNoteOff
    }
  }

  def message(shortMessage: ShortMessage) = {
    sendEventToHandlers(Event(
      eventTypeFrom(shortMessage.getCommand),
      shortMessage.getChannel,
      shortMessage.getData2,
      Note(shortMessage.getData1))
    )
  }

  def sendEventToHandlers(event: Event): Unit = {
    handlers.foreach(_.message(event))
  }

  def start = {
    MidiSystem.getTransmitter.setReceiver(new MidiReceiver(this))
  }
}
