package debugview

import debugview.server._

/**
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
package object client {

  def renderHtml[T](x: T)(implicit f: HtmlRenderer[T]) = {
    RenderService.renderHtmlContent(f.str(x))
  }

  def renderHtmlAtPath(path: String) = {
    RenderService.renderHtmlAtPath(path)
  }

  def renderSvgContent(content: String) = ???
  def renderSvgAtPath(content: String) = ???

  def renderGraphvizContent(content: String) = ???
  def renderGraphvizAtPath(content: String) = ???

}
