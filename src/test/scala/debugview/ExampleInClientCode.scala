package debugview

import debugview.client._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object ExampleInClientCode extends App {

  val htmlStr = "<html>Html page from the <strong>test client</strong>. </html>"
  RenderService.renderHtmlContent(htmlStr)

  val bp = 0

}
