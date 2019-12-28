package components.video_player

import jsDirective.ReactPlayer
import kotlinx.css.*
import kotlinx.css.VerticalAlign.Companion.top
import kotlinx.html.js.onClickFunction
import model.Video
import react.*
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledButton
import styled.styledDiv

interface VideoPlayerProps: RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

class VideoPlayer(props: VideoPlayerProps) : RComponent<VideoPlayerProps, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            styledButton {
                css {
                    display = Display.block
                    backgroundColor = if(props.unwatchedVideo) Color.lightGreen else Color.red
                }
                attrs {
                    onClickFunction = {
                        props.onWatchedButtonPressed(props.video)
                    }
                }
                if(props.unwatchedVideo) {
                    +"Mark as watched"
                }
                else {
                    +"Mark as unwatched"
                }
            }
            ReactPlayer {
                attrs.url = props.video.videoUrl
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit) : ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}