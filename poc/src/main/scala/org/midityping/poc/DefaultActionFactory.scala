package org.midityping.poc

class DefaultActionFactory extends ActionFactory {
  override def createAction(descriptor: ActionDescriptor): Action = {
    ActionType.withName(descriptor.actionType) match {
      case ActionType.KeyPress => KeyPressAction(descriptor.arg)
    }
  }
}
