package debugview.client

import debugview.server._

import java.net.Socket

/**
 * Methods for rendering. Clients, please use these methods to render your objects.
 * Please ensure that the rendering server is running before calling these methods.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object RenderService {

  val socket = new Socket("127.0.0.1", 1234)
  val messenger = TcpMessenger(socket)
  val segmentSize = 1024

  def renderHtmlContent(content: String) = {
    messenger.sendInt(TaskCode.RenderHtmlContent)
    val segments = content.grouped(segmentSize).toSeq
    messenger.sendStrings(segments)
  }

  def renderHtmlAtPath(path: String) = {
    messenger.sendInt(TaskCode.RenderHtmlContent)
    messenger.sendString(path)
  }

  def renderSvgContent(content: String) = ???
  def renderSvgAtPath(content: String) = ???

  def renderGraphvizContent(content: String) = ???
  def renderGraphvizAtPath(content: String) = ???

}
