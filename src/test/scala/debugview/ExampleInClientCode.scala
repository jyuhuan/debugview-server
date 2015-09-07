package debugview

import debugview.client._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object ExampleInClientCode extends App {


  implicit val stringRenderer = new HtmlRenderer[String] {
    def str(x: String): String = x
  }

  val htmlStr = "<html>Html page from the <strong>test client</strong>. </html>"
  renderHtml(htmlStr)
  renderHtml(htmlStr)
  renderHtml(htmlStr)
  renderHtml(htmlStr)

  val bp = 0

}
