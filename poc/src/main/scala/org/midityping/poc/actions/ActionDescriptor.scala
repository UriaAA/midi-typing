package org.midityping.poc.actions

import org.midityping.poc.actions.ActionType.ActionType

case class ActionDescriptor(actionType: String, arg: String, modifiers: Modifiers = Modifiers.None)

object ActionDescriptor {
  def apply(actionType: ActionType, arg: String): ActionDescriptor = new ActionDescriptor(actionType.toString, arg)

  def apply(arg: String): ActionDescriptor = new ActionDescriptor(ActionType.KeyStroke.toString, arg)
}