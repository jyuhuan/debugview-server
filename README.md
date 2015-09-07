# DebugView

## Example Usage

Run `Server` first. Then, in your code, call the render service like this:

```scala
val htmlStr = "<html>Your content. </html>"
RenderService.renderHtmlContent(htmlStr)
```

Rendering services for other types of objects will be added soon.