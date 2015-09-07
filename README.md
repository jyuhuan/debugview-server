# DebugView

## Example Usage

Run `debugview.server.Server` first. Then, in your code, call the rendering service like this:

```scala
import debugview.client._
val htmlStr = "<html>Your content. </html>"
renderHtmlContent(htmlStr)
```

Rendering services for other types of objects will be added soon.