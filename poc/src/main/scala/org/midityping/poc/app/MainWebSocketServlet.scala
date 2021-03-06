package org.midityping.poc.app

import org.eclipse.jetty.websocket.servlet.{WebSocketServlet, WebSocketServletFactory}

class MainWebSocketServlet extends WebSocketServlet {
  override def configure(factory: WebSocketServletFactory): Unit = {
    factory.getPolicy().setIdleTimeout(10000)
    factory.setCreator(new MainWebSocketCreator(MidiTypingApp.system))
  }
}
