package debugview

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object ExampleClientCode extends App {

  val htmlStr = "<html>Html page from the <strong>test client</strong>. </html>"
  RenderService.renderHtmlContent(htmlStr)

  val bp = 0

}
