package debugview

import debugview.server._

import java.net._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object client {

  val socket = new Socket("127.0.0.1", 1234)
  val messenger = TcpMessenger(socket)
  val segmentSize = 1024

  def renderHtml[T](x: T)(implicit f: HtmlRenderer[T]) = {
    val content = f.str(x)
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
