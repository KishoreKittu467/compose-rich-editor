import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.mohamedrejeb.richeditor.sample.common.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(content = {
        App()
    })
}