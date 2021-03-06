@file:JsModule("react-player")
@file:JsNonModule

package jsDirective

import react.*

@JsName("default")
external val ReactPlayer: RClass<ReactPlayerProps>

external interface ReactPlayerProps : RProps {
    var url: String
}